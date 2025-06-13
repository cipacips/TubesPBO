// src/main/java/com/example/nazyshine/model/User.java
package com.example.nazyshine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Column; // <--- PASTIKAN IMPORT INI ADA
import jakarta.persistence.Enumerated; // PASTIKAN IMPORT INI ADA
import jakarta.persistence.EnumType; // PASTIKAN IMPORT INI ADA

/**
 * Abstract base class for User.
 */
@Entity
@Table(name = "user_account")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id; // Diubah dari int menjadi Integer untuk konsistensi dengan JpaRepository

    protected String nama;
    protected String email;
    protected String username;
    protected String password;

    @Column(columnDefinition = "VARCHAR(20)") // <--- BARIS INI YANG HARUS DITAMBAHKAN
    @Enumerated(EnumType.STRING)
    protected Role role;

    // Metode login() dan logout() dihapus karena otentikasi/otorisasi dihandle oleh Spring Security.

    // Getter dan Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}