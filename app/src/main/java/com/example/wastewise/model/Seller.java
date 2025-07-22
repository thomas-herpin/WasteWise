package com.example.wastewise.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Seller extends RealmObject {

    @PrimaryKey
    private String email;

    private String username;
    private String password;

    private String namaOutlet;
    private String tipeOutlet;
    private String alamatOutlet;
    private int logoOutlet;
    private String jamBuka;
    private String jamTutup;
    private String phoneNumber;

    public Seller() {
    }

    public Seller(String email, String username, String password, String namaOutlet, String tipeOutlet, String alamatOutlet, int logoOutlet, String jamBuka, String jamTutup, String phoneNumber) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.namaOutlet = namaOutlet;
        this.tipeOutlet = tipeOutlet;
        this.alamatOutlet = alamatOutlet;
        this.logoOutlet = logoOutlet;
        this.jamBuka = jamBuka;
        this.jamTutup = jamTutup;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getJamBuka() {
        return jamBuka;
    }

    public void setJamBuka(String jamBuka) {
        this.jamBuka = jamBuka;
    }

    public String getJamTutup() {
        return jamTutup;
    }

    public void setJamTutup(String jamTutup) {
        this.jamTutup = jamTutup;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}