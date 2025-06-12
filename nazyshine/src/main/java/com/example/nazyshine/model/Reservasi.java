package com.example.nazyshine.model; // Changed package name

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table; // Added @Table for explicit mapping

/**
 * Represents a reservation record, detailing the relationship between a customer and a service.
 *
 * <p>This entity includes scores for assessment points (UTS, UAS, quizzes), the total score,
 * and the calculated grade for the customer's interaction with a particular service.</p>
 */
@Entity
@Table(name = "reservasi") // Explicitly setting table name
public class Reservasi { // Changed class name from MataKuliahMahasiswa to Reservasi

    /**
     * The unique identifier for the Reservasi entity.
     * Automatically generated using {@code GenerationType.IDENTITY}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Many-to-One relationship to the {@link Customer} entity.
     *
     * <p>This property links the record to a specific customer. It is ignored during JSON
     * serialization to avoid cyclic references.</p>
     */
    @ManyToOne
    @JoinColumn(name = "customer_id") // Changed join column name
    @JsonIgnore
    private Customer customer; // Changed type from Mahasiswa to Customer

    /**
     * Many-to-One relationship to the {@link Layanan} entity.
     *
     * <p>This property links the record to a specific service. It is ignored during JSON
     * serialization to avoid cyclic references.</p>
     */
    @ManyToOne
    @JoinColumn(name = "layanan_id") // Changed join column name
    @JsonIgnore
    private Layanan layanan; // Changed type from MataKuliah to Layanan

    /**
     * The score obtained by the customer in a Mid-Service Check (UTS equivalent).
     */
    private int uts;

    /**
     * The score obtained by the customer in a Final Service Review (UAS equivalent).
     */
    private int uas;

    /**
     * The score obtained by the customer in quick feedback or quizzes (Kuis equivalent).
     */
    private int kuis;

    /**
     * The total score of the customer's interaction with the service, calculated as the average
     * of UTS, UAS, and Kuis scores.
     */
    private int total;

    /**
     * The grade of the customer's interaction with the service, calculated based on the total score.
     * Possible grades are "A", "AB", "B", "BC", "C", and "D".
     */
    private String grade;

    /**
     * Default constructor for the Reservasi entity.
     */
    public Reservasi() {}

    /**
     * Constructor to initialize Reservasi with specific customer and service.
     *
     * @param customer The customer associated with this record.
     * @param layanan The service associated with this record.
     */
    public Reservasi(Customer customer, Layanan layanan) { // Changed parameters
        this.customer = customer;
        this.layanan = layanan;
    }

    // Getters and Setters

    /**
     * Returns the unique ID of the Reservasi record.
     *
     * @return The unique ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID for the Reservasi record.
     *
     * @param id The unique ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the customer associated with this record.
     *
     * @return The customer.
     */
    public Customer getCustomer() { // Changed method name and return type
        return customer;
    }

    /**
     * Sets the customer for this record.
     *
     * @param customer The customer to set.
     */
    public void setCustomer(Customer customer) { // Changed method name and parameter
        this.customer = customer;
    }

    /**
     * Returns the service associated with this record.
     *
     * @return The service.
     */
    public Layanan getLayanan() { // Changed method name and return type
        return layanan;
    }

    /**
     * Sets the service for this record.
     *
     * @param layanan The service to set.
     */
    public void setLayanan(Layanan layanan) { // Changed method name and parameter
        this.layanan = layanan;
    }

    /**
     * Returns the score of the Mid-Service Check (UTS equivalent).
     *
     * @return The UTS score.
     */
    public int getUts() {
        return uts;
    }

    /**
     * Sets the score of the Mid-Service Check (UTS equivalent) and recalculates the total score and grade.
     *
     * @param uts The UTS score to set.
     */
    public void setUts(int uts) {
        this.uts = uts;
        calculateTotalAndGrade();
    }

    /**
     * Returns the score of the Final Service Review (UAS equivalent).
     *
     * @return The UAS score.
     */
    public int getUas() {
        return uas;
    }

    /**
     * Sets the score of the Final Service Review (UAS equivalent) and recalculates the total score and grade.
     *
     * @param uas The UAS score to set.
     */
    public void setUas(int uas) {
        this.uas = uas;
        calculateTotalAndGrade();
    }

    /**
     * Returns the score of quick feedback or quizzes (Kuis equivalent).
     *
     * @return The quiz score.
     */
    public int getKuis() {
        return kuis;
    }

    /**
     * Sets the score of quick feedback or quizzes (Kuis equivalent) and recalculates the total score and grade.
     *
     * @param kuis The quiz score to set.
     */
    public void setKuis(int kuis) {
        this.kuis = kuis;
        calculateTotalAndGrade();
    }

    /**
     * Returns the total score of the customer in the service interaction.
     *
     * @return The total score.
     */
    public int getTotal() {
        return total;
    }

    /**
     * Returns the grade of the customer in the service interaction.
     *
     * @return The grade.
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Calculates the total score and grade based on UTS, UAS, and Kuis scores.
     *
     * <p>The total score is the average of the three assessments, and the grade is assigned
     * based on predefined thresholds:
     * <ul>
     * <li>A: Total > 85</li>
     * <li>AB: Total ≥ 85</li>
     * <li>B: Total ≥ 80</li>
     * <li>BC: Total ≥ 70</li>
     * <li>C: Total ≥ 50</li>
     * <li>D: Total < 50</li>
     * </ul>
     * </p>
     */
    private void calculateTotalAndGrade() {
        this.total = (this.uts + this.uas + this.kuis) / 3;

        if (total > 85) {
            this.grade = "A";
        } else if (total >= 80) {
            this.grade = "AB";
        } else if (total >= 75) {
            this.grade = "B";
        } else if (total >= 70) {
            this.grade = "BC";
        } else if (total >= 50) {
            this.grade = "C";
        } else {
            this.grade = "D";
        }
    }
}