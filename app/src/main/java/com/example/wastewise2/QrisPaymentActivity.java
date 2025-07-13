package com.example.wastewise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QrisPaymentActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnViewOrder;
    private ImageView qrCodeImage;

    //Data transaksi
    private String transactionId = "133756";
    private String storeName = "Roti O";
    private String storeLocation = "Roti O - Karya";
    private String pickupTime = "10.00";
    private String orderDate = "Sabtu, 28 Sep 2025";
    private String paymentMethod = "QRIS";
    private String totalAmount = "Rp43.000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qris_payment);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        loadDataFromIntent();
        setupListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnViewOrder = findViewById(R.id.btnViewOrder);
        qrCodeImage = findViewById(R.id.ivQrCodeImage);
    }

    private void loadDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            transactionId = intent.getStringExtra("transaction_id") != null ?
                    intent.getStringExtra("transaction_id") : transactionId;
            storeName = intent.getStringExtra("store_name") != null ?
                    intent.getStringExtra("store_name") : storeName;
            storeLocation = intent.getStringExtra("store_location") != null ?
                    intent.getStringExtra("store_location") : storeLocation;
            pickupTime = intent.getStringExtra("pickup_time") != null ?
                    intent.getStringExtra("pickup_time") : pickupTime;
            orderDate = intent.getStringExtra("order_date") != null ?
                    intent.getStringExtra("order_date") : orderDate;
            paymentMethod = intent.getStringExtra("payment_method") != null ?
                    intent.getStringExtra("payment_method") : paymentMethod;
            totalAmount = intent.getStringExtra("total_amount") != null ?
                    intent.getStringExtra("total_amount") : totalAmount;
        }
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> onBackPressed());

        btnViewOrder.setOnClickListener(v -> {
            Intent intent = new Intent(QrisPaymentActivity.this, BeforePaymentActivity.class);
            intent.putExtra("payment_status", "need_payment");
            intent.putExtra("transaction_id", transactionId);
            intent.putExtra("store_name", storeName);
            intent.putExtra("store_location", storeLocation);
            intent.putExtra("pickup_time", pickupTime);
            intent.putExtra("order_date", orderDate);
            intent.putExtra("payment_method", paymentMethod);
            intent.putExtra("total_amount", totalAmount);
            startActivity(intent);
        });


        if (qrCodeImage != null) {
            qrCodeImage.setOnClickListener(v -> {
                Toast.makeText(this, "Pembayaran QRIS berhasil!", Toast.LENGTH_SHORT).show();


                new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        runOnUiThread(() -> {
                            navigateToPaymentSuccess();
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            });
        }
    }

    private void navigateToPaymentSuccess() {
        Intent intent = new Intent(QrisPaymentActivity.this, PaymentBerhasilActivity.class);
        intent.putExtra("transaction_id", transactionId);
        intent.putExtra("store_name", storeName);
        intent.putExtra("store_location", storeLocation);
        intent.putExtra("pickup_time", pickupTime);
        intent.putExtra("order_date", orderDate);
        intent.putExtra("payment_method", paymentMethod);
        intent.putExtra("total_amount", totalAmount);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}