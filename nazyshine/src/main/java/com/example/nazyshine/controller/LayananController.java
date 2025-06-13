// src/main/java/com/example/nazyshine/controller/LayananController.java
package com.example.nazyshine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nazyshine.model.Layanan;
import com.example.nazyshine.service.LayananService;

/**
 * REST controller for managing Layanan data.
 *
 * <p>This controller provides APIs for CRUD (Create, Read, Update, Delete)
 * operations on the Layanan entity.</p>
 */
@RestController
@RequestMapping("/api/layanan")
public class LayananController {

    @Autowired
    private LayananService layananService;

    /**
     * Adds a new Layanan record.
     *
     * @param layanan the Layanan object to be created
     * @return ResponseEntity containing the created Layanan and HTTP status 201 Created.
     */
    @PostMapping
    public ResponseEntity<Layanan> createLayanan(@RequestBody Layanan layanan) {
        Layanan createdLayanan = layananService.createLayanan(layanan);
        return new ResponseEntity<>(createdLayanan, HttpStatus.CREATED);
    }

    /**
     * Retrieves all Layanan records.
     *
     * @return ResponseEntity containing a list of all Layanan
     */
    @GetMapping
    public ResponseEntity<List<Layanan>> getAllLayanan() {
        List<Layanan> layananList = layananService.getAllLayanan();
        return ResponseEntity.ok(layananList);
    }

    /**
     * Retrieves Layanan data by ID.
     *
     * @param id the ID of the Layanan to be retrieved
     * @return ResponseEntity containing the Layanan with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Layanan> getLayananById(@PathVariable Long id) { // ID type is consistent with Layanan.id (Long)
        Layanan layanan = layananService.getLayananById(id);
        return ResponseEntity.ok(layanan);
    }

    /**
     * Updates Layanan data by ID.
     *
     * @param id the ID of the Layanan to be updated
     * @param layanan the new Layanan data to replace the old data
     * @return ResponseEntity containing the updated Layanan.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Layanan> updateLayanan(@PathVariable Long id, @RequestBody Layanan layanan) { // ID type is consistent
        return ResponseEntity.ok(layananService.updateLayanan(id, layanan));
    }

    /**
     * Deletes Layanan data by ID.
     *
     * @param id the ID of the Layanan to be deleted
     * @return ResponseEntity with no content (204 No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLayanan(@PathVariable Long id) { // ID type is consistent
        layananService.deleteLayanan(id);
        return ResponseEntity.noContent().build();
    }
}