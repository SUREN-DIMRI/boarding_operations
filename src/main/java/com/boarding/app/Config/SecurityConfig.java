package com.boarding.app.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.boarding.app.Entity.User;
import com.boarding.app.Service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(csrf -> csrf.disable()) // Disable CSRF for testing in Postman
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/api/auth/login", "/api/auth/register","/api/users/create").permitAll() // Public endpoints
    //             .anyRequest().authenticated() // Secure all other endpoints
    //         )
    //         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Use JWT (Stateless)
    //         .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic (Use JWT later)

    //     return http.build();
    // }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/users/create" , "/api/users/delete/{id}" , "/api/users/update/{id}").permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // @Bean
    // public AuthenticationManager authenticationManager() {
    //     DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    //     authProvider.setUserDetailsService(username -> {
    //         com.boarding.app.Entity.User user = userService.findByUsername(username)
    //             .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    //         String role = userService.getUserRole(user);
    //         UserDetails userDetails = User.withUsername(user.getUsername())
    //             .password(user.getPassword())
    //             .roles(role.replace("ROLE_", "")) // Convert "ROLE_ADMIN" -> "ADMIN"
    //             .build();

    //         return userDetails;
    //     });
    //     authProvider.setPasswordEncoder(passwordEncoder());
    //     return new ProviderManager(authProvider);
    // }
}
