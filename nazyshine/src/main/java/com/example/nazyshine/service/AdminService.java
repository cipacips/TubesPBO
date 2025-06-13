// src/main/java/com/example/nazyshine/service/AdminService.java
package com.example.nazyshine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder; // Import PasswordEncoder

import com.example.nazyshine.model.Admin;
import com.example.nazyshine.model.Role; // Import Role
import com.example.nazyshine.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository; // Nama variabel disesuaikan

    @Autowired
    private PasswordEncoder passwordEncoder; // Injeksi PasswordEncoder

    public Admin createAdmin(Admin admin) { // Nama metode disesuaikan
        admin.setPassword(passwordEncoder.encode(admin.getPassword())); // Encode password sebelum menyimpan
        admin.setRole(Role.ADMIN); // Set role secara default saat membuat Admin
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() { // Nama metode disesuaikan
        return adminRepository.findAll();
    }

    public Admin getAdminById(Integer id) { // Nama metode disesuaikan, ID adalah Integer, konsisten
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found for ID: " + id)); // Pesan disesuaikan
    }

    public Admin updateAdmin(Integer id, Admin updatedAdmin) { // Nama metode disesuaikan, ID adalah Integer, konsisten
        Admin admin = getAdminById(id);
        admin.setNama(updatedAdmin.getNama());
        admin.setEmail(updatedAdmin.getEmail());
        admin.setUsername(updatedAdmin.getUsername());
        
        // Hanya encode password jika password baru diberikan/berubah
        if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
            admin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
        }
        admin.setShift(updatedAdmin.getShift()); // Perbarui properti shift
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Integer id) { // Nama metode disesuaikan, ID adalah Integer, konsisten
        adminRepository.deleteById(id);
    }
}