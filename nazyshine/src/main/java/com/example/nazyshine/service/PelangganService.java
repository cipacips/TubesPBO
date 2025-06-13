package com.example.nazyshine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nazyshine.model.Pelanggan;
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

    public Pelanggan createPelanggan(Pelanggan pelanggan) {
        return pelangganRepository.save(pelanggan);
    }

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
        pelanggan.setUsername(updatedPelanggan.getUsername());
        pelanggan.setPassword(updatedPelanggan.getPassword());
        pelanggan.setNoHp(updatedPelanggan.getNoHp());
        return pelangganRepository.save(pelanggan);
    }

    public void deletePelanggan(Integer id) {
        pelangganRepository.deleteById(id);
    }
}
