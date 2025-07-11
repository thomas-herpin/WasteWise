package com.example.wastewise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BeforePaymentActivity extends AppCompatActivity {

    private LinearLayout navHome, navProduct, navActivity, navAccount;

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


        setupClickListeners();

        loadData();
    }


    private void setupClickListeners() {
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });

        navProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToProduct();
            }
        });

        navActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BeforePaymentActivity.this, "Already in Activity", Toast.LENGTH_SHORT).show();
            }
        });

        navAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAccount();
            }
        });

        setupRecentItemsClickListeners();
    }

    private void setupRecentItemsClickListeners() {
        View.OnClickListener reviewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReviewDialog();
            }
        };
    }

    private void loadData() {
        Intent intent = getIntent();
        if (intent != null) {
            String transactionId = intent.getStringExtra("transaction_id");
            if (transactionId != null) {

            }
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(BeforePaymentActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToProduct() {
        Toast.makeText(this, "Product feature coming soon", Toast.LENGTH_SHORT).show();
    }

    private void navigateToAccount() {
        Toast.makeText(this, "Account feature coming soon", Toast.LENGTH_SHORT).show();
    }

    private void showReviewDialog() {
        Toast.makeText(this, "Review functionality", Toast.LENGTH_SHORT).show();
    }

    private void processPayment() {
        Toast.makeText(this, "Processing payment...", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Payment feature coming soon", Toast.LENGTH_SHORT).show();
    }

    private void updateTransactionStatus(String status) {
        Toast.makeText(this, "Transaction status updated: " + status, Toast.LENGTH_SHORT).show();
    }

    private void refreshRecentActivities() {
        Toast.makeText(this, "Refreshing activities...", Toast.LENGTH_SHORT).show();
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