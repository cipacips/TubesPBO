package com.example.nazyshine.model; // Changed package name

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table; // Added @Table for explicit mapping

/**
 * Represents the Pegawai (Employee) entity in the Nazyshine application.
 *
 * <p>This class extends the {@link User} class to inherit common user properties,
 * while adding specific attributes for the Pegawai entity such as NIP (Employee Identification Number)
 * and position.</p>
 */
@Entity
@Table(name = "pegawai") // Explicitly setting table name
public class Pegawai extends User { // Changed class name from Laa to Pegawai

    /**
     * Unique ID for each Pegawai entity.
     *
     * <p>This ID is automatically generated using the {@code GenerationType.IDENTITY} strategy.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Employee Identification Number (NIP) for the Pegawai.
     */
    private String nip;

    /**
     * Position or role of the Pegawai.
     */
    private String posisi;

    /**
     * Returns the unique ID of the Pegawai entity.
     *
     * @return The unique ID of the Pegawai.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID for the Pegawai entity.
     *
     * @param id The unique ID to be assigned to the Pegawai entity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the Employee Identification Number (NIP) of the Pegawai.
     *
     * @return The NIP of the Pegawai.
     */
    public String getNip() {
        return nip;
    }

    /**
     * Sets the Employee Identification Number (NIP) for the Pegawai.
     *
     * @param nip The new NIP for the Pegawai entity.
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * Returns the position or role of the Pegawai.
     *
     * @return The position or role of the Pegawai.
     */
    public String getPosisi() {
        return posisi;
    }

    /**
     * Sets the position or role for the Pegawai.
     *
     * @param posisi The new position or role for the Pegawai entity.
     */
    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }
}