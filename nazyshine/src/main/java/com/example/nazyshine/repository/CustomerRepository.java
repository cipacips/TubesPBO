package com.example.nazyshine.repository; // Changed package name

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nazyshine.model.Customer; // Changed model import

public interface CustomerRepository extends JpaRepository<Customer, Long> { // Changed interface name
    // Search customer by member ID
    Optional<Customer> findByMemberId(String memberId); // Changed method name from findByNim to findByMemberId
    Optional<Customer> findByUsername(String username);
}