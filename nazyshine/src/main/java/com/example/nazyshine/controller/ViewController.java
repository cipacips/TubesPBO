package com.example.nazyshine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.nazyshine.model.Pelanggan;
import com.example.nazyshine.service.PelangganService;

@Controller
public class ViewController {

    @Autowired
    private PelangganService pelangganService;

    @GetMapping("/dashboard_customer")
    public String dashboardPelanggan(Authentication auth, Model model) {
        String username = auth.getName();
        model.addAttribute("username", username);
        return "dashboard_pelanggan";
    }

    @GetMapping("/dashboard_admin")
    public String dashboardAdmin(Authentication auth, Model model) {
        String username = auth.getName();
        model.addAttribute("username", username);
        return "dashboard_admin";
    }

    @GetMapping("/dashboard_admin/pelanggan")
    public String showCrudPelangganPage() {
        return "crud_pelanggan";
    }

    @GetMapping("/dashboard_admin/admin")
    public String showCrudAdminPage() {
        return "crud_admin";
    }

    @GetMapping("/dashboard_admin/layanan")
    public String showCrudLayananPage() {
        return "crud_layanan";
    }

    @GetMapping("/dashboard_admin/search_username")
    public String showSearchUsernamePage() {
        return "search_username";
    }

    @GetMapping("/dashboard_admin/search_id")
    public String showSearchByIdPage() {
        return "search_id";
    }

    @GetMapping("/dashboard_admin/search_layanan")
    public String showSearchLayananPage() {
        return "search_layanan";
    }

    @GetMapping("/dashboard_customer/registrasi_layanan")
    public String registrasiLayanan(Authentication auth, Model model) {
        String username = auth.getName();
        Integer pelangganId = findPelangganIdByUsername(username);
        model.addAttribute("pelangganId", pelangganId);
        return "regis_layanan";
    }

    @GetMapping("/dashboard_customer/riwayat_reservasi")
    public String showCustomerRiwayatReservasiPage(Authentication auth, Model model) {
        String username = auth.getName();
        Integer pelangganId = findPelangganIdByUsername(username);
        model.addAttribute("pelangganId", pelangganId);
        return "view_riwayat_reservasi";
    }

    @GetMapping("dashboard_admin/update_reservasi_status")
    public String updateReservasiStatusPage() {
        return "update_reservasi_status";
    }

    // --- METODE showRegisterPage() DIHAPUS SEPENUHNYA ---

    @GetMapping("/dashboard")
    public String redirectToDashboard(Authentication auth) {
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/dashboard_admin";
        }
        return "redirect:/dashboard_customer";
    }

    private Integer findPelangganIdByUsername(String username) {
        Pelanggan pelanggan = pelangganService.getPelangganByUsername(username);
        return pelanggan.getId();
    }
}