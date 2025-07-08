package com.example.wastewise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BeforePaymentActivity extends AppCompatActivity {

    // UI Components
    private TextView tvHeader;
    private TextView tvRotiTitle;
    private TextView tvTransactionId;
    private LinearLayout navHome, navProduct, navActivity, navAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_before_payment);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        initViews();

        // Set up click listeners
        setupClickListeners();

        // Load data (if any)
        loadData();
    }

    private void initViews() {
        // Header components
        tvHeader = findViewById(R.id.tv_header);
        tvRotiTitle = findViewById(R.id.tv_roti_title);
        tvTransactionId = findViewById(R.id.tv_transaction_id);

        // Bottom navigation
        navHome = findViewById(R.id.nav_home);
        navProduct = findViewById(R.id.nav_product);
        navActivity = findViewById(R.id.nav_activity);
        navAccount = findViewById(R.id.nav_account);
    }

    private void setupClickListeners() {
        // Bottom navigation click listeners
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
                // Already in Activity screen
                Toast.makeText(BeforePaymentActivity.this, "Already in Activity", Toast.LENGTH_SHORT).show();
            }
        });

        navAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAccount();
            }
        });

        // Click listener for recent items (Review buttons)
        setupRecentItemsClickListeners();
    }

    private void setupRecentItemsClickListeners() {
        // You can add click listeners for recent items here
        // For example, if you want to handle review button clicks:

        // Find all review TextViews and set click listeners
        // This is a simplified approach - you might want to use RecyclerView for better performance
        View.OnClickListener reviewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReviewDialog();
            }
        };

        // Add click listeners to review buttons if needed
        // findViewById(R.id.review_button_1).setOnClickListener(reviewClickListener);
    }

    private void loadData() {
        // Load any data from Intent extras or database
        Intent intent = getIntent();
        if (intent != null) {
            // Example: Load transaction data
            String transactionId = intent.getStringExtra("transaction_id");
            if (transactionId != null) {
                // Update transaction ID if provided
                // tvTransactionId.setText(transactionId);
            }
        }
    }

    private void navigateToHome() {
        Intent intent = new Intent(BeforePaymentActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToProduct() {
        Intent intent = new Intent(BeforePaymentActivity.this, ProductActivity.class);
        startActivity(intent);
        finish();
    }

    private void navigateToAccount() {
        Intent intent = new Intent(BeforePaymentActivity.this, AccountActivity.class);
        startActivity(intent);
        finish();
    }

    private void showReviewDialog() {
        // Show review dialog or navigate to review screen
        Toast.makeText(this, "Review functionality", Toast.LENGTH_SHORT).show();
        // You can implement a dialog or navigate to review activity
    }

    // Method to handle payment process
    private void processPayment() {
        // Implement payment processing logic
        Toast.makeText(this, "Processing payment...", Toast.LENGTH_SHORT).show();

        // You can add payment logic here
        // For example, navigate to payment activity
        Intent paymentIntent = new Intent(BeforePaymentActivity.this, PaymentActivity.class);
        startActivity(paymentIntent);
    }

    // Method to update transaction status
    private void updateTransactionStatus(String status) {
        // Update transaction status in database or shared preferences
        Toast.makeText(this, "Transaction status updated: " + status, Toast.LENGTH_SHORT).show();
    }

    // Method to refresh recent activities
    private void refreshRecentActivities() {
        // Refresh recent activities list
        // This could involve making API calls or database queries
        Toast.makeText(this, "Refreshing activities...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        // Handle back button press
        super.onBackPressed();
        // You can add custom back button behavior here
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data when activity resumes
        refreshRecentActivities();
    }
}