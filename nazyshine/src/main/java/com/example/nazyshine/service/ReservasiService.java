package com.example.nazyshine.service; // Changed package name

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nazyshine.model.Customer; // Changed model import
import com.example.nazyshine.model.Layanan; // Changed model import
import com.example.nazyshine.model.Reservasi; // Changed model import
import com.example.nazyshine.repository.CustomerRepository; // Changed repository import
import com.example.nazyshine.repository.ReservasiRepository; // Changed repository import
import com.example.nazyshine.repository.LayananRepository; // Changed repository import

/**
 * Service layer for managing the {@link Reservasi} relationship.
 *
 * <p>This service class provides business logic for managing the relationship between
 * {@link Customer} and {@link Layanan} entities. It handles assigning, removing, and retrieving
 * {@link Reservasi} instances from the database.</p>
 */
@Service
public class ReservasiService { // Changed class name

    @Autowired
    private ReservasiRepository reservasiRepository; // Changed repository name

    @Autowired
    private CustomerRepository customerRepository; // Changed repository name

    @Autowired
    private LayananRepository layananRepository; // Changed repository name

    /**
     * Assigns a {@link Layanan} to a {@link Customer}.
     *
     * <p>This method creates a new {@link Reservasi} relationship between the given
     * {@link Customer} and {@link Layanan} by their respective IDs.</p>
     *
     * @param customerId The ID of the {@link Customer} to assign.
     * @param layananId The ID of the {@link Layanan} to assign.
     * @return The saved {@link Reservasi} relationship.
     * @throws RuntimeException if either the {@link Customer} or {@link Layanan} is not found.
     */
    public Reservasi assignLayananToCustomer(Long customerId, Long layananId) { // Changed method name and parameters
        Customer customer = customerRepository.findById(customerId) // Changed variable and findById parameter
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId)); // Changed error message

        Layanan layanan = layananRepository.findById(layananId) // Changed variable and findById parameter
                .orElseThrow(() -> new RuntimeException("Layanan not found with ID: " + layananId)); // Changed error message

        Reservasi reservasi = new Reservasi(); // Changed variable name
        reservasi.setCustomer(customer); // Changed method name and parameter
        reservasi.setLayanan(layanan); // Changed method name and parameter

        return reservasiRepository.save(reservasi); // Changed variable name
    }

    /**
     * Deletes a {@link Reservasi} relationship based on the given {@link Customer} and
     * {@link Layanan} IDs.
     *
     * <p>This method removes the relationship between a {@link Customer} and a {@link Layanan}
     * if it exists in the database.</p>
     *
     * @param customerId The ID of the {@link Customer}.
     * @param layananId The ID of the {@link Layanan}.
     * @throws RuntimeException if the relationship is not found for the given IDs.
     */
    public void deleteReservasiByParams(Long customerId, Long layananId) { // Changed method name and parameters
        Reservasi relation = reservasiRepository.findByCustomerIdAndLayananId(customerId, layananId) // Changed variable, findBy method
                .orElseThrow(() -> new RuntimeException("Reservation not found for Customer ID: " + customerId + " and Layanan ID: " + layananId)); // Changed error message
        reservasiRepository.delete(relation);
    }

    /**
     * Retrieves a {@link Reservasi} entity by its ID.
     *
     * <p>If no {@link Reservasi} entity is found with the provided ID, a {@link RuntimeException}
     * is thrown.</p>
     *
     * @param id The ID of the {@link Reservasi} entity to retrieve.
     * @return The {@link Reservasi} entity with the given ID.
     * @throws RuntimeException if no {@link Reservasi} is found for the provided ID.
     */
    public Reservasi getById(Long id) { // Changed method name
        return reservasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservasi not found with ID: " + id)); // Changed error message
    }
}