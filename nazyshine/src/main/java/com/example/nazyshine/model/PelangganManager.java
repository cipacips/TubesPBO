package com.example.nazyshine.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas untuk mengelola daftar pelanggan (CRUD).
 */
public class PelangganManager implements ICRUD {

    private List<Pelanggan> pelangganList;

    public PelangganManager() {
        this.pelangganList = new ArrayList<>();
    }

    @Override
    public void create(Object obj) {
        if (obj instanceof Pelanggan) {
            pelangganList.add((Pelanggan) obj);
        }
    }

    @Override
    public void read() {
        for (Pelanggan pelanggan : pelangganList) {
            System.out.println(pelanggan.getNama());
        }
    }

    @Override
    public void update(Object obj) {
        if (obj instanceof Pelanggan) {
            Pelanggan updated = (Pelanggan) obj;
            for (int i = 0; i < pelangganList.size(); i++) {
                if (pelangganList.get(i).getId() == updated.getId()) {
                    pelangganList.set(i, updated);
                    break;
                }
            }
        }
    }

    @Override
    public void delete(Object obj) {
        if (obj instanceof Pelanggan) {
            pelangganList.remove(obj);
        }
    }

    public List<Pelanggan> getPelangganList() {
        return pelangganList;
    }

    public void setPelangganList(List<Pelanggan> pelangganList) {
        this.pelangganList = pelangganList;
    }
}
