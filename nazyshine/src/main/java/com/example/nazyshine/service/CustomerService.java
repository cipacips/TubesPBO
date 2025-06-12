package com.example.nazyshine.service; // Changed package name

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nazyshine.model.Customer; // Changed model import
import com.example.nazyshine.model.Layanan; // Changed model import
import com.example.nazyshine.model.Reservasi; // Changed model import
import com.example.nazyshine.repository.CustomerRepository; // Changed repository import


/**
 * Service layer for managing {@link Customer} entities.
 *
 * <p>This service class provides business logic for creating, retrieving, updating, and deleting
 * {@link Customer} records in the database. It interacts with the {@link CustomerRepository} for CRUD operations.</p>
 */
@Service
public class CustomerService { // Changed class name

    @Autowired
    private CustomerRepository customerRepository; // Changed repository name

    /**
     * Creates a new {@link Customer} entity.
     *
     * <p>This method saves the provided {@link Customer} instance into the database.</p>
     *
     * @param customer The {@link Customer} entity to create.
     * @return The saved {@link Customer} entity.
     */
    public Customer createCustomer(Customer customer) { // Changed method name and parameter
        return customerRepository.save(customer);
    }

    /**
     * Retrieves all {@link Customer} entities from the database.
     *
     * @return A list of all {@link Customer} entities.
     */
    public List<Customer> getAllCustomer() { // Changed method name
        return customerRepository.findAll();
    }

    /**
     * Retrieves the list of {@link Layanan} entities associated with a given member ID.
     *
     * <p>This method finds a {@link Customer} by their member ID and returns a list of {@link Layanan}
     * associated with that customer through the {@link Reservasi} relationship.</p>
     *
     * @param memberId The member ID of the {@link Customer} to search for.
     * @return A list of {@link Layanan} entities associated with the given member ID.
     * @throws RuntimeException if no {@link Customer} is found with the given member ID.
     */
    public List<Layanan> getLayananByMemberId(String memberId) { // Changed method name and parameter
        Customer customer = customerRepository.findByMemberId(memberId) // Changed findByNim to findByMemberId
                .orElseThrow(() -> new RuntimeException("Customer not found with Member ID: " + memberId)); // Changed error message

        // Retrieve Layanan through Reservasi relationship
        return customer.getReservasi().stream() // Changed method name from getMataKuliahMahasiswa to getReservasi
                .map(Reservasi::getLayanan) // Changed method name from getMataKuliah to getLayanan
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a {@link Customer} entity by its ID.
     *
     * <p>If no entity is found with the given ID, a {@link RuntimeException} is thrown.</p>
     *
     * @param id The ID of the {@link Customer} entity to retrieve.
     * @return The {@link Customer} entity with the given ID.
     * @throws RuntimeException if no {@link Customer} entity is found for the provided ID.
     */
    public Customer getCustomerById(Long id) { // Changed method name
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id)); // Changed error message
    }

    /**
     * Retrieves a {@link Customer} entity with additional details, such as associated {@link Layanan}
     * and related evaluation metrics (UTS, UAS, Kuis, total, grade).
     *
     * <p>This method fetches a {@link Customer} and populates the list of {@link Layanan} with
     * the corresponding metrics and other service interaction data.</p>
     *
     * @param id The ID of the {@link Customer} entity to retrieve.
     * @return The {@link Customer} entity with additional details, including associated {@link Layanan}.
     * @throws RuntimeException if no {@link Customer} entity is found for the provided ID.
     */
    public Customer getCustomerWithDetails(Long id) { // Changed method name
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id)); // Changed error message

        // Map Reservasi to add evaluation details to Layanan
        List<Layanan> layananList = customer.getReservasi().stream() // Changed method name from getMataKuliahMahasiswa to getReservasi
                .map(r -> { // Changed mkm to r
                    Layanan layanan = r.getLayanan(); // Changed mk to layanan, getMataKuliah to getLayanan
                    // Set additional properties from Reservasi
                    layanan.setUts(r.getUts());
                    layanan.setUas(r.getUas());
                    layanan.setKuis(r.getKuis());
                    layanan.setTotal(r.getTotal());
                    layanan.setGrade(r.getGrade());
                    return layanan;
                }).collect(Collectors.toList());

        customer.setLayanan(layananList); // Changed method name from setMataKuliah to setLayanan

        return customer;
    }

    /**
     * Updates an existing {@link Customer} entity.
     *
     * <p>This method updates the properties of an existing {@link Customer} entity identified by its ID.
     * If the entity is not found, an exception is thrown. The updated data is saved to the database.</p>
     *
     * @param id The ID of the {@link Customer} entity to update.
     * @param updatedCustomer The updated {@link Customer} entity data.
     * @return The updated {@link Customer} entity.
     * @throws RuntimeException if no {@link Customer} entity is found for the provided ID.
     */
    public Customer updateCustomer(Long id, Customer updatedCustomer) { // Changed method name and parameter
        Customer customer = getCustomerById(id); // Changed variable name
        customer.setUsername(updatedCustomer.getUsername());
        customer.setPassword(updatedCustomer.getPassword());
        customer.setMemberId(updatedCustomer.getMemberId()); // Changed from setNim to setMemberId
        customer.setCategory(updatedCustomer.getCategory()); // Changed from setProdi to setCategory
        return customerRepository.save(customer);
    }

    /**
     * Deletes a {@link Customer} entity by its ID.
     *
     * <p>This method removes the {@link Customer} entity with the specified ID from the database.</p>
     *
     * @param id The ID of the {@link Customer} entity to delete.
     */
    public void deleteCustomer(Long id) { // Changed method name
        customerRepository.deleteById(id);
    }
}