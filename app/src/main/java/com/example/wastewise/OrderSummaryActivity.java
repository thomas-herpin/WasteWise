package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrderSummaryActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnOrderNow;
    private BottomNavigationView navView;
    private TextView seeAllButton;
    private TextView paymentMethodAmount;
    private LinearLayout paymentMethodIcon;
    private LinearLayout paymentInfoLayout;

    private String currentPaymentMethod = "Ovo";
    private String currentPaymentAmount = "Rp300.000";

    private static final int PAYMENT_METHOD_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_summary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupClickListeners();
        setupBottomNavigation();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btnBack);
        btnOrderNow = findViewById(R.id.btnOrderNow);
        navView = findViewById(R.id.nav_view);

        findPaymentInformationViews();
    }

    private void findPaymentInformationViews() {
        View scrollView = findViewById(R.id.scroll_view);
        if (scrollView != null) {
            seeAllButton = findViewWithText(scrollView, "See all");
            paymentMethodAmount = findViewById(R.id.tvsaldo);
            paymentMethodIcon = findViewById(R.id.llygambarsaldo);
            paymentInfoLayout = findViewById(R.id.llysaldo);
        }
    }

    private TextView findViewWithText(View parent, String text) {
        if (parent instanceof TextView) {
            TextView textView = (TextView) parent;
            if (text.equals(textView.getText().toString())) {
                return textView;
            }
        } else if (parent instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) parent;
            for (int i = 0; i < group.getChildCount(); i++) {
                TextView result = findViewWithText(group.getChildAt(i), text);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(v -> onBackPressed());
        btnOrderNow.setOnClickListener(v -> handleOrderNow());

        if (seeAllButton != null) {
            seeAllButton.setOnClickListener(v -> openPaymentMethodActivity());
        }

        if (paymentInfoLayout != null) {
            paymentInfoLayout.setOnClickListener(v -> openPaymentMethodActivity());
        }

        if (paymentMethodAmount != null) {
            paymentMethodAmount.setOnClickListener(v -> openPaymentMethodActivity());
        }

        if (paymentMethodIcon != null) {
            paymentMethodIcon.setOnClickListener(v -> openPaymentMethodActivity());
        }
    }

    private void openPaymentMethodActivity() {
        Intent intent = new Intent(OrderSummaryActivity.this, PaymentMethodActivity.class);
        intent.putExtra("total_amount", "Rp43.000");
        intent.putExtra("order_id", "ORDER_123");
        intent.putExtra("restaurant_name", "Roti'O");
        intent.putExtra("current_payment_method", currentPaymentMethod);

        startActivityForResult(intent, PAYMENT_METHOD_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PAYMENT_METHOD_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String selectedPaymentMethod = data.getStringExtra("selected_payment_method");
                if (selectedPaymentMethod != null && !selectedPaymentMethod.equals(currentPaymentMethod)) {
                    currentPaymentMethod = selectedPaymentMethod;
                    updatePaymentMethodDisplay(selectedPaymentMethod);
                    Toast.makeText(this, "Metode pembayaran diubah ke: " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void updatePaymentMethodDisplay(String paymentMethod) {
        if (paymentMethodIcon != null) {
            int backgroundResource = getPaymentMethodBackground(paymentMethod);
            paymentMethodIcon.setBackgroundResource(backgroundResource);
        }

        if (paymentMethodAmount != null) {
            String amount = getPaymentMethodAmount(paymentMethod);
            paymentMethodAmount.setText(amount);
            currentPaymentAmount = amount;
        }
    }

    private int getPaymentMethodBackground(String paymentMethod) {
        switch (paymentMethod.toLowerCase()) {
            case "cash":
                return R.drawable.cash;
            case "ovo":
                return R.drawable.ovo;
            case "dana":
                return R.drawable.dana;
            case "gopay":
                return R.drawable.gopay;
            case "qris":
                return R.drawable.qris;
            default:
                return R.drawable.ovo;
        }
    }

    private String getPaymentMethodAmount(String paymentMethod) {
        switch (paymentMethod.toLowerCase()) {
            case "cash":
                return "Cash";
            case "ovo":
                return "Rp300.000";
            case "dana":
                return "Rp450.000";
            case "gopay":
                return "Rp250.000";
            case "qris":
                return "QRIS";
            default:
                return "Rp300.000";
        }
    }

    private void setupBottomNavigation() {
        if (navView != null) {
            navView.setOnItemSelectedListener(item -> {
                return false;
            });
        }
    }

    private void handleOrderNow() {
        // Validasi payment method
        if (currentPaymentMethod.equals("Cash")) {
            Toast.makeText(this, "Memproses pesanan dengan pembayaran tunai...", Toast.LENGTH_SHORT).show();
        } else if (currentPaymentMethod.equalsIgnoreCase("qris")) {
            Toast.makeText(this, "Memproses pesanan dengan QRIS...", Toast.LENGTH_SHORT).show();
        } else {
            // mengecek apakah saldo mencukupi untuk e-wallet
            if (isBalanceSufficient()) {
                Toast.makeText(this, "Memproses pesanan dengan " + currentPaymentMethod + "...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Saldo " + currentPaymentMethod + " tidak mencukupi!", Toast.LENGTH_LONG).show();
                return;
            }
        }
        processOrder();
    }

    private boolean isBalanceSufficient() {
        int totalAmount = 43000; // Rp43.000

        switch (currentPaymentMethod.toLowerCase()) {
            case "ovo":
                return 300000 >= totalAmount;
            case "dana":
                return 450000 >= totalAmount;
            case "gopay":
                return 250000 >= totalAmount;
            case "qris":
            case "cash":
                return true;
            default:
                return true;
        }
    }

    private void processOrder() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                runOnUiThread(() -> {
                    // Navigasi berdasarkan metode pembayaran
                    navigateToPaymentResult();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(OrderSummaryActivity.this,
                            "Pesanan gagal. Silakan coba lagi.", Toast.LENGTH_LONG).show();
                });
            }
        }).start();
    }

    private void navigateToPaymentResult() {
        Intent intent;

        // Jika metode pembayaran adalah QRIS, arahkan ke QrisPaymentActivity
        if (currentPaymentMethod.equalsIgnoreCase("qris")) {
            intent = new Intent(OrderSummaryActivity.this, QrisPaymentActivity.class);
            intent.putExtra("transaction_id", "133756");
            intent.putExtra("store_name", "Roti O");
            intent.putExtra("store_location", "Roti O - Karya");
            intent.putExtra("pickup_time", "10.00");
            intent.putExtra("order_date", "Sabtu, 28 Sep 2025");
            intent.putExtra("payment_method", currentPaymentMethod);
            intent.putExtra("total_amount", "Rp43.000");
            Toast.makeText(this, "Silakan lakukan pembayaran melalui QRIS", Toast.LENGTH_LONG).show();
        } else {
            // Untuk metode pembayaran lainnya, arahkan ke PaymentBerhasilActivity
            intent = new Intent(OrderSummaryActivity.this, PaymentBerhasilActivity.class);
            intent.putExtra("transaction_id", "133756");
            intent.putExtra("store_name", "Roti O");
            intent.putExtra("store_location", "Roti O - Karya");
            intent.putExtra("pickup_time", "10.00");
            intent.putExtra("order_date", "Sabtu, 28 Sep 2025");
            intent.putExtra("payment_method", currentPaymentMethod);
            intent.putExtra("total_amount", "Rp43.000");
            Toast.makeText(this, "Pesanan berhasil dibuat dengan " + currentPaymentMethod + "!", Toast.LENGTH_LONG).show();
        }

        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public String getCurrentPaymentMethod() {
        return currentPaymentMethod;
    }

    public String getCurrentPaymentAmount() {
        return currentPaymentAmount;
    }
}