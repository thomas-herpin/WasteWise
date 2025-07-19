package com.example.wastewise.model;

public class ProductDetail {
    private int logoOutlet;
    private String namaMakanan;
    private String quantity;

    public ProductDetail(int logoOutlet, String namaMakanan, String quantity) {
        this.logoOutlet = logoOutlet;
        this.namaMakanan = namaMakanan;
        this.quantity = quantity;
    }

    public int getLogoOutlet() {
        return logoOutlet;
    }

    public void setLogoOutlet(int logoOutlet) {
        this.logoOutlet = logoOutlet;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
