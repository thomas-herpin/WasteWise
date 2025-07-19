package com.example.wastewise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wastewise.adapter.ProductSellerAdapter;
import com.example.wastewise.model.ProductSeller;

import java.util.ArrayList;

public class ProductSellerActivity extends AppCompatActivity {

    RecyclerView lsvProdukPenjual;
    ArrayList<ProductSeller> productSellerList;
    ProductSellerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_seller);

        lsvProdukPenjual = findViewById(R.id.lsvProdukPenjual);
        lsvProdukPenjual.setLayoutManager(new LinearLayoutManager(this));
        productSellerList = new ArrayList<>();

        productSellerList.add(new ProductSeller("10 pcs", "Roti Gosong", 10000, R.drawable.rotigosong));
        productSellerList.add(new ProductSeller("8 pcs", "Roti Tawar", 8000,R.drawable.rotitawar));
        productSellerList.add(new ProductSeller("5 pcs", "Biskuit", 5000,R.drawable.biscuit));
        productSellerList.add(new ProductSeller("2 pcs", "Cupcake", 12000,R.drawable.cupcake));

        adapter = new ProductSellerAdapter(this, productSellerList);
        lsvProdukPenjual.setAdapter(adapter);
    }
}