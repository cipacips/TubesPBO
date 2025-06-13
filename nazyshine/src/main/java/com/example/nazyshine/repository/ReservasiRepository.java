package com.example.nazyshine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.Reservasi;
import com.example.nazyshine.model.Pelanggan;

public interface ReservasiRepository extends JpaRepository<Reservasi, Long> {
    List<Reservasi> findByCustomer(Pelanggan customer);
}
