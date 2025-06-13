// src/main/java/com/example/nazyshine/repository/ReservasiRepository.java
package com.example.nazyshine.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.Reservasi;
import com.example.nazyshine.model.Pelanggan;

public interface ReservasiRepository extends JpaRepository<Reservasi, Long> { // ID Reservasi adalah Long, konsisten

    /**
     * Menemukan reservasi berdasarkan ID pelanggan dan ID layanan.
     * customerId adalah Integer (dari Pelanggan), layananId adalah Long (dari Layanan).
     */
    Optional<Reservasi> findByCustomerIdAndLayananId(Integer customerId, Long layananId);

    /**
     * Menemukan semua reservasi yang terkait dengan pelanggan tertentu.
     */
    List<Reservasi> findByCustomer(Pelanggan customer);
}