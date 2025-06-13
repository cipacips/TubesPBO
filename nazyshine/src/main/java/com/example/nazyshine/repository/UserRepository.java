// src/main/java/com/example/nazyshine/repository/UserRepository.java
package com.example.nazyshine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.User;

public interface UserRepository extends JpaRepository<User, Integer> { // ID User adalah Integer, konsisten
    Optional<User> findByUsername(String username);
}