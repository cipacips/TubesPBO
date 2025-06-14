package com.example.nazyshine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nazyshine.model.Pelanggan;
import com.example.nazyshine.service.PelangganService;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {

    @Autowired
    private PelangganService pelangganService;

    // --- FUNGSI CREATE (REGISTER) DIHAPUS DARI CONTROLLER INI KARENA TIDAK ADA LAGI REGISTRASI PUBLIK ---
    // Jika Anda ingin Admin bisa membuat Pelanggan, Anda perlu menambahkan endpoint baru
    // yang terproteksi (misalnya POST ke /api/admin/pelanggan) atau menyesuaikan metode update.

    /**
     * Retrieves all Pelanggan records.
     * @return a ResponseEntity containing a list of all Pelanggan records.
     */
    @GetMapping
    public ResponseEntity<List<Pelanggan>> getAllPelanggan() {
        return ResponseEntity.ok(pelangganService.getAllPelanggan());
    }

    /**
     * Retrieves a Pelanggan record by its ID.
     * @param id the ID of the Pelanggan record to retrieve.
     * @return a ResponseEntity containing the Pelanggan record with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pelanggan> getPelangganById(@PathVariable Integer id) {
        return ResponseEntity.ok(pelangganService.getPelangganById(id));
    }

    /**
     * Updates an existing Pelanggan record.
     * @param id the ID of the Pelanggan record to update.
     * @param pelanggan the updated Pelanggan object.
     * @return a ResponseEntity containing the updated Pelanggan record.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pelanggan> updatePelanggan(@PathVariable Integer id, @RequestBody Pelanggan pelanggan) {
        return ResponseEntity.ok(pelangganService.updatePelanggan(id, pelanggan));
    }

    /**
     * Deletes a Pelanggan record by its ID.
     * @param id the ID of the Pelanggan record to delete.
     * @return a ResponseEntity with no content after the Pelanggan record is deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelanggan(@PathVariable Integer id) {
        pelangganService.deletePelanggan(id);
        return ResponseEntity.noContent().build();
    }
}