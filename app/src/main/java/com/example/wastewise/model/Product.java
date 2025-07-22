package com.example.wastewise.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject {
    public Product() {
    }

    @PrimaryKey
    private int idProduk;

    private Seller seller;

    private String namaItem;

    private String jumlahItem;

    private int harga;

    private int tanggalExpired;

    private int fotoProduk;

    public Product(int idProduk, Seller seller, String jumlahItem, int harga, int tanggalExpired) {
        this.idProduk = idProduk;
        this.seller = seller;
        this.namaItem = namaItem;
        this.jumlahItem = jumlahItem;
        this.harga = harga;
        this.tanggalExpired = tanggalExpired;
        this.fotoProduk = fotoProduk;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    public String getJumlahItem() {
        return jumlahItem;
    }

    public void setJumlahItem(String jumlahItem) {
        this.jumlahItem = jumlahItem;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTanggalExpired() {
        return tanggalExpired;
    }

    public void setTanggalExpired(int tanggalExpired) {
        this.tanggalExpired = tanggalExpired;
    }

    public int getFotoProduk() {
        return fotoProduk;
    }

    public void setFotoProduk(int fotoProduk) {
        this.fotoProduk = fotoProduk;
    }
}

