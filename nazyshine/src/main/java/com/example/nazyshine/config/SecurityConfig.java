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
                .csrf(csrf -> csrf.disable()) // Nonaktifkan CSRF untuk sementara (untuk kemudahan debugging)
                .authorizeHttpRequests(auth -> auth
                        // === Public Endpoints (Accessible by anyone, including unauthenticated users) ===
                        // Izinkan akses publik ke root, login, dan resource statis.
                        // /register dan /register.html DIHAPUS dari public, kecuali Anda masih ada JSP-nya
                        .requestMatchers(
                                "/", // Root path
                                "/login", // Jika ada controller mapping untuk /login
                                "/login.html", // Halaman login JSP/HTML
                                "/resources/**", // Folder resources statis
                                "/static/**", // Folder static (jika ada)
                                "/css/**", // Folder css di resources/static/css/
                                "/js/**", // Folder js di resources/static/js/
                                "/test-user", // Endpoint untuk pengujian (jika ada)
                                "/api/layanan", // API publik untuk melihat daftar layanan
                                "/api/layanan/{id}" // API publik untuk melihat detail layanan
                        ).permitAll()
                        // Endpoint API untuk registrasi pelanggan DIHAPUS dari public karena fungsi register dihilangkan
                        // .requestMatchers(HttpMethod.POST, "/api/pelanggan/register").permitAll()

                        // Akses ke dashboard dan API tertentu yang memerlukan autentikasi tapi belum spesifik role
                        .requestMatchers("/dashboard").authenticated() // User harus login untuk masuk ke /dashboard
                        .requestMatchers("/api/reservasi/customer/**").authenticated() // Pelanggan atau Admin bisa lihat riwayat reservasi mereka sendiri


                        // === PELANGGAN Specific Access ===
                        .requestMatchers("/dashboard_customer/**").hasRole("PELANGGAN")
                        .requestMatchers(HttpMethod.POST, "/api/reservasi/create").hasRole("PELANGGAN")


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


                        // Semua request lainnya harus diautentikasi (catch-all rule)
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login.html") // Menentukan halaman login kustom
                        .loginProcessingUrl("/login") // URL untuk memproses POST login (tidak perlu JSP/Controller untuk ini)
                        .defaultSuccessUrl("/dashboard", true) // Redirect setelah login sukses (ke View Controller yang menentukan dashboard)
                        .failureHandler((request, response, exception) -> { // Handler kustom untuk kegagalan login
                            System.out.println("Login failed: " + exception.getMessage()); // Cetak pesan error di konsol
                            response.sendRedirect("/login.html?error"); // Redirect kembali ke halaman login dengan parameter error
                        })
                        .permitAll() // Izinkan siapa saja mengakses form login dan proses login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL untuk proses logout
                        .logoutSuccessUrl("/login.html") // Redirect setelah logout sukses
                        .permitAll() // Izinkan siapa saja untuk logout
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