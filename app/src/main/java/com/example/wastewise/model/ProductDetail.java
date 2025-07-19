package com.example.wastewise.model;

public class ProductDetail {
    private int imvMakanan;
    private String namaMakanan;
    private String quantity;

    public ProductDetail(int imvMakanan, String namaMakanan, String quantity) {
        this.imvMakanan = imvMakanan;
        this.namaMakanan = namaMakanan;
        this.quantity = quantity;
    }

    public int getImvMakanan() {
        return imvMakanan;
    }

    public void setImvMakanan(int imvMakanan) {
        this.imvMakanan = imvMakanan;
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
