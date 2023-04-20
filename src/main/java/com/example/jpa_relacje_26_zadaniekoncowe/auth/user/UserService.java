package com.example.jpa_relacje_26_zadaniekoncowe.auth.user;

import com.example.jpa_relacje_26_zadaniekoncowe.auth.user.dto.UserCredentialsDto;
import com.example.jpa_relacje_26_zadaniekoncowe.auth.user.dto.UserDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String ADMIN_AUTHORITY = "ROLE_ADMIN";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::mapToUserCredentialsDto);
    }

    public Optional<UserDto> findUserDtoByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::mapToUserDto);
    }

    public List<UserDto> findAllUserExcludeCurrentUser() {
        return userRepository.findAll().stream().map(this::mapToUserDto).filter(u -> !u.getEmail().equals(getCurrentUserName())).toList();
    }

    @Transactional
    public void register(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        String passwordHash = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(passwordHash);
        Optional<UserRole> userRole = userRoleRepository.findByName(USER_ROLE);
        userRole.ifPresentOrElse(
                role -> user.getRoles().add(role),
                () -> {
                    throw new NoSuchElementException();
                }
        );
        userRepository.save(user);
    }

    public boolean isCurrentUserAdmin() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equalsIgnoreCase(ADMIN_AUTHORITY));
    }

    public UserCredentialsDto mapToUserCredentialsDto(User user) {
        String email = user.getEmail();
        String password = user.getPassword();
        Set<String> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        return new UserCredentialsDto(email, password, roles);
    }

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        if (user.getRoles().contains(userRole(ADMIN_ROLE))) {
            userDto.setAdmin(true);
        }
        return userDto;
    }

    static String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    Optional<User> getCurrentUser() {
        return userRepository.findByEmail(getCurrentUserName());
    }

    public Optional<UserDto> getCurrentUserDto() {
        return getCurrentUser().map(this::mapToUserDto);
    }

    @Transactional
    public void updateUser(UserDto userDto) {
        Optional<User> usersByEmailOpt = userRepository.findByEmail(userDto.getEmail());
        if (usersByEmailOpt.isPresent()) {
            User user = usersByEmailOpt.get();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            if (!userDto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            userRepository.save(user);
        }
    }

    @Transactional
    public void addAdminRole(String userEmail) {
        if (isCurrentUserAdmin()) {
            Optional<User> userOptional = userRepository.findByEmail(userEmail);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.addRole(userRole(ADMIN_ROLE));
                userRepository.save(user);
            }
        }
    }

    @Transactional
    public void deleteAdminRole(String userEmail) {
        if (isCurrentUserAdmin()) {
            Optional<User> userOptional = userRepository.findByEmail(userEmail);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.deleteRole(userRole(ADMIN_ROLE));
                if (!user.getRoles().contains(userRole(USER_ROLE))) {
                    user.addRole(userRole(USER_ROLE));
                }
                userRepository.save(user);
            }
        }
    }

    public UserRole userRole(String roleName) {
        Optional<UserRole> optionalUserRole = userRoleRepository.findByName(roleName);
        return optionalUserRole.orElse(null);
    }
}
