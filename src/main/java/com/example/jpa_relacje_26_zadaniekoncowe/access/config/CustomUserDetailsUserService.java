package com.example.jpa_relacje_26_zadaniekoncowe.access.config;

import com.example.jpa_relacje_26_zadaniekoncowe.access.user.UserService;
import com.example.jpa_relacje_26_zadaniekoncowe.access.user.dto.UserCredentialsDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsUserService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findCredentialsByEmail(username).map(this::createUserDetails).orElseThrow(()-> new UsernameNotFoundException("User with email %s not found".formatted(username)));
    }

    private UserDetails createUserDetails(UserCredentialsDto credentials) {
        return User.builder().username(credentials.getEmail()).password(credentials.getPassword()).roles(credentials.getRoles().toArray(String[]::new)).build();
    }
}
