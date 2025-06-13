// src/main/java/com/example/nazyshine/model/Pelanggan.java
package com.example.nazyshine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * Class representing `Pelanggan`.
 */
@Entity
@Table(name = "pelanggan")
public class Pelanggan extends User {

    private String noHp;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL) // Pastikan mappedBy sesuai dengan nama field di Reservasi
    private List<Reservasi> reservasi;

    // Metode-metode stub seperti login(), reservasi(), lihatRiwayat() dihapus.

    // Getter dan Setter
    public String getNoHp() { return noHp; }
    public void setNoHp(String noHp) { this.noHp = noHp; }

    public List<Reservasi> getReservasi() { return reservasi; }
    public void setReservasi(List<Reservasi> reservasi) { this.reservasi = reservasi; }
}