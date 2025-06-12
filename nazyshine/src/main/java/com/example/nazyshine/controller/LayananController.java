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

import com.example.nazyshine.model.Layanan; // Changed from MataKuliah
import com.example.nazyshine.service.LayananService; // Changed from MataKuliahService

/**
 * REST controller for managing Layanan data.
 *
 * <p>This controller provides APIs for CRUD (Create, Read, Update, Delete)
 * operations on the Layanan entity.</p>
 */
@RestController
@RequestMapping("/api/layanan") // Changed endpoint path
public class LayananController {

    @Autowired
    private LayananService layananService; // Changed service reference

    /**
     * Adds a new Layanan record.
     *
     * @param layanan the Layanan object to be created
     * @return ResponseEntity containing the created Layanan
     */
    @PostMapping
    public ResponseEntity<Layanan> createLayanan(@RequestBody Layanan layanan) { // Changed method signature
        return ResponseEntity.ok(layananService.createLayanan(layanan)); // Changed service method call
    }

    /**
     * Retrieves all Layanan records.
     *
     * @return ResponseEntity containing a list of all Layanan
     */
    @GetMapping
    public ResponseEntity<List<Layanan>> getAllLayanan() { // Changed method signature
        List<Layanan> layananList = layananService.getAllLayanan(); // Changed service method call and variable name
        System.out.println("Layanan Data: " + layananList); // Debugging
        return ResponseEntity.ok(layananList);
    }

    /**
     * Retrieves Layanan data by ID.
     *
     * @param id the ID of the Layanan to be retrieved
     * @return ResponseEntity containing the Layanan with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Layanan> getById(@PathVariable Long id) { // Changed method signature
        Layanan layananWithDetails = layananService.getLayananWithDetails(id); // Changed service method call and variable name
        return ResponseEntity.ok(layananWithDetails);
    }

    /**
     * Updates Layanan data by ID.
     *
     * @param id the ID of the Layanan to be updated
     * @param layanan the new Layanan data to replace the old data
     * @return ResponseEntity containing the updated Layanan
     */
    @PutMapping("/{id}")
    public ResponseEntity<Layanan> updateLayanan(@PathVariable Long id, @RequestBody Layanan layanan) { // Changed method signature
        return ResponseEntity.ok(layananService.updateLayanan(id, layanan)); // Changed service method call
    }

    /**
     * Deletes Layanan data by ID.
     *
     * @param id the ID of the Layanan to be deleted
     * @return ResponseEntity with no content (204 No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLayanan(@PathVariable Long id) { // Changed method signature
        layananService.deleteLayanan(id); // Changed service method call
        return ResponseEntity.noContent().build();
    }
}