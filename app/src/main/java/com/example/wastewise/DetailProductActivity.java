package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wastewise.adapter.ProductDetailAdapter;
import com.example.wastewise.model.ProductDetail;
import com.example.wastewise.ui.account.AccountFragment;
import com.example.wastewise.ui.product.ProductFragment;

import java.util.ArrayList;

public class DetailProductActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ProductDetail> productDetailList;
    ProductDetailAdapter adapter;
    ImageView btnBack;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProduct();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPayment();
            }
        });

        recyclerView = findViewById(R.id.rvDetailProduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productDetailList = new ArrayList<>();
        productDetailList.add(new ProductDetail(R.drawable.rotibun, "Roti Bun", "3 pcs"));
        productDetailList.add(new ProductDetail(R.drawable.cheesepastry, "Cheese Pastry", "4 pcs"));
        productDetailList.add(new ProductDetail(R.drawable.buttercroissant, "Butter Croissanr", "3 pcs"));

        adapter = new ProductDetailAdapter(this, productDetailList);
        recyclerView.setAdapter(adapter);
    }

    public void toProduct(){
        Intent intent = new Intent(this, ProductFragment.class);
        startActivity(intent);
    }

    public void toPayment() {
        Intent intent = new Intent(this, ProductFragment.class);
        startActivity(intent);
    }
}