package com.example.nazyshine.repository; // Changed package name

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.Layanan; // Changed model import

public interface LayananRepository extends JpaRepository<Layanan, Long> { // Changed interface name
}