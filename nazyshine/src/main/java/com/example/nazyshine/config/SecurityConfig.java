// src/main/java/com/example/nazyshine/config/SecurityConfig.java
package com.example.nazyshine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Import HttpMethod
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.nazyshine.service.CustomUserDetailsService;

/**
 * SecurityConfig is the configuration class for setting up Spring Security in the application.
 * It defines the security rules for the application, including endpoint access control, login/logout behavior,
 * and authentication providers.
 */
@Configuration
public class SecurityConfig {

    /**
     * Custom service to retrieve user details for authentication.
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object to configure security rules
     * @return a configured SecurityFilterChain
     * @throws Exception if any error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disables CSRF protection (for easier API testing, consider enabling for production with proper handling)
                .authorizeHttpRequests(auth -> auth
                        // === Public Endpoints (Accessible by anyone, including unauthenticated users) ===
                        .requestMatchers("/login", "/resources/**", "/static/**", "/test-user", "/dashboard").permitAll() // Added /dashboard to permitAll
                        .requestMatchers("/api/pelanggan/register").permitAll() // Endpoint for new customer registration
                        .requestMatchers(HttpMethod.GET, "/api/layanan").permitAll() // Allow anyone to view all services
                        .requestMatchers(HttpMethod.GET, "/api/layanan/{id}").permitAll() // Allow anyone to view a single service by ID

                        // === PELANGGAN Specific Access ===
                        .requestMatchers("/dashboard_customer/**").hasRole("PELANGGAN") // Dashboard for customers
                        .requestMatchers(HttpMethod.POST, "/api/reservasi/create").hasRole("PELANGGAN") // Customers can create reservations
                        .requestMatchers(HttpMethod.GET, "/api/reservasi/customer/**").hasAnyRole("PELANGGAN", "ADMIN") // Customers can see their own reservations, Admins can see all

                        // === ADMIN Specific Access (More specific rules first) ===
                        .requestMatchers("/dashboard_admin/**").hasRole("ADMIN") // Dashboard for admins
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // All CRUD operations for Admin entity
                        .requestMatchers(HttpMethod.POST, "/api/layanan").hasRole("ADMIN") // Admins can create services
                        .requestMatchers(HttpMethod.PUT, "/api/layanan/**").hasRole("ADMIN") // Admins can update services
                        .requestMatchers(HttpMethod.DELETE, "/api/layanan/**").hasRole("ADMIN") // Admins can delete services
                        .requestMatchers("/api/pelanggan").hasRole("ADMIN") // Admins can get all customers (GET /api/pelanggan)
                        .requestMatchers("/api/pelanggan/{id}").hasRole("ADMIN") // Admins can get a specific customer (GET /api/pelanggan/{id})
                        .requestMatchers(HttpMethod.PUT, "/api/pelanggan/**").hasRole("ADMIN") // Admins can update customers
                        .requestMatchers(HttpMethod.DELETE, "/api/pelanggan/**").hasRole("ADMIN") // Admins can delete customers
                        .requestMatchers(HttpMethod.PUT, "/api/reservasi/{id}/status").hasRole("ADMIN") // Admins can update reservation status
                        .requestMatchers(HttpMethod.DELETE, "/api/reservasi/delete").hasRole("ADMIN") // Admins can delete reservations by params

                        // === Any Other Request (Requires Authentication) ===
                        .anyRequest().authenticated() // All other requests not matched above require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login.html") // Custom login page (ensure this HTML file exists)
                        .loginProcessingUrl("/login") // URL for processing login (form action)
                        .defaultSuccessUrl("/dashboard", true) // Redirect after successful login (true ensures redirect even if originally trying to access a different protected URL)
                        .failureHandler((request, response, exception) -> {
                            System.out.println("Login failed: " + exception.getMessage()); // Log login failure
                            response.sendRedirect("/login.html?error"); // Redirect back to login page with an error parameter
                        })
                        .permitAll() // Allows access to the login page and related processing URL for all users
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL for logging out
                        .logoutSuccessUrl("/login.html") // Redirect after successful logout
                        .permitAll() // Allows access to the logout URL for all users
                );

        return http.build();
    }

    /**
     * Configures the AuthenticationManager bean to manage authentication.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configures a DaoAuthenticationProvider bean that uses the CustomUserDetailsService
     * and a PasswordEncoder for authentication.
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Configures a PasswordEncoder bean for encoding and verifying passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Secure password encoding
    }
}