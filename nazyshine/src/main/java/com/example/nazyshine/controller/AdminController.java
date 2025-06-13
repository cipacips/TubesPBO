// src/main/java/com/example/nazyshine/controller/AdminController.java
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

import com.example.nazyshine.model.Admin;
import com.example.nazyshine.service.AdminService;

/**
 * AdminController is a REST controller that handles CRUD operations for the Admin entity.
 * It provides endpoints for creating, reading, updating, and deleting Admin records.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService; // Variable name is now consistent

    /**
     * Creates a new Admin record.
     * @param admin the Admin object to be created.
     * @return a ResponseEntity containing the created Admin object and HTTP status 201 Created.
     */
    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin createdAdmin = adminService.createAdmin(admin); // Method call is now consistent
        return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
    }

    /**
     * Retrieves all Admin records.
     * @return a ResponseEntity containing a list of all Admin records.
     */
    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() { // Method name is now consistent
        return ResponseEntity.ok(adminService.getAllAdmins()); // Method call is now consistent
    }

    /**
     * Retrieves an Admin record by its ID.
     * @param id the ID of the Admin record to retrieve.
     * @return a ResponseEntity containing the Admin record with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer id) { // ID type is consistent with Admin.id (Integer)
        return ResponseEntity.ok(adminService.getAdminById(id)); // Method call is now consistent
    }

    /**
     * Updates an existing Admin record.
     * @param id the ID of the Admin record to update.
     * @param admin the updated Admin object.
     * @return a ResponseEntity containing the updated Admin record.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) { // ID type and parameter name are consistent
        return ResponseEntity.ok(adminService.updateAdmin(id, admin)); // Method call is now consistent
    }

    /**
     * Deletes an Admin record by its ID.
     * @param id the ID of the Admin record to delete.
     * @return a ResponseEntity with no content after the Admin record is deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Integer id) { // ID type is consistent
        adminService.deleteAdmin(id); // Method call is now consistent
        return ResponseEntity.noContent().build();
    }
}