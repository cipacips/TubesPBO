package com.example.nazyshine.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.nazyshine.model.Customer;
import com.example.nazyshine.model.Layanan; // Changed from MataKuliah
import com.example.nazyshine.model.User;
import com.example.nazyshine.repository.UserRepository;
import com.example.nazyshine.service.CustomerService;

/**
 * CustomerController is a REST controller that handles CRUD operations for Customer entities
 * and other related actions like fetching Layanan (service) information for a customer.
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserRepository userRepository;

    /**
     * Creates a new Customer record.
     * * @param customer the Customer object to be created.
     * @return a ResponseEntity containing the created Customer object.
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    /**
     * Retrieves all Customer records.
     * * @return a ResponseEntity containing a list of all Customer records.
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    /**
     * Retrieves a Customer record by its ID.
     * * @param id the ID of the Customer to retrieve.
     * @return a ResponseEntity containing the Customer record with the specified ID, or an error message if the ID is invalid.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().body("ID Customer tidak valid.");
        }
        Customer customer = customerService.getCustomerWithDetails(id);
        return ResponseEntity.ok(customer);
    }

    /**
     * Updates an existing Customer record.
     * * @param id the ID of the Customer to update.
     * @param customer the updated Customer object.
     * @return a ResponseEntity containing the updated Customer object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    /**
     * Deletes a Customer record by its ID.
     * * @param id the ID of the Customer to delete.
     * @return a ResponseEntity with no content after the Customer record is deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the list of Layanan (services) for a Customer by their NIM (customer ID).
     * * @param nim the NIM (customer ID) of the Customer.
     * @return a ResponseEntity containing a list of Layanan for the Customer with the specified NIM.
     */
    @GetMapping("/nim/{nim}/layanan") // Changed endpoint path
    public ResponseEntity<List<Layanan>> getLayananByMemberId(@PathVariable String nim) { // Changed method name
        List<Layanan> layananList = customerService.getLayananByMemberId(nim); // Changed service method call and variable name
        return ResponseEntity.ok(layananList);
    }

    /**
     * Retrieves the current logged-in Customer based on the authenticated username.
     * * @param auth the Authentication object containing the current authenticated user's details.
     * @return a ResponseEntity containing the Customer ID if the logged-in user is a Customer.
     * @throws RuntimeException if the logged-in user is not a Customer.
     */
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentCustomer(Authentication auth) {
        String username = auth.getName();
        System.out.println("DEBUG: Logged-in username = " + username);

        // Fetch user based on username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        // Cast to Customer
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            System.out.println("DEBUG: Customer ID = " + customer.getId());
            return ResponseEntity.ok(Map.of("customerId", customer.getId()));
        } else {
            throw new RuntimeException("Logged-in user is not a Customer");
        }
    }
}