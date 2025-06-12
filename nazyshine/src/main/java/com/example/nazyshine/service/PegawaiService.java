package com.example.nazyshine.service; // Changed package name

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nazyshine.model.Pegawai; // Changed model import
import com.example.nazyshine.repository.PegawaiRepository; // Changed repository import

/**
 * Service layer for managing {@link Pegawai} entities.
 *
 * <p>This service class provides business logic for creating, retrieving, updating, and deleting
 * {@link Pegawai} records in the database. It interacts with the {@link PegawaiRepository} for CRUD operations.</p>
 */
@Service
public class PegawaiService { // Changed class name

    @Autowired
    private PegawaiRepository pegawaiRepository; // Changed repository name

    /**
     * Creates a new {@link Pegawai} entity.
     *
     * <p>This method saves the provided {@link Pegawai} instance into the database.</p>
     *
     * @param pegawai The {@link Pegawai} entity to create.
     * @return The saved {@link Pegawai} entity.
     */
    public Pegawai createPegawai(Pegawai pegawai) { // Changed method name and parameter
        return pegawaiRepository.save(pegawai);
    }

    /**
     * Retrieves all {@link Pegawai} entities from the database.
     *
     * @return A list of all {@link Pegawai} entities.
     */
    public List<Pegawai> getAllPegawai() { // Changed method name
        return pegawaiRepository.findAll();
    }

    /**
     * Retrieves a {@link Pegawai} entity by its ID.
     *
     * <p>If no entity is found with the given ID, a {@link RuntimeException} is thrown.</p>
     *
     * @param id The ID of the {@link Pegawai} entity to retrieve.
     * @return The {@link Pegawai} entity with the given ID.
     * @throws RuntimeException if no {@link Pegawai} entity is found for the provided ID.
     */
    public Pegawai getPegawaiById(Long id) { // Changed method name
        return pegawaiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pegawai not found for ID: " + id)); // Changed error message
    }

    /**
     * Updates an existing {@link Pegawai} entity.
     *
     * <p>This method updates the properties of an existing {@link Pegawai} entity identified by its ID.
     * If the entity is not found, an exception is thrown. The updated data is saved to the database.</p>
     *
     * @param id The ID of the {@link Pegawai} entity to update.
     * @param updatedPegawai The updated {@link Pegawai} entity data.
     * @return The updated {@link Pegawai} entity.
     * @throws RuntimeException if no {@link Pegawai} entity is found for the provided ID.
     */
    public Pegawai updatePegawai(Long id, Pegawai updatedPegawai) { // Changed method name and parameter
        Pegawai pegawai = getPegawaiById(id); // Changed variable name
        pegawai.setUsername(updatedPegawai.getUsername());
        pegawai.setPassword(updatedPegawai.getPassword());
        pegawai.setNip(updatedPegawai.getNip());
        pegawai.setPosisi(updatedPegawai.getPosisi());
        return pegawaiRepository.save(pegawai);
    }

    /**
     * Deletes a {@link Pegawai} entity by its ID.
     *
     * <p>This method removes the {@link Pegawai} entity with the specified ID from the database.</p>
     *
     * @param id The ID of the {@link Pegawai} entity to delete.
     */
    public void deletePegawai(Long id) { // Changed method name
        pegawaiRepository.deleteById(id);
    }
}