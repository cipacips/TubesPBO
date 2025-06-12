package com.example.nazyshine.repository; // Changed package name

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nazyshine.model.Reservasi; // Changed model import

public interface ReservasiRepository extends JpaRepository<Reservasi, Long> { // Changed interface name
    Optional<Reservasi> findByCustomerIdAndLayananId(Long customerId, Long layananId); // Changed method name parameters
}