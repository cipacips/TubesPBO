// src/main/java/com/example/nazyshine/model/StatusReservasi.java
package com.example.nazyshine.model;

/**
 * Enum untuk status reservasi.
 */
public enum StatusReservasi {
    PENDING,
    DIKONFIRMASI,
    SELESAI,
    CANCELED // Menambahkan status CANCELED untuk konsistensi di frontend dan backend
}