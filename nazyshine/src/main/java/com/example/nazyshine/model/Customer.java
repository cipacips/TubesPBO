package com.example.nazyshine.model; // Changed package name

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table; // Added @Table for explicit mapping
import jakarta.persistence.Transient;

/**
 * Represents the Customer entity in the Nazyshine application.
 *
 * <p>This class inherits properties from {@link User} and adds specific attributes
 * for customers, such as member ID and category.
 * Additionally, it models the relationship between customers and the services they have reserved.</p>
 */
@Entity
@Table(name = "customer") // Explicitly setting table name
public class Customer extends User { // Changed class name from Mahasiswa to Customer

    /**
     * The unique Customer Member ID.
     */
    private String memberId; // Changed from nim to memberId

    /**
     * The customer category (e.g., premium, regular).
     */
    private String category; // Changed from prodi to category

    /**
     * One-to-Many relationship between Customer and Reservasi.
     *
     * <p>This property stores the list of services the customer has reserved.
     * It is ignored during JSON serialization to prevent cyclic references.</p>
     */
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) // Changed mappedBy to customer, type to Reservasi
    private List<Reservasi> reservasi; // Changed type from MataKuliahMahasiswa to Reservasi

    /**
     * List of Layanan (Services) for the customer.
     *
     * <p>This property is used only at the application level and is not stored in the database.</p>
     */
    @Transient
    private List<Layanan> layanan; // Changed type from MataKuliah to Layanan

    /**
     * Default constructor for the Customer entity.
     */
    public Customer() {}

    /**
     * Parameterized constructor for creating a Customer entity with specific attributes.
     *
     * @param username The customer's username.
     * @param password The customer's password.
     * @param role The role of the customer in the system.
     * @param memberId The unique Customer Member ID.
     * @param category The customer category.
     */
    public Customer(String username, String password, Role role, String memberId, String category) { // Changed parameters
        super(username, password, role);
        this.memberId = memberId; // Changed field assignment
        this.category = category; // Changed field assignment
    }

    /**
     * Returns the Customer Member ID.
     *
     * @return The Member ID of the customer.
     */
    public String getMemberId() { // Changed method name
        return memberId;
    }

    /**
     * Sets the Customer Member ID.
     *
     * @param memberId The Member ID to be assigned to the customer.
     */
    public void setMemberId(String memberId) { // Changed method name and parameter
        this.memberId = memberId;
    }

    /**
     * Returns the customer category.
     *
     * @return The category of the customer.
     */
    public String getCategory() { // Changed method name
        return category;
    }

    /**
     * Sets the customer category.
     *
     * @param category The category to be assigned to the customer.
     */
    public void setCategory(String category) { // Changed method name and parameter
        this.category = category;
    }

    /**
     * Returns the list of Reservasi entities associated with the customer.
     *
     * @return The list of Reservasi.
     */
    public List<Reservasi> getReservasi() { // Changed method name and return type
        return reservasi;
    }

    /**
     * Sets the list of Reservasi entities for the customer.
     *
     * @param reservasi The list of Reservasi to be assigned.
     */
    public void setReservasi(List<Reservasi> reservasi) { // Changed method name and parameter
        this.reservasi = reservasi;
    }

    /**
     * Returns the list of Layanan (Services) for the customer.
     *
     * @return The list of Layanan.
     */
    public List<Layanan> getLayanan() { // Changed method name and return type
        return layanan;
    }

    /**
     * Sets the list of Layanan (Services) for the customer.
     *
     * @param layanan The list of Layanan to be assigned.
     */
    public void setLayanan(List<Layanan> layanan) { // Changed method name and parameter
        this.layanan = layanan;
    }
}