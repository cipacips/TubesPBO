package com.example.nazyshine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nazyshine.model.Reservasi; // Changed from MataKuliahMahasiswa
import com.example.nazyshine.repository.ReservasiRepository; // Changed from MataKuliahMahasiswaRepository
import com.example.nazyshine.service.ReservasiService; // Changed from MataKuliahMahasiswaService

/**
 * REST controller for managing the relationship between Customers and Layanan (services).
 *
 * <p>This controller provides APIs for the following operations:
 * - Assigning a Customer to a Layanan
 * - Deleting the relationship between a Customer and a Layanan
 * - Retrieving details of the relationship
 * - Updating a Customer's grades for a specific Layanan</p>
 */
@RestController
@RequestMapping("/api/reservasi") // Changed endpoint path
public class ReservasiController {

    @Autowired
    private ReservasiService service; // Changed service reference

    @Autowired
    private ReservasiRepository reservasiRepository; // Changed repository reference

    /**
     * Assigns a Customer to a Layanan.
     *
     * @param customerId ID of the Customer
     * @param layananId ID of the Layanan
     * @return ResponseEntity containing the created Reservasi object
     */
    @PostMapping("/assign")
    public ResponseEntity<Reservasi> assignLayananToCustomer( // Changed method name
            @RequestParam Long customerId,
            @RequestParam Long layananId) { // Changed parameter name
        System.out.println("Assigning Customer ID: " + customerId + " to Layanan ID: " + layananId);
        Reservasi assigned = service.assignLayananToCustomer(customerId, layananId); // Changed service method call
        return ResponseEntity.ok(assigned);
    }

    /**
     * Deletes the relationship between a Customer and a Layanan based on their IDs.
     *
     * @param customerId ID of the Customer
     * @param layananId ID of the Layanan
     * @return ResponseEntity with no content (204 No Content)
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteReservasiByParams( // Changed method name
            @RequestParam Long customerId,
            @RequestParam Long layananId) { // Changed parameter name
        service.deleteReservasiByParams(customerId, layananId); // Changed service method call
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the relationship details between a Customer and a Layanan by its ID.
     *
     * @param id ID of the Reservasi relationship
     * @return ResponseEntity containing the Reservasi object with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reservasi> getReservasiById(@PathVariable Long id) { // Changed method signature
        Reservasi reservasi = service.getById(id); // Changed variable name
        return ResponseEntity.ok(reservasi);
    }

    /**
     * Updates a Customer's grades for a specific Layanan.
     *
     * @param id ID of the Reservasi relationship
     * @param uts UTS grade
     * @param uas UAS grade
     * @param kuis Quiz grade
     * @return ResponseEntity containing a confirmation message that the grades have been updated
     */
    @PostMapping("/update-nilai")
    public ResponseEntity<?> updateNilai(@RequestParam Long id, @RequestParam int uts, @RequestParam int uas, @RequestParam int kuis) {
        Reservasi reservasi = reservasiRepository.findById(id) // Changed variable name and repository
                .orElseThrow(() -> new RuntimeException("Data not found"));

        reservasi.setUts(uts);
        reservasi.setUas(uas);
        reservasi.setKuis(kuis);

        // Automatically calculate total score and grade (assuming these are still relevant for "Layanan")
        reservasiRepository.save(reservasi);
        return ResponseEntity.ok("Score Updated !!.");
    }
}