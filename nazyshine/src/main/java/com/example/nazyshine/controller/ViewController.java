// src/main/java/com/example/nazyshine/controller/ViewController.java
package com.example.nazyshine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nazyshine.model.Pelanggan; // Import Pelanggan jika diperlukan
import com.example.nazyshine.service.PelangganService; // Menggunakan PelangganService

/**
 * Spring MVC Controller for managing navigation pages in a JSP-based application.
 *
 * <p>This controller handles various GET requests to display the dashboard,
 * CRUD pages, search pages, and specific features like service registration and
 * reservation history display for customers and admins.</p>
 */
@Controller
public class ViewController {

    @Autowired
    private PelangganService pelangganService; // Menggunakan pelangganService, konsisten

    /**
     * Displays the customer dashboard page.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add attributes that will be passed to the JSP page
     * @return The name of the JSP page `dashboard_pelanggan`
     */
    @GetMapping("/dashboard_customer")
    public String dashboardPelanggan(Authentication auth, Model model) { // Mengubah nama metode
        String username = auth.getName();
        model.addAttribute("username", username);
        return "dashboard_pelanggan"; // Nama JSP page konsisten
    }

    /**
     * Displays the Admin dashboard page.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add attributes that will be passed to the JSP page
     * @return The name of the JSP page `dashboard_admin`
     */
    @GetMapping("/dashboard_admin") // Mengubah endpoint dari /dashboard_pegawai
    public String dashboardAdmin(Authentication auth, Model model) { // Mengubah nama metode
        String username = auth.getName();
        model.addAttribute("username", username);
        return "dashboard_admin"; // Nama JSP page konsisten
    }

    /**
     * Displays the CRUD page for managing customers (Pelanggan).
     *
     * @return The name of the JSP page `crud_pelanggan`
     */
    @GetMapping("/dashboard_admin/pelanggan") // Mengubah endpoint dari /dashboard_pegawai/customer
    public String showCrudPelangganPage() { // Mengubah nama metode
        return "crud_pelanggan"; // Nama JSP page konsisten
    }

    /**
     * Displays the CRUD page for managing Admins.
     *
     * @return The name of the JSP page `crud_admin`
     */
    @GetMapping("/dashboard_admin/admin") // Mengubah endpoint dari /dashboard_pegawai/pegawai
    public String showCrudAdminPage() { // Mengubah nama metode
        return "crud_admin"; // Nama JSP page konsisten
    }

    /**
     * Displays the CRUD page for managing services (Layanan).
     *
     * @return The name of the JSP page `crud_layanan`
     */
    @GetMapping("/dashboard_admin/layanan")
    public String showCrudLayananPage() {
        return "crud_layanan";
    }

    /**
     * Displays the search page by customer username.
     * (As we use username for login, searching by username is more common than NIM/ID for general search)
     *
     * @return The name of the JSP page `search_username`
     */
    @GetMapping("/dashboard_admin/search_username") // Mengubah endpoint dari search_nim
    public String showSearchUsernamePage() { // Mengubah nama metode
        return "search_username"; // Nama JSP page konsisten
    }

    /**
     * Displays the search page by customer ID.
     *
     * @return The name of the JSP page `search_id`
     */
    @GetMapping("/dashboard_admin/search_id") // Mengubah endpoint dari dashboard_pegawai
    public String showSearchByIdPage() { // Mengubah nama metode
        return "search_id";
    }

    /**
     * Displays the search page by service (Layanan).
     *
     * @return The name of the JSP page `search_layanan`
     */
    @GetMapping("/dashboard_admin/search_layanan")
    public String showSearchLayananPage() {
        return "search_layanan";
    }

    /**
     * Displays the service registration page for customers.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add the `pelangganId` attribute to the JSP page
     * @return The name of the JSP page `regis_layanan`
     */
    @GetMapping("/dashboard_customer/registrasi_layanan")
    public String registrasiLayanan(Authentication auth, Model model) {
        String username = auth.getName();
        Integer pelangganId = findPelangganIdByUsername(username); // Tipe ID konsisten
        model.addAttribute("pelangganId", pelangganId); // Nama atribut konsisten
        return "regis_layanan";
    }

    /**
     * Displays the reservation history page for customers.
     *
     * @param auth Authentication object to get the username of the logged-in user
     * @param model Model to add the `pelangganId` attribute to the JSP page
     * @return The name of the JSP page `view_riwayat_reservasi`
     */
    @GetMapping("/dashboard_customer/riwayat_reservasi")
    public String showCustomerRiwayatReservasiPage(Authentication auth, Model model) {
        String username = auth.getName();
        Integer pelangganId = findPelangganIdByUsername(username); // Tipe ID konsisten
        model.addAttribute("pelangganId", pelangganId); // Nama atribut konsisten
        return "view_riwayat_reservasi"; // Nama JSP page konsisten
    }

    /**
     * Displays the page for updating reservation status (for Admin).
     *
     * @return The name of the JSP page `update_reservasi_status`
     */
    @GetMapping("dashboard_admin/update_reservasi_status")
    public String updateReservasiStatusPage() {
        return "update_reservasi_status"; // Nama JSP page konsisten
    }
    
    /**
     * Displays the registration page for new customers.
     * This is a public page.
     * @return The name of the JSP page `register`
     */
    @GetMapping("/register") // New mapping for the registration page
    public String showRegisterPage() {
        return "register"; // Returns register.jsp
    }

    /**
     * Redirects to the appropriate dashboard based on user's role after login.
     * This endpoint is targeted by defaultSuccessUrl in SecurityConfig.
     *
     * @param auth Authentication object to determine user's role
     * @return Redirect string to either customer or admin dashboard
     */
    @GetMapping("/dashboard")
    public String redirectToDashboard(Authentication auth) {
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/dashboard_admin";
        }
        return "redirect:/dashboard_customer";
    }

    /**
     * Finds the customer (Pelanggan) ID based on the username.
     * Uses PelangganService to retrieve customer details.
     *
     * @param username The username of the customer
     * @return The ID of the customer (Integer) if found
     * @throws RuntimeException if the customer is not found
     */
    private Integer findPelangganIdByUsername(String username) { // Tipe return dan nama metode konsisten
        // Menggunakan pelangganService untuk mengambil Pelanggan berdasarkan username
        Pelanggan pelanggan = pelangganService.getPelangganByUsername(username); 
        return pelanggan.getId(); // Mengembalikan ID pelanggan
    }
}