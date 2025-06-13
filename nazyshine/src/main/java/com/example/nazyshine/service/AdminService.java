package com.example.nazyshine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.nazyshine.model.Admin;
import com.example.nazyshine.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository pegawaiRepository;

    public Admin createPegawai(Admin pegawai) {
        return pegawaiRepository.save(pegawai);
    }

    public List<Admin> getAllPegawai() {
        return pegawaiRepository.findAll();
    }

    public Admin getPegawaiById(Integer id) {
        return pegawaiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pegawai not found for ID: " + id));
    }

    public Admin updatePegawai(Integer id, Admin updatedPegawai) {
        Admin pegawai = getPegawaiById(id);
        pegawai.setUsername(updatedPegawai.getUsername());
        pegawai.setPassword(updatedPegawai.getPassword());
        return pegawaiRepository.save(pegawai);
    }

    public void deletePegawai(Integer id) {
        pegawaiRepository.deleteById(id);
    }
}
