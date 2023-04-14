package com.example.jpa_relacje_26_zadaniekoncowe.access.user;

import com.example.jpa_relacje_26_zadaniekoncowe.access.user.dto.UserCredentialsDto;
import com.example.jpa_relacje_26_zadaniekoncowe.access.user.dto.UserDto;
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

    //chcemy liste osob, same adresy email
    public List<String> findAllUserEmails(){
        return userRepository.findAll().stream().map(User::getEmail).filter(email -> !email.equals(getCurrentUserName())).toList();
    }

    public List<UserDto> findAllUserExcludeCurrentUser(){
        return userRepository.findAll().stream().map(this::mapToUserDto).filter(u -> u.getEmail() != getCurrentUserName()).toList();
    }

    @Transactional
    //najlepiej jest dodawac te adnotacje wlasnie w warstiwe uslug a nie w repositorium
    public void deleteByEMail(String email){
        if (isCurrentUserAdmin()) {
            userRepository.deleteByEmail(email);
        }
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

    public UserDto mapToUserDto(User user){
        String firstname = user.getFirstName();
        String lastname = user.getLastName();
        String email = user.getEmail();
        String pass = user.getPassword();
        return new UserDto(firstname, lastname, email, pass);
    }

    static String getCurrentUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    Optional<User> getCurrentUser(){
        return userRepository.findByEmail(getCurrentUserName());
    }

    public Optional<UserDto> getCurrentUserDto(){
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

    public boolean isUserAdmin(String email) {
        return userRepository.findByEmail(email).get().getRoles().contains(userRoleRepository.findByName("ADMIN").get());
    }

    @Transactional
    public void addAdminRole(String userEmail) {
        if (isCurrentUserAdmin()) {
            Optional<User> userOptional = userRepository.findByEmail(userEmail);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.addRole(userRoleRepository.findByName("ADMIN").get());
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
                user.deleteRole(userRoleRepository.findByName("ADMIN").get());
                userRepository.save(user);
            }
        }
    }
}
