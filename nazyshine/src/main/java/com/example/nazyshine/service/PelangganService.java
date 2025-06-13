// src/main/java/com/example/nazyshine/service/PelangganService.java
package com.example.nazyshine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder; // Import PasswordEncoder

import com.example.nazyshine.model.Pelanggan;
import com.example.nazyshine.model.Role; // Import Role
import com.example.nazyshine.repository.PelangganRepository;

/**
 * Service layer untuk mengelola entitas {@link Pelanggan}.
 *
 * <p>Kelas ini menyediakan logika bisnis untuk operasi CRUD pada data pelanggan.</p>
 */
@Service
public class PelangganService {

    @Autowired
    private PelangganRepository pelangganRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injeksi PasswordEncoder

    public Pelanggan createPelanggan(Pelanggan pelanggan) {
        pelanggan.setPassword(passwordEncoder.encode(pelanggan.getPassword())); // Encode password sebelum menyimpan
        pelanggan.setRole(Role.PELANGGAN); // Set role secara default saat membuat Pelanggan
        return pelangganRepository.save(pelanggan);
    }

    public List<Pelanggan> getAllPelanggan() {
        return pelangganRepository.findAll();
    }

    public Pelanggan getPelangganById(Integer id) { // ID adalah Integer, konsisten
        return pelangganRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pelanggan tidak ditemukan dengan ID: " + id));
    }

    public Pelanggan getPelangganByUsername(String username) {
        return pelangganRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Pelanggan tidak ditemukan dengan username: " + username));
    }

    public Pelanggan updatePelanggan(Integer id, Pelanggan updatedPelanggan) { // ID adalah Integer, konsisten
        Pelanggan pelanggan = getPelangganById(id);
        pelanggan.setNama(updatedPelanggan.getNama());
        pelanggan.setEmail(updatedPelanggan.getEmail());
        pelanggan.setUsername(updatedPelanggan.getUsername());
        
        // Hanya encode password jika password baru diberikan/berubah
        if (updatedPelanggan.getPassword() != null && !updatedPelanggan.getPassword().isEmpty()) {
            pelanggan.setPassword(passwordEncoder.encode(updatedPelanggan.getPassword()));
        }
        pelanggan.setNoHp(updatedPelanggan.getNoHp());
        return pelangganRepository.save(pelanggan);
    }

    public void deletePelanggan(Integer id) { // ID adalah Integer, konsisten
        pelangganRepository.deleteById(id);
    }
}