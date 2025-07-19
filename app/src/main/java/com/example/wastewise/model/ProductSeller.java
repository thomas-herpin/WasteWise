package com.example.wastewise.model;

public class ProductSeller {
    private String jumlahItem;
    private String namaProduk;
    private int harga;
    private int fotoProduk;

    public ProductSeller(String jumlahItem, String namaProduk, int harga, int fotoProduk) {
        this.jumlahItem = jumlahItem;
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.fotoProduk = fotoProduk;
    }

    public String getJumlahItem() {
        return jumlahItem;
    }

    public void setJumlahItem(String jumlahItem) {
        this.jumlahItem = jumlahItem;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(int fotoProduk) {
        this.fotoProduk = fotoProduk;
    }
}
