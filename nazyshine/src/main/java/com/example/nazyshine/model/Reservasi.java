package com.example.nazyshine.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

/**
 * Represents a reservation record, detailing the relationship between a customer and a service.
 */
@Entity
@Table(name = "reservasi")
public class Reservasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date tanggal;

    @Enumerated(EnumType.STRING)
    private StatusReservasi status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Pelanggan customer;

    @ManyToOne
    @JoinColumn(name = "layanan_id")
    @JsonIgnore
    private Layanan layanan;

    public Reservasi() {}

    public Reservasi(Pelanggan customer, Layanan layanan) {
        this.customer = customer;
        this.layanan = layanan;
        this.status = StatusReservasi.PENDING; // default
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public StatusReservasi getStatus() {
        return status;
    }

    public void setStatus(StatusReservasi status) {
        this.status = status;
    }

    public Pelanggan getCustomer() {
        return customer;
    }

    public void setCustomer(Pelanggan customer) {
        this.customer = customer;
    }

    public Layanan getLayanan() {
        return layanan;
    }

    public void setLayanan(Layanan layanan) {
        this.layanan = layanan;
    }

    /**
     * Mengubah status reservasi.
     * 
     * @param status Status baru yang akan diset.
     */
    public void ubahStatus(StatusReservasi status) {
        this.status = status;
    }

    /**
     * Mengecek apakah reservasi ini bentrok. Saat ini hanya placeholder.
     * 
     * @return false selalu, perlu logika tambahan jika ingin mendeteksi bentrok sebenarnya.
     */
    public boolean cekBentrok() {
        return false;
    }
}
