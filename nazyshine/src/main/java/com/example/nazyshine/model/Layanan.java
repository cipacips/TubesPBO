package com.example.nazyshine.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a service (Layanan) entity in the Nazyshine application.
 */
@Entity
@Table(name = "layanan") // Explicitly setting table name
public class Layanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;

    private double harga;

    private int durasi;

    @JsonIgnore
    @OneToMany(mappedBy = "layanan", cascade = CascadeType.ALL)
    private List<Reservasi> reservasi;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public List<Reservasi> getReservasi() {
        return reservasi;
    }

    public void setReservasi(List<Reservasi> reservasi) {
        this.reservasi = reservasi;
    }

    /**
     * Returns a string containing detailed information about the service.
     *
     * @return a formatted string with nama, harga, and durasi.
     */
    public String getInfo() {
        return "Layanan: " + nama + ", Harga: Rp" + harga + ", Durasi: " + durasi + " menit";
    }
}
