package com.example.nazyshine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.nazyshine.model.Pelanggan;
import com.example.nazyshine.model.Role; // Tetap import Role karena dipakai di tempat lain
import com.example.nazyshine.repository.PelangganRepository;

@Service
public class PelangganService {

    @Autowired
    private PelangganRepository pelangganRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // --- FUNGSI CREATE (REGISTER) DIHAPUS DARI SERVICE INI ---
    // Karena tidak ada lagi registrasi publik.
    // Jika Anda ingin membuat metode untuk ADMIN menambahkan pelanggan baru,
    // Anda bisa membuat metode terpisah seperti:
    /*
    public Pelanggan createPelangganByAdmin(Pelanggan pelanggan) {
        pelanggan.setPassword(passwordEncoder.encode(pelanggan.getPassword()));
        pelanggan.setRole(Role.PELANGGAN); // Pelanggan baru yang dibuat admin tetap ROLE_PELANGGAN
        return pelangganRepository.save(pelanggan);
    }
    */

    public List<Pelanggan> getAllPelanggan() {
        return pelangganRepository.findAll();
    }

    public Pelanggan getPelangganById(Integer id) {
        return pelangganRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pelanggan tidak ditemukan dengan ID: " + id));
    }

    public Pelanggan getPelangganByUsername(String username) {
        return pelangganRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Pelanggan tidak ditemukan dengan username: " + username));
    }

    public Pelanggan updatePelanggan(Integer id, Pelanggan updatedPelanggan) {
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

    public void deletePelanggan(Integer id) {
        pelangganRepository.deleteById(id);
    }
}