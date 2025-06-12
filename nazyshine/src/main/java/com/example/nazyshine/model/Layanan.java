package com.example.nazyshine.model; // Changed package name

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table; // Added @Table for explicit mapping
import jakarta.persistence.Transient;


/**
 * Represents a service (Layanan) entity in the Nazyshine application.
 *
 * <p>This class contains attributes such as service name, code, duration/credits (sks), evaluation metrics,
 * and relationships with customers and their reservations for the service.</p>
 */
@Entity
@Table(name = "layanan") // Explicitly setting table name
public class Layanan { // Changed class name from MataKuliah to Layanan

    /**
     * The unique identifier for the Layanan entity.
     * Automatically generated using {@code GenerationType.IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the service.
     */
    private String nama;

    /**
     * The unique code of the service.
     */
    private String kode;

    /**
     * The number of units or credits (sks) assigned to the service,
     * which could represent duration, complexity, or cost.
     */
    private int sks; // Kept 'sks' but redefined its meaning for a service

    /**
     * The score of the Mid-Service Evaluation (UTS for analogy, could be renamed).
     */
    private int uts; // Kept 'uts' for generality in scoring, may need renaming later

    /**
     * The score of the Final Service Evaluation (UAS for analogy, could be renamed).
     */
    private int uas; // Kept 'uas' for generality in scoring, may need renaming later

    /**
     * The score of quick checks or minor evaluations (Kuis for analogy, could be renamed).
     */
    private int kuis; // Kept 'kuis' for generality in scoring, may need renaming later

    /**
     * The total evaluation score for the service.
     */
    private int total;

    /**
     * The overall rating or grade for the service (e.g., A, B, C for quality).
     */
    private String grade;

    /**
     * One-to-Many relationship with the {@link Reservasi} entity.
     *
     * <p>This property stores the list of customer reservations for the service.
     * It is ignored during JSON serialization to avoid cyclic references.</p>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "layanan", cascade = CascadeType.ALL) // Changed mappedBy to layanan, type to Reservasi
    private List<Reservasi> reservasi; // Changed type from MataKuliahMahasiswa to Reservasi

    /**
     * A transient property to store the list of customers enrolled in the service.
     *
     * <p>This property is used only at the application level and is not persisted in the database.</p>
     */
    @Transient
    private List<Customer> customer; // Changed type from Mahasiswa to Customer

    // Getters and Setters

    /**
     * Returns the unique ID of the service.
     *
     * @return The unique ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID for the service.
     *
     * @param id The unique ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the service.
     *
     * @return The service name.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Sets the name of the service.
     *
     * @param nama The service name to set.
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     * Returns the unique code of the service.
     *
     * @return The service code.
     */
    public String getKode() {
        return kode;
    }

    /**
     * Sets the unique code of the service.
     *
     * @param kode The service code to set.
     */
    public void setKode(String kode) {
        this.kode = kode;
    }

    /**
     * Returns the number of units or credits (sks) for the service.
     *
     * @return The number of units/credits.
     */
    public int getSks() {
        return sks;
    }

    /**
     * Sets the number of units or credits (sks) for the service.
     *
     * @param sks The number of units/credits to set.
     */
    public void setSks(int sks) {
        this.sks = sks;
    }

    /**
     * Returns the score of the Mid-Service Evaluation.
     *
     * @return The UTS score.
     */
    public int getUts() {
        return uts;
    }

    /**
     * Sets the score of the Mid-Service Evaluation.
     *
     * @param uts The UTS score to set.
     */
    public void setUts(int uts) {
        this.uts = uts;
    }

    /**
     * Returns the score of the Final Service Evaluation.
     *
     * @return The UAS score.
     */
    public int getUas() {
        return uas;
    }

    /**
     * Sets the score of the Final Service Evaluation.
     *
     * @param uas The UAS score to set.
     */
    public void setUas(int uas) {
        this.uas = uas;
    }

    /**
     * Returns the score of quick checks or minor evaluations.
     *
     * @return The quiz score.
     */
    public int getKuis() {
        return kuis;
    }

    /**
     * Sets the score of quick checks or minor evaluations.
     *
     * @param kuis The quiz score to set.
     */
    public void setKuis(int kuis) {
        this.kuis = kuis;
    }

    /**
     * Returns the total evaluation score for the service.
     *
     * @return The total score.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the total evaluation score for the service.
     *
     * @param total The total score to set.
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * Returns the overall rating or grade for the service.
     *
     * @return The grade.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Sets the grade for the service.
     *
     * @param grade The grade to set.
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Returns the list of {@link Reservasi} entities associated with the service.
     *
     * @return The list of Reservasi entities.
     */
    public List<Reservasi> getReservasi() { // Changed method name and return type
        return reservasi;
    }

    /**
     * Sets the list of {@link Reservasi} entities for the service.
     *
     * @param reservasi The list of Reservasi to set.
     */
    public void setReservasi(List<Reservasi> reservasi) { // Changed method name and parameter
        this.reservasi = reservasi;
    }

    /**
     * Returns the list of customers registered for the service.
     *
     * @return The list of customers.
     */
    public List<Customer> getCustomer() { // Changed method name and return type
        return customer;
    }

    /**
     * Sets the list of customers registered for the service.
     *
     * @param customer The list of customers to set.
     */
    public void setCustomer(List<Customer> customer) { // Changed method name and parameter
        this.customer = customer;
    }
}