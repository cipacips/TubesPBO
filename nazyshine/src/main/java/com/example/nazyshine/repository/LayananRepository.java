// src/main/java/com/example/nazyshine/repository/LayananRepository.java
package com.example.nazyshine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.Layanan;

public interface LayananRepository extends JpaRepository<Layanan, Long> { // ID Layanan adalah Long, konsisten
}