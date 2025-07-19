package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    Button btnManageProduct, btnCreateOffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnManageProduct = findViewById(R.id.btnManageProduct);
        btnCreateOffer = findViewById(R.id.btnCreateOffer);

        btnManageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toManageProduct();
            }
        });

        btnCreateOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCreateOffer();
            }
        });
    }

    public void toManageProduct(){
        Intent intent = new Intent(this, ProductSellerActivity.class);
        startActivity(intent);
    }

    public void toCreateOffer(){
        Intent intent = new Intent(this, CreateOfferActivity.class);
        startActivity(intent);
    }
}