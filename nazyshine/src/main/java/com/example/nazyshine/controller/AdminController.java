package com.example.nazyshine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nazyshine.model.Admin;
import com.example.nazyshine.service.AdminService;

/**
 * PegawaiController is a REST controller that handles CRUD operations for the Pegawai entity.
 * It provides endpoints for creating, reading, updating, and deleting Pegawai records.
 */
@RestController
@RequestMapping("/api/pegawai")
public class AdminController {

    @Autowired
    private AdminService pegawaiService;

    /**
     * Creates a new Pegawai record.
     * * @param pegawai the Pegawai object to be created.
     * @return a ResponseEntity containing the created Pegawai object.
     */
    @PostMapping
    public ResponseEntity<Admin> createPegawai(@RequestBody Admin pegawai) {
        return ResponseEntity.ok(pegawaiService.createPegawai(pegawai));
    }

    /**
     * Retrieves all Pegawai records.
     * * @return a ResponseEntity containing a list of all Pegawai records.
     */
    @GetMapping
    public ResponseEntity<List<Admin>> getAllPegawai() {
        return ResponseEntity.ok(pegawaiService.getAllPegawai());
    }

    /**
     * Retrieves a Pegawai record by its ID.
     * * @param id the ID of the Pegawai record to retrieve.
     * @return a ResponseEntity containing the Pegawai record with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getPegawaiById(@PathVariable Long id) {
        return ResponseEntity.ok(pegawaiService.getPegawaiById(id));
    }

    /**
     * Updates an existing Pegawai record.
     * * @param id the ID of the Pegawai record to update.
     * @param pegawai the updated Pegawai object.
     * @return a ResponseEntity containing the updated Pegawai record.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updatePegawai(@PathVariable Long id, @RequestBody Admin pegawai) {
        return ResponseEntity.ok(pegawaiService.updatePegawai(id, pegawai));
    }

    /**
     * Deletes a Pegawai record by its ID.
     * * @param id the ID of the Pegawai record to delete.
     * @return a ResponseEntity with no content after the Pegawai record is deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePegawai(@PathVariable Long id) {
        pegawaiService.deletePegawai(id);
        return ResponseEntity.noContent().build();
    }
}