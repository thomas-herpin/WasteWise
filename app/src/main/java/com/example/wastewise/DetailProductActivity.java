package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wastewise.adapter.ProductDetailAdapter;
import com.example.wastewise.model.ProductDetail;

import java.util.ArrayList;

public class DetailProductActivity extends AppCompatActivity {

    ListView listView;
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
        btnNext = findViewById(R.id.btnNext);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPayment();
            }
        });

        listView = findViewById(R.id.lvDetailProduct);

        productDetailList = new ArrayList<>();
        productDetailList.add(new ProductDetail(R.drawable.rotibun, "Roti Bun", "3 pcs"));
        productDetailList.add(new ProductDetail(R.drawable.cheesepastry, "Cheese Pastry", "4 pcs"));
        productDetailList.add(new ProductDetail(R.drawable.buttercroissant, "Butter Croissant", "3 pcs"));

        adapter = new ProductDetailAdapter(this, productDetailList);
        listView.setAdapter(adapter);

    }

    private void toPayment() {
        Intent intent = new Intent(this, OrderSummaryActivity.class);
        startActivity(intent);
    }
}