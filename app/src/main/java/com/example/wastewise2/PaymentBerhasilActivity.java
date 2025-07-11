package com.example.wastewise2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_berhasil);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        initViews();

        // Set data
        setTransactionData();

        // Set click listeners
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

    private void setTransactionData() {
        // Set current date and time
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());
        tvDateTime.setText(currentDateTime);

        // You can set other data here if needed
        // tvTransactionId.setText("133756"); // Already set in XML
        // tvOrder.setText("10 items Roti'O"); // Already set in XML
        // tvTotal.setText("Rp 40.000"); // Already set in XML
        // tvPaymentMethod.setText("QRIS"); // Already set in XML
    }

    private void setClickListeners() {
        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle view order button click
                Toast.makeText(PaymentBerhasilActivity.this, "Redirecting to My Orders...", Toast.LENGTH_SHORT).show();

                // TODO: Add intent to navigate to order history activity
                // Intent intent = new Intent(PaymentBerhasilActivity.this, OrderHistoryActivity.class);
                // startActivity(intent);
                // finish();
            }
        });
    }
}