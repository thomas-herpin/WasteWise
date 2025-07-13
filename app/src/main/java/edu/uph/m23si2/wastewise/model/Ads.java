package edu.uph.m23si2.wastewise.model;

public class Ads {
    String judul;

    String deskripsi;

    int logoOutlet;

    public Ads(String judul, String deskripsi, int logoOutlet) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.logoOutlet = logoOutlet;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getLogoOutlet() {
        return logoOutlet;
    }

    public void setLogoOutlet(int logoOutlet) {
        this.logoOutlet = logoOutlet;
    }
}
