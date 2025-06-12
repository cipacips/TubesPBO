package com.example.nazyshine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nazyshine.model.Customer;
import com.example.nazyshine.repository.CustomerRepository;

/**
 * Spring MVC Controller for managing navigation pages in a JSP-based application.
 *
 * <p>This controller handles various GET requests to display the dashboard,
 * CRUD pages, search pages, and specific features like service registration and
 * transcript display for customers.</p>
 */
@Controller
public class ViewController {

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Displays the customer dashboard page.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add attributes that will be passed to the JSP page
     * @return The name of the JSP page `dashboard_customer`
     */
    @GetMapping("/dashboard_customer")
    public String dashboardCustomer(Authentication auth, Model model) {
        String username = auth.getName();
        model.addAttribute("username", username);
        return "dashboard_customer";
    }

    /**
     * Displays the Pegawai dashboard page.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add attributes that will be passed to the JSP page
     * @return The name of the JSP page `dashboard_pegawai`
     */
    @GetMapping("/dashboard_pegawai")
    public String dashboardPegawai(Authentication auth, Model model) {
        String username = auth.getName();
        model.addAttribute("username", username);
        return "dashboard_pegawai";
    }

    /**
     * Displays the CRUD page for managing customers.
     *
     * @return The name of the JSP page `crud_customer`
     */
    @GetMapping("/dashboard_pegawai/customer")
    public String showCrudCustomerPage() {
        return "crud_customer";
    }

    /**
     * Displays the CRUD page for managing Pegawai.
     *
     * @return The name of the JSP page `crud_pegawai`
     */
    @GetMapping("/dashboard_pegawai/pegawai")
    public String showCrudPegawaiPage() {
        return "crud_pegawai";
    }

    /**
     * Displays the CRUD page for managing services.
     *
     * @return The name of the JSP page `crud_layanan`
     */
    @GetMapping("/dashboard_pegawai/layanan") // Changed endpoint path
    public String showCrudLayananPage() { // Changed method name
        return "crud_layanan"; // Changed JSP page name
    }

    /**
     * Displays the search page by customer NIM.
     *
     * @return The name of the JSP page `search_nim`
     */
    @GetMapping("/dashboard_pegawai/search_nim")
    public String showNimPage() {
        return "search_nim";
    }

    /**
     * Displays the search page by customer ID.
     *
     * @return The name of the JSP page `search_id`
     */
    @GetMapping("/dashboard_pegawai/search_id")
    public String showIdPage() {
        return "search_id";
    }

    /**
     * Displays the search page by service.
     *
     * @return The name of the JSP page `search_layanan`
     */
    @GetMapping("/dashboard_pegawai/search_layanan") // Changed endpoint path
    public String showLayananPage() { // Changed method name
        return "search_layanan"; // Changed JSP page name
    }

    /**
     * Displays the service registration page for customers.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add the `customerId` attribute to the JSP page
     * @return The name of the JSP page `regis_layanan`
     */
    @GetMapping("/dashboard_customer/registrasi_layanan") // Changed endpoint path
    public String registrasiLayanan(Authentication auth, Model model) { // Changed method name
        String username = auth.getName();
        Long customerId = findCustomerIdByUsername(username);
        model.addAttribute("customerId", customerId);
        return "regis_layanan"; // Changed JSP page name
    }

    /**
     * Displays the transcript page for customers.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add the `customerId` attribute to the JSP page
     * @return The name of the JSP page `view_transkrip`
     */
    @GetMapping("/dashboard_customer/view_transkrip")
    public String showCustomerTranskripPage(Authentication auth, Model model) {
        String username = auth.getName();
        Long customerId = findCustomerIdByUsername(username);
        model.addAttribute("customerId", customerId);
        return "view_transkrip";
    }

    /**
     * Displays the page for updating grades.
     *
     * @return The name of the JSP page `update_nilai`
     */
    @GetMapping("dashboard_pegawai/update-nilai-page")
    public String updateNilaiPage() {
        return "update_nilai";
    }

    /**
     * Finds the customer ID based on the username.
     *
     * @param username The username of the customer
     * @return The ID of the customer if found
     * @throws RuntimeException if the customer is not found
     */
    private Long findCustomerIdByUsername(String username) {
        return customerRepository.findByUsername(username)
                .map(Customer::getId)
                .orElseThrow(() -> new RuntimeException("Customer not found with username: " + username));
    }
}