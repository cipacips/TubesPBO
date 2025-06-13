// src/main/java/com/example/nazyshine/config/SecurityConfig.java
package com.example.nazyshine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.nazyshine.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // === Public Endpoints (Accessible by anyone, including unauthenticated users) ===
                        // Pastikan /login.html ada di sini!
                        .requestMatchers("/login", "/login.html", "/resources/**", "/static/**", "/test-user", "/dashboard", "/register").permitAll()
                        .requestMatchers("/api/pelanggan/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/layanan").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/layanan/{id}").permitAll()

                        // === PELANGGAN Specific Access ===
                        .requestMatchers("/dashboard_customer/**").hasRole("PELANGGAN")
                        .requestMatchers(HttpMethod.POST, "/api/reservasi/create").hasRole("PELANGGAN")
                        .requestMatchers(HttpMethod.GET, "/api/reservasi/customer/**").hasAnyRole("PELANGGAN", "ADMIN")

                        // === ADMIN Specific Access ===
                        .requestMatchers("/dashboard_admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/layanan").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/layanan/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/layanan/**").hasRole("ADMIN")
                        .requestMatchers("/api/pelanggan").hasRole("ADMIN")
                        .requestMatchers("/api/pelanggan/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/pelanggan/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/pelanggan/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/reservasi/{id}/status").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/reservasi/delete").hasRole("ADMIN")

                        // === Any Other Request (Requires Authentication) ===
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureHandler((request, response, exception) -> {
                            System.out.println("Login failed: " + exception.getMessage());
                            response.sendRedirect("/login.html?error");
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login.html")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}