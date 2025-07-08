package com.example.wastewise2;

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
    private TextView paymentMethodText;

    private String currentPaymentMethod = "Ovo"; // Default payment method
    private String currentPaymentAmount = "Rp300.000"; // Default amount

    private static final int PAYMENT_METHOD_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_summary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupClickListeners();
        setupBottomNavigation();
    }

    private void initializeViews() {
        btnBack = findViewById(R.id.btn_back);
        btnOrderNow = findViewById(R.id.btnOrderNow);
        navView = findViewById(R.id.nav_view);

        // Temukan TextView "See all" berdasarkan XML yang ada
        seeAllButton = findSeeAllTextView();

        // Temukan TextView untuk amount payment method
        paymentMethodAmount = findPaymentAmountTextView();

        // Inisialisasi payment method icon dan text (akan dibuat dinamis)
        findPaymentMethodViews();
    }

    private void findPaymentMethodViews() {
        // Mencari LinearLayout yang berisi icon payment method dan TextView amount
        View scrollView = findViewById(R.id.scroll_view);
        if (scrollView != null) {
            // Cari LinearLayout dengan background "@drawable/ovo"
            paymentMethodIcon = findPaymentMethodIcon(scrollView);

            // Cari TextView dengan text yang dimulai dengan "Rp"
            paymentMethodAmount = findViewWithTextStarting(scrollView, "Rp");
        }
    }

    private LinearLayout findPaymentMethodIcon(View parent) {
        if (parent instanceof LinearLayout) {
            LinearLayout layout = (LinearLayout) parent;
            // Cek apakah ini LinearLayout untuk payment method icon
            if (layout.getLayoutParams() != null &&
                    layout.getLayoutParams().width == 96 && // 32dp converted to pixels approximately
                    layout.getLayoutParams().height == 96) {
                return layout;
            }
        } else if (parent instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) parent;
            for (int i = 0; i < group.getChildCount(); i++) {
                LinearLayout result = findPaymentMethodIcon(group.getChildAt(i));
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private TextView findSeeAllTextView() {
        View paymentSection = findViewById(R.id.scroll_view);
        if (paymentSection != null) {
            return findViewWithText(paymentSection, "See all");
        }
        return null;
    }

    private TextView findPaymentAmountTextView() {
        View paymentSection = findViewById(R.id.scroll_view);
        if (paymentSection != null) {
            return findViewWithTextStarting(paymentSection, "Rp");
        }
        return null;
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

    private TextView findViewWithTextStarting(View parent, String startText) {
        if (parent instanceof TextView) {
            TextView textView = (TextView) parent;
            String text = textView.getText().toString();
            if (text.startsWith(startText) && text.contains(".")) {
                return textView;
            }
        } else if (parent instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) parent;
            for (int i = 0; i < group.getChildCount(); i++) {
                TextView result = findViewWithTextStarting(group.getChildAt(i), startText);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private void setupClickListeners() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOrderNow();
            }
        });

        // Setup listener untuk tombol "See all"
        if (seeAllButton != null) {
            seeAllButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openPaymentMethodActivity();
                }
            });
        }
    }

    private void openPaymentMethodActivity() {
        Intent intent = new Intent(OrderSummaryActivity.this, PaymentMethodActivity.class);

        // Kirim data yang diperlukan
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
        // Update icon background berdasarkan payment method
        if (paymentMethodIcon != null) {
            int backgroundResource = getPaymentMethodBackground(paymentMethod);
            paymentMethodIcon.setBackgroundResource(backgroundResource);
        }

        // Update amount berdasarkan payment method
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
        // Simulasi saldo untuk setiap payment method
        switch (paymentMethod.toLowerCase()) {
            case "cash":
                return "Tunai";
            case "ovo":
                return "Rp300.000";
            case "dana":
                return "Rp450.000";
            case "gopay":
                return "Rp250.000";
            case "qris":
                return "Tersedia";
            default:
                return "Rp300.000";
        }
    }

    private void setupBottomNavigation() {
        if (navView != null) {
            navView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                // Handle navigation
                return false;
            });
        }
    }

    private void handleOrderNow() {
        // Validasi payment method
        if (currentPaymentMethod.equals("Cash")) {
            Toast.makeText(this, "Memproses pesanan dengan pembayaran tunai...", Toast.LENGTH_SHORT).show();
        } else {
            // Cek apakah saldo mencukupi
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
        // Simulasi pengecekan saldo
        int totalAmount = 43000; // Rp43.000

        switch (currentPaymentMethod.toLowerCase()) {
            case "ovo":
                return 300000 >= totalAmount;
            case "dana":
                return 450000 >= totalAmount;
            case "gopay":
                return 250000 >= totalAmount;
            case "qris":
                return true; // QRIS selalu tersedia
            default:
                return true;
        }
    }

    private void processOrder() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);

                runOnUiThread(() -> {
                    Toast.makeText(OrderSummaryActivity.this,
                            "Pesanan berhasil dibuat dengan " + currentPaymentMethod + "!", Toast.LENGTH_LONG).show();
                    finish();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void updateOrderInfo(String restaurantName, String address,
                                String totalAmount, String itemCount) {
        // Implementation untuk update order info
    }

    // Getter untuk current payment method
    public String getCurrentPaymentMethod() {
        return currentPaymentMethod;
    }

    public String getCurrentPaymentAmount() {
        return currentPaymentAmount;
    }
}