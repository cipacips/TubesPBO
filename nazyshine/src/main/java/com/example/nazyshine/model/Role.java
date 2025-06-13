package com.example.nazyshine.model; // Changed package name

/**
 * Enumeration representing the roles in the Nazyshine application.
 *
 * <p>This enum is used to differentiate between different types of users
 * in the system. Each role determines the permissions and functionalities
 * accessible to the user.</p>
 */
public enum Role {

    /**
     * Role for customers.
     *
     * <p>Users with this role are customers who can perform actions such as:
     * <ul>
     * <li>Viewing and managing their service reservations.</li>
     * <li>Booking new services.</li>
     * </ul>
     * </p>
     */
    PELANGGAN,

    /**
     * Role for Pegawai (employee) staff.
     *
     * <p>Users with this role are administrative staff who can perform actions such as:
     * <ul>
     * <li>Managing customer data.</li>
     * <li>Adding or updating service information.</li>
     * <li>Overseeing reservations and service delivery.</li>
     * </ul>
     * </p>
     */
    ADMIN
}