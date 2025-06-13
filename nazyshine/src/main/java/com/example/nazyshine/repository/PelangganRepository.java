// src/main/java/com/example/nazyshine/repository/PelangganRepository.java
package com.example.nazyshine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.Pelanggan;

public interface PelangganRepository extends JpaRepository<Pelanggan, Integer> { // ID Pelanggan adalah Integer, konsisten
    Optional<Pelanggan> findByUsername(String username);
}