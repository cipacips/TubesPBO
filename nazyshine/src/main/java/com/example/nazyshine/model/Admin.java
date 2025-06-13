// src/main/java/com/example/nazyshine/model/Admin.java
package com.example.nazyshine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
// Import yang tidak digunakan dihapus untuk kebersihan kode
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.CascadeType;
// import java.util.List;

/**
 * Class representing `Admin`.
 */
@Entity
@Table(name = "admin")
public class Admin extends User {

    private String shift;

    // Relasi OneToMany ke Layanan dan Pelanggan dihapus dari entitas Admin
    // karena manajemennya lebih cocok berada di service layer daripada sebagai bagian langsung dari entitas Admin.
    // Metode-metode stub seperti login(), kelolaLayanan(), dll., juga dihapus.

    // Getter dan Setter
    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }
}