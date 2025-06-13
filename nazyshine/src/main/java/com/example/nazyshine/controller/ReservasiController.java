// src/main/java/com/example/nazyshine/controller/ReservasiController.java
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
import org.springframework.web.bind.annotation.RequestParam; // Used for request parameters
import org.springframework.web.bind.annotation.RestController;

import com.example.nazyshine.model.Reservasi;
import com.example.nazyshine.model.Pelanggan; // To get customer's reservations
import com.example.nazyshine.model.StatusReservasi; // To update reservation status
import com.example.nazyshine.service.ReservasiService;
import com.example.nazyshine.service.PelangganService; // To retrieve customer for filtering reservations

/**
 * ReservasiController is a REST controller that handles operations for the Reservasi entity.
 * It provides endpoints for creating, retrieving, updating status, and deleting reservations.
 */
@RestController
@RequestMapping("/api/reservasi")
public class ReservasiController {

    @Autowired
    private ReservasiService reservasiService;

    @Autowired
    private PelangganService pelangganService; // Injeksi PelangganService untuk mendapatkan data pelanggan

    /**
     * Creates a new reservation by assigning a Layanan to a Pelanggan.
     * @param customerId ID of the customer making the reservation.
     * @param layananId ID of the service being reserved.
     * @return a ResponseEntity containing the created Reservasi object and HTTP status 201 Created.
     */
    @PostMapping("/create")
    public ResponseEntity<Reservasi> createReservation(
            @RequestParam Integer customerId, // Customer ID is Integer
            @RequestParam Long layananId) {   // Layanan ID is Long
        Reservasi createdReservasi = reservasiService.assignLayananToCustomer(customerId, layananId);
        return new ResponseEntity<>(createdReservasi, HttpStatus.CREATED);
    }

    /**
     * Retrieves a reservation by its ID.
     * @param id the ID of the reservation to retrieve.
     * @return a ResponseEntity containing the Reservasi record with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reservasi> getReservationById(@PathVariable Long id) { // ID type is consistent with Reservasi.id (Long)
        return ResponseEntity.ok(reservasiService.getById(id));
    }

    /**
     * Retrieves all reservations for a specific customer.
     * @param customerId ID of the customer whose reservations are to be retrieved.
     * @return a ResponseEntity containing a list of Reservasi for the specified customer.
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Reservasi>> getReservationsByCustomer(@PathVariable Integer customerId) {
        Pelanggan customer = pelangganService.getPelangganById(customerId); // Retrieve the customer first
        List<Reservasi> customerReservations = reservasiService.findByCustomer(customer);
        return ResponseEntity.ok(customerReservations);
    }

    /**
     * Updates the status of an existing reservation.
     * @param id the ID of the reservation to update.
     * @param newStatus the new status for the reservation (e.g., PENDING, DIKONFIRMASI, SELESAI).
     * @return a ResponseEntity containing the updated Reservasi object.
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<Reservasi> updateReservationStatus(
            @PathVariable Long id,
            @RequestParam StatusReservasi newStatus) {
        Reservasi reservasi = reservasiService.getById(id);
        reservasi.ubahStatus(newStatus); // Use the method from the model
        Reservasi updatedReservasi = reservasiService.save(reservasi); // Save the updated reservation
        return ResponseEntity.ok(updatedReservasi);
    }

    /**
     * Deletes a reservation based on customer ID and Layanan ID.
     * @param customerId ID of the customer associated with the reservation.
     * @param layananId ID of the service associated with the reservation.
     * @return a ResponseEntity with no content after the reservation is deleted.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteReservationByParams(
            @RequestParam Integer customerId,
            @RequestParam Long layananId) {
        reservasiService.deleteReservasiByParams(customerId, layananId);
        return ResponseEntity.noContent().build();
    }
}