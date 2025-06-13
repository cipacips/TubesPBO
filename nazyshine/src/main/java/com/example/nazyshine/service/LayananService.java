// src/main/java/com/example/nazyshine/service/LayananService.java
package com.example.nazyshine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nazyshine.model.Layanan;
import com.example.nazyshine.repository.LayananRepository;

/**
 * Service layer for managing {@link Layanan} entities.
 */
@Service
public class LayananService {

    @Autowired
    private LayananRepository layananRepository;

    /**
     * Creates a new {@link Layanan} entity.
     */
    public Layanan createLayanan(Layanan layanan) {
        return layananRepository.save(layanan);
    }

    /**
     * Retrieves all {@link Layanan} entities.
     */
    public List<Layanan> getAllLayanan() {
        return layananRepository.findAll();
    }

    /**
     * Retrieves a {@link Layanan} entity by its ID.
     */
    public Layanan getLayananById(Long id) { // Tipe ID adalah Long, konsisten
        return layananRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Layanan not found with ID: " + id));
    }

    /**
     * Updates an existing {@link Layanan} entity.
     */
    public Layanan updateLayanan(Long id, Layanan updatedLayanan) { // Tipe ID adalah Long, konsisten
        Layanan layanan = getLayananById(id);
        layanan.setNama(updatedLayanan.getNama());
        layanan.setHarga(updatedLayanan.getHarga());
        layanan.setDurasi(updatedLayanan.getDurasi()); // Menambahkan update durasi
        return layananRepository.save(layanan);
    }

    /**
     * Deletes a {@link Layanan} entity by its ID.
     */
    public void deleteLayanan(Long id) { // Tipe ID adalah Long, konsisten
        layananRepository.deleteById(id);
    }
}