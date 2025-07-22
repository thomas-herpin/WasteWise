package com.example.wastewise.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SellerAuth extends RealmObject {
    @PrimaryKey
    private String email;
    private String username;
    private String password;

    private String namaOutlet;

    public SellerAuth() {
    }

    public SellerAuth(String email, String username, String password, String namaOutlet) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.namaOutlet = namaOutlet;
    }

    // Getter dan Setter
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
}