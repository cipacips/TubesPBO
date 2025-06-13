package com.example.nazyshine; // Pastikan package ini sesuai dengan lokasi file Anda

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "adminpassword"; // <--- GANTI DENGAN PASSWORD YANG ANDA INGINKAN!
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Encoded password for '" + rawPassword + "': " + encodedPassword);
    }
}