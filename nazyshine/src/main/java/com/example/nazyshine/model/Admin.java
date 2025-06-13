package com.example.nazyshine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

/**
 * Class representing `Admin`.
 */
@Entity
@Table(name = "admin")
public class Admin extends User {

    private String shift;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Layanan> layanan;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Pelanggan> pelanggan;

    @Override
    public boolean login() {
        // Logic login admin
        return true;
    }

    public void kelolaLayanan() {
        // Stub untuk kelola layanan (CRUD) → akan di-handle oleh LayananManager implementasi ICRUD
    }

    public void kelolaPelanggan() {
        // Stub untuk kelola pelanggan (CRUD) → akan di-handle oleh PelangganManager implementasi ICRUD
    }

    public void updateStatusReservasi(Reservasi reservasi) {
        // Logic update status reservasi (dummy)
    }

    // Getter dan Setter
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public List<Layanan> getLayanan() { return layanan; }
    public void setLayanan(List<Layanan> layanan) { this.layanan = layanan; }

    public List<Pelanggan> getPelanggan() { return pelanggan; }
    public void setPelanggan(List<Pelanggan> pelanggan) { this.pelanggan = pelanggan; }
}
