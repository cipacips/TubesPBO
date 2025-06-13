// src/main/java/com/example/nazyshine/service/ReservasiService.java
package com.example.nazyshine.service;

import java.util.Date;
import java.util.List; // Import List
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nazyshine.model.Pelanggan;
import com.example.nazyshine.model.Layanan;
import com.example.nazyshine.model.Reservasi;
import com.example.nazyshine.repository.PelangganRepository;
import com.example.nazyshine.repository.ReservasiRepository;
import com.example.nazyshine.repository.LayananRepository;

@Service
public class ReservasiService {

    @Autowired
    private ReservasiRepository reservasiRepository;

    @Autowired
    private PelangganRepository pelangganRepository; // Nama variabel disesuaikan

    @Autowired
    private LayananRepository layananRepository;

    /**
     * Membuat reservasi antara pelanggan dan layanan.
     * Tanggal reservasi disetel ke waktu sistem saat ini.
     *
     * @param customerId ID pelanggan (Integer)
     * @param layananId  ID layanan (Long)
     * @return Reservasi yang berhasil disimpan
     */
    public Reservasi assignLayananToCustomer(Integer customerId, Long layananId) { // Tipe layananId diubah ke Long
        Pelanggan customer = pelangganRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Pelanggan not found with ID: " + customerId));

        Layanan layanan = layananRepository.findById(layananId)
                .orElseThrow(() -> new RuntimeException("Layanan not found with ID: " + layananId));

        Reservasi reservasi = new Reservasi(customer, layanan);
        reservasi.setTanggal(new Date()); // Tanggal reservasi disetel ke waktu sekarang

        return reservasiRepository.save(reservasi);
    }

    /**
     * Menghapus reservasi berdasarkan ID pelanggan dan layanan.
     * Akan melempar RuntimeException jika reservasi tidak ditemukan.
     *
     * @param customerId ID pelanggan (Integer)
     * @param layananId  ID layanan (Long)
     */
    public void deleteReservasiByParams(Integer customerId, Long layananId) { // Tipe layananId diubah ke Long
        Optional<Reservasi> reservasiOpt = reservasiRepository.findByCustomerIdAndLayananId(customerId, layananId);

        Reservasi reservasi = reservasiOpt.orElseThrow(() ->
                new RuntimeException("Reservasi not found for Pelanggan ID: " + customerId + " and Layanan ID: " + layananId));

        reservasiRepository.delete(reservasi);
    }

    /**
     * Mengambil reservasi berdasarkan ID.
     * Akan melempar RuntimeException jika reservasi tidak ditemukan.
     *
     * @param id ID reservasi (Long)
     * @return reservasi yang ditemukan
     */
    public Reservasi getById(Long id) { // Tipe ID diubah menjadi Long, konsisten
        return reservasiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservasi not found with ID: " + id));
    }

    /**
     * Mengambil semua reservasi untuk pelanggan tertentu.
     *
     * @param customer Objek Pelanggan yang reservasinya ingin dicari.
     * @return Daftar reservasi untuk pelanggan yang diberikan.
     */
    public List<Reservasi> findByCustomer(Pelanggan customer) {
        return reservasiRepository.findByCustomer(customer);
    }

    /**
     * Menyimpan atau memperbarui reservasi.
     * Metode ini digunakan untuk menyimpan reservasi baru atau memperbarui reservasi yang sudah ada.
     *
     * @param reservasi Objek Reservasi untuk disimpan atau diperbarui.
     * @return Reservasi yang telah disimpan atau diperbarui.
     */
    public Reservasi save(Reservasi reservasi) {
        return reservasiRepository.save(reservasi);
    }
}