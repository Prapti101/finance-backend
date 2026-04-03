package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .httpBasic(); // 🔥 enables username/password popup

        return http.build();
    }

    // 🔹 DUMMY USERS FOR TESTING
    @Bean
    public UserDetailsService users() {

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123")
                .roles("ADMIN")
                .build();

        UserDetails analyst = User.builder()
                .username("analyst")
                .password("{noop}analyst123")
                .roles("ANALYST")
                .build();

        UserDetails viewer = User.builder()
                .username("viewer")
                .password("{noop}viewer123")
                .roles("VIEWER")
                .build();

        return new InMemoryUserDetailsManager(admin, analyst, viewer);
    }
}