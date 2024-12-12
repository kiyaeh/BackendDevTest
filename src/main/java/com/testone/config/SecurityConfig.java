package com.testone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Allow Swagger URLs
                        .requestMatchers("/users/**").permitAll() // Allow access to /users endpoints
                        .anyRequest().authenticated() // Require authentication for other endpoints
                )
                .httpBasic(httpBasic -> httpBasic.disable()) // Disable basic authentication
                .formLogin(formLogin -> formLogin.disable()); // Disable form-based authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Define an in-memory user (replace with your own user service if needed)
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin")) // Use encoded password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCrypt password encoder
        return new BCryptPasswordEncoder();
    }
}