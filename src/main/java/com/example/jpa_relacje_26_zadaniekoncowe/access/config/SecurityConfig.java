package com.example.jpa_relacje_26_zadaniekoncowe.access.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/images/**", "/styles/**").permitAll()
                .requestMatchers("/register/**", "/registration-success").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/").hasAnyRole("USER", "ADMIN")
//                .requestMatchers("/updateUser/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form.loginPage("/login").permitAll());
        http.csrf().disable();
        return http.build();
    }

    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers("/h2-console/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
