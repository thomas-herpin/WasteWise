package com.example.wastewise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PaymentBerhasilActivity extends AppCompatActivity {

    private TextView tvTransactionId;
    private TextView tvOrder;
    private TextView tvTotal;
    private TextView tvPaymentMethod;
    private TextView tvDateTime;
    private Button btnViewOrder;

    // Data transaksi
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
        setContentView(R.layout.activity_payment_berhasil);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        initViews();


        loadDataFromIntent();


        setTransactionData();


        setClickListeners();
    }

    private void initViews() {
        tvTransactionId = findViewById(R.id.tvTransactionId);
        tvOrder = findViewById(R.id.tvOrder);
        tvTotal = findViewById(R.id.tvTotal);
        tvPaymentMethod = findViewById(R.id.tvPaymentMethod);
        tvDateTime = findViewById(R.id.tvDateTime);
        btnViewOrder = findViewById(R.id.btnViewOrder);
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

    private void setTransactionData() {
        // Set data dan waktu
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());

        if (tvDateTime != null) {
            tvDateTime.setText(currentDateTime);
        }

        if (tvTransactionId != null) {
            tvTransactionId.setText(transactionId);
        }

        if (tvOrder != null) {
            tvOrder.setText("10 items " + storeName);
        }

        if (tvTotal != null) {
            tvTotal.setText(totalAmount);
        }

        if (tvPaymentMethod != null) {
            tvPaymentMethod.setText(paymentMethod);
        }
    }

    private void setClickListeners() {
        if (btnViewOrder != null) {
            btnViewOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigasi ke AfterPaymentActivity
                    Intent intent = new Intent(PaymentBerhasilActivity.this, AfterPaymentActivity.class);
                    intent.putExtra("id_transaksi", transactionId);
                    intent.putExtra("nama_toko", storeName);
                    intent.putExtra("lokasi_toko", storeLocation);
                    intent.putExtra("waktu_ambil", pickupTime);
                    intent.putExtra("tanggal_pesan", orderDate);
                    intent.putExtra("payment_method", paymentMethod);
                    intent.putExtra("total_amount", totalAmount);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}