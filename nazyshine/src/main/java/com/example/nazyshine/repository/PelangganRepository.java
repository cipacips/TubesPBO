package com.example.nazyshine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.Pelanggan;

public interface PelangganRepository extends JpaRepository<Pelanggan, Integer> {
    Optional<Pelanggan> findByUsername(String username);
}
