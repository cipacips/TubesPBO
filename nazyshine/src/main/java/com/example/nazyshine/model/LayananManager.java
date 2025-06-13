package com.example.nazyshine.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas untuk mengelola daftar layanan (CRUD).
 */
public class LayananManager implements ICRUD {

    private List<Layanan> layananList;

    public LayananManager() {
        this.layananList = new ArrayList<>();
    }

    @Override
    public void create(Object obj) {
        if (obj instanceof Layanan) {
            layananList.add((Layanan) obj);
        }
    }

    @Override
    public void read() {
        for (Layanan layanan : layananList) {
            System.out.println(layanan.getInfo());
        }
    }

    @Override
    public void update(Object obj) {
        if (obj instanceof Layanan) {
            Layanan updated = (Layanan) obj;
            for (int i = 0; i < layananList.size(); i++) {
                if (layananList.get(i).getId() == updated.getId()) {
                    layananList.set(i, updated);
                    break;
                }
            }
        }
    }

    @Override
    public void delete(Object obj) {
        if (obj instanceof Layanan) {
            layananList.remove(obj);
        }
    }

    public List<Layanan> getLayananList() {
        return layananList;
    }

    public void setLayananList(List<Layanan> layananList) {
        this.layananList = layananList;
    }
}
