package com.example.wastewise.model;

public class ProductBackup {

    private String jumlahItem;

    private String alamat;

    private int harga;

    private int logoOutlet;

    public ProductBackup(String jumlahItem, String alamat, int harga, int logoOutlet) {
        this.jumlahItem = jumlahItem;
        this.alamat = alamat;
        this.harga = harga;
        this.logoOutlet = logoOutlet;
    }

    public int getLogoOutlet() {
        return logoOutlet;
    }

    public void setLogoOutlet(int logoOutlet) {
        this.logoOutlet = logoOutlet;
    }

    public String getJumlahItem() {
        return jumlahItem;
    }

    public void setJumlahItem(String jumlahItem) {
        this.jumlahItem = jumlahItem;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        alamat = alamat;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        harga = harga;
    }
}
