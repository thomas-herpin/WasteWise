package com.example.wastewise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BeforePaymentActivity extends AppCompatActivity {

    private LinearLayout navHome, navProduct, navActivity, navAccount;
    private LinearLayout cardOrderStatus;
    private TextView tvTransactionId, tvStoreName, tvStoreLocation, tvPickupTime, tvOrderDate;
    private Button btnPayNow;

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
        setContentView(R.layout.activity_before_payment);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupClickListeners();
        loadDataFromIntent();
        updateUI();
    }

    private void initializeViews() {
        // Initialize transaction info views
        tvTransactionId = findViewById(R.id.tvTransactionId);
        tvStoreName = findViewById(R.id.tvStoreName);
        tvStoreLocation = findViewById(R.id.tvStoreLocation);
        tvPickupTime = findViewById(R.id.tvPickupTime);
        tvOrderDate = findViewById(R.id.tvOrderDate);

        cardOrderStatus = findViewById(R.id.cardOrderStatus);
    }

    private void setupClickListeners() {

        if (navHome != null) {
            navHome.setOnClickListener(v -> navigateToHome());
        }

        if (navProduct != null) {
            navProduct.setOnClickListener(v -> navigateToProduct());
        }

        if (navActivity != null) {
            navActivity.setOnClickListener(v ->
                    Toast.makeText(BeforePaymentActivity.this, "Anda sudah berada di halaman Activity", Toast.LENGTH_SHORT).show()
            );
        }

        if (navAccount != null) {
            navAccount.setOnClickListener(v -> navigateToAccount());
        }

        if (btnPayNow != null) {
            btnPayNow.setOnClickListener(v -> navigateToQrisPayment());
        }

        if (cardOrderStatus != null) {
            cardOrderStatus.setOnClickListener(v -> {
                Toast.makeText(BeforePaymentActivity.this, "Navigating to QR Payment", Toast.LENGTH_SHORT).show();
                navigateToQrisPayment();
            });
        }

        setupRecentItemsClickListeners();
    }

    private void setupRecentItemsClickListeners() {
        View.OnClickListener reviewClickListener = v -> showReviewDialog();

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

    private void updateUI() {
        if (tvTransactionId != null) {
            tvTransactionId.setText(transactionId);
        }
        if (tvStoreName != null) {
            tvStoreName.setText(storeName);
        }
        if (tvStoreLocation != null) {
            tvStoreLocation.setText(storeLocation);
        }
        if (tvPickupTime != null) {
            tvPickupTime.setText(pickupTime + " WIB");
        }
        if (tvOrderDate != null) {
            tvOrderDate.setText(orderDate);
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(BeforePaymentActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToProduct() {
        Toast.makeText(this, "Fitur Produk segera hadir", Toast.LENGTH_SHORT).show();
    }

    private void navigateToAccount() {
        Toast.makeText(this, "Fitur Akun segera hadir", Toast.LENGTH_SHORT).show();
    }

    private void showReviewDialog() {
        Toast.makeText(this, "Fitur Review", Toast.LENGTH_SHORT).show();
    }

    // Navigate back to QR Payment when green button is clicked or card is clicked
    private void navigateToQrisPayment() {
        Intent intent = new Intent(BeforePaymentActivity.this, QrisPaymentActivity.class);
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

    private void processPayment() {
        Toast.makeText(this, "Memproses pembayaran...", Toast.LENGTH_SHORT).show();
        
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                runOnUiThread(() -> {
                    Toast.makeText(BeforePaymentActivity.this,
                            "Pembayaran berhasil! Pesanan sedang dipersiapkan", Toast.LENGTH_LONG).show();

                    // Pindah ke AfterPaymentActivity setelah pembayaran berhasil
                    navigateToAfterPayment();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(BeforePaymentActivity.this,
                            "Pembayaran gagal. Silakan coba lagi.", Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }

    private void navigateToAfterPayment() {
        Intent intent = new Intent(BeforePaymentActivity.this, AfterPaymentActivity.class);
        intent.putExtra("id_transaksi", transactionId);
        intent.putExtra("nama_toko", storeName);
        intent.putExtra("lokasi_toko", storeLocation);
        intent.putExtra("waktu_ambil", pickupTime);
        intent.putExtra("tanggal_pesan", orderDate);
        intent.putExtra("payment_method", paymentMethod);
        startActivity(intent);
        finish();
    }

    private void updateTransactionStatus(String status) {
        Toast.makeText(this, "Status transaksi diperbarui: " + status, Toast.LENGTH_SHORT).show();
    }

    private void refreshRecentActivities() {
        // Refresh activities if needed
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshRecentActivities();
    }
}