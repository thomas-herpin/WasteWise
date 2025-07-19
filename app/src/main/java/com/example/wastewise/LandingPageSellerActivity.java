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

public class LandingPageSellerActivity extends AppCompatActivity {
    Button btnDaftarSlr, btnMasukSlr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_landing_page_seller);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnDaftarSlr = findViewById(R.id.btnDaftarSlr);
        btnMasukSlr = findViewById(R.id.btnMasukSlr);

        btnDaftarSlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });

        btnMasukSlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });
    }
    public void toRegister() {
        Intent intent = new Intent(this, RegisterSellerActivity.class);
        startActivity(intent);
    }

    public void toLogin() {
        Intent intent = new Intent(this, LoginSellerActivity.class);
        startActivity(intent);
    }
}