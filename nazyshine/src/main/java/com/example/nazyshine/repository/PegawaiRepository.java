package com.example.nazyshine.repository; // Changed package name

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.nazyshine.model.Pegawai; // Changed model import

public interface PegawaiRepository extends JpaRepository<Pegawai, Long> { // Changed interface name
    Optional<Pegawai> findByUsername(String username);
}