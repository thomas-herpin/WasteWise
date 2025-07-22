package com.example.wastewise.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Seller extends RealmObject {
    public Seller() {
    }

    private String namaOutlet;

    private String tipeOutlet;

    private String alamatOutlet;

    private int logoOutlet;

    public Seller(String namaOutlet, String tipeOutlet, String alamatOutlet, int logoOutlet) {
        this.namaOutlet = namaOutlet;
        this.tipeOutlet = tipeOutlet;
        this.alamatOutlet = alamatOutlet;
        this.logoOutlet = logoOutlet;
    }

    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
    }

    public String getTipeOutlet() {
        return tipeOutlet;
    }

    public void setTipeOutlet(String tipeOutlet) {
        this.tipeOutlet = tipeOutlet;
    }

    public String getAlamatOutlet() {
        return alamatOutlet;
    }

    public void setAlamatOutlet(String alamatOutlet) {
        this.alamatOutlet = alamatOutlet;
    }

    public int getLogoOutlet() {
        return logoOutlet;
    }

    public void setLogoOutlet(int logoOutlet) {
        this.logoOutlet = logoOutlet;
    }
}
