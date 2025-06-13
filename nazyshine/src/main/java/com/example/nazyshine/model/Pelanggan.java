package com.example.nazyshine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;

/**
 * Class representing `Pelanggan`.
 */
@Entity
@Table(name = "pelanggan")
public class Pelanggan extends User {

    private String noHp;

    @JsonIgnore
    @OneToMany(mappedBy = "pelanggan", cascade = CascadeType.ALL)
    private List<Reservasi> reservasi;

    @Override
    public boolean login() {
        // Logic login pelanggan
        return true;
    }

    public void reservasi(Layanan layanan, Date tanggal) {
        // Membuat reservasi baru (logic diimplementasi di controller/service)
    }

    public void lihatRiwayat() {
        // Menampilkan daftar reservasi (logic dummy sesuai class diagram)
    }

    // Getter dan Setter
    public String getNoHp() { return noHp; }
    public void setNoHp(String noHp) { this.noHp = noHp; }

    public List<Reservasi> getReservasi() { return reservasi; }
    public void setReservasi(List<Reservasi> reservasi) { this.reservasi = reservasi; }
}
