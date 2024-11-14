package com.project.jfb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/tickets/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        http
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        UserDetails user = userBuilder
                .username("user")
                .password("password")
                .roles("ADMIN")
                .build();
        UserDetails user1 = userBuilder
                .username("user1")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, user1);
    }
}

