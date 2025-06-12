package com.example.nazyshine.service; // Changed package name

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nazyshine.model.Customer; // Changed model import
import com.example.nazyshine.model.Layanan; // Changed model import
import com.example.nazyshine.model.Reservasi; // Changed model import
import com.example.nazyshine.repository.LayananRepository; // Changed repository import

/**
 * Service layer for managing {@link Layanan} entities.
 *
 * <p>This service provides business logic for creating, retrieving, updating, and deleting {@link Layanan}
 * entities. It also handles relationships between {@link Layanan} and {@link Customer} entities.</p>
 */
@Service
public class LayananService { // Changed class name

    @Autowired
    private LayananRepository layananRepository; // Changed repository name

    /**
     * Creates a new {@link Layanan} entity.
     *
     * @param layanan The {@link Layanan} entity to be created.
     * @return The created {@link Layanan} entity.
     */
    public Layanan createLayanan(Layanan layanan) { // Changed method name and parameter
        return layananRepository.save(layanan);
    }

    /**
     * Retrieves all {@link Layanan} entities.
     *
     * @return A list of all {@link Layanan} entities.
     */
    public List<Layanan> getAllLayanan() { // Changed method name
        return layananRepository.findAll();
    }

    /**
     * Retrieves a {@link Layanan} entity by its ID.
     *
     * @param id The ID of the {@link Layanan} entity to retrieve.
     * @return The {@link Layanan} entity with the given ID.
     * @throws RuntimeException if no {@link Layanan} is found for the provided ID.
     */
    public Layanan getLayananById(Long id) { // Changed method name
        return layananRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Layanan not found with ID: " + id)); // Changed error message
    }

    /**
     * Retrieves a {@link Layanan} entity along with its associated {@link Customer} entities.
     *
     * <p>This method retrieves the {@link Layanan} entity and populates it with a list of
     * {@link Customer} entities that are associated with this {@link Layanan} through the
     * {@link Reservasi} relationship.</p>
     *
     * @param id The ID of the {@link Layanan} entity to retrieve.
     * @return The {@link Layanan} entity with associated {@link Customer} entities.
     * @throws RuntimeException if no {@link Layanan} is found for the provided ID.
     */
    public Layanan getLayananWithDetails(Long id) { // Changed method name
        Layanan layanan = layananRepository.findById(id) // Changed variable name
                .orElseThrow(() -> new RuntimeException("Layanan not found with ID: " + id)); // Changed error message

        // Retrieve all customers related to this layanan
        List<Customer> customerList = layanan.getReservasi().stream() // Changed method name from getMataKuliahMahasiswa to getReservasi
                .map(Reservasi::getCustomer) // Changed getMahasiswa to getCustomer
                .collect(Collectors.toList());

        // Set the related customer list into layanan
        layanan.setCustomer(customerList); // Changed setMahasiswa to setCustomer

        return layanan;
    }

    /**
     * Updates an existing {@link Layanan} entity.
     *
     * @param id The ID of the {@link Layanan} entity to update.
     * @param updatedLayanan The {@link Layanan} entity containing the updated values.
     * @return The updated {@link Layanan} entity.
     * @throws RuntimeException if the {@link Layanan} entity is not found for the provided ID.
     */
    public Layanan updateLayanan(Long id, Layanan updatedLayanan) { // Changed method name and parameter
        Layanan layanan = getLayananById(id); // Changed variable name
        layanan.setNama(updatedLayanan.getNama());
        layanan.setKode(updatedLayanan.getKode());
        layanan.setSks(updatedLayanan.getSks());
        return layananRepository.save(layanan);
    }

    /**
     * Deletes a {@link Layanan} entity by its ID.
     *
     * @param id The ID of the {@link Layanan} entity to delete.
     * @throws RuntimeException if the {@link Layanan} entity is not found for the provided ID.
     */
    public void deleteLayanan(Long id) { // Changed method name
        layananRepository.deleteById(id);
    }
}