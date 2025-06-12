package com.example.nazyshine.repository; // Changed package name
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.User; // Changed model import

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}