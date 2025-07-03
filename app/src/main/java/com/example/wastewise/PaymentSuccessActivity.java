package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentSuccessActivity extends AppCompatActivity {

    private TextView tvTransactionId, tvOrderDetails, tvTotalAmount, tvPaymentMethod, tvPaymentDate;
    private Button btnBackToHome;
    private String paymentMethod;
    private String orderDetails;
    private String totalAmount;
    private String transactionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        initViews();
        getIntentData();
        setupSuccessDetails();
        setupClickListeners();
    }

    private void initViews() {
        tvTransactionId = findViewById(R.id.tv_transaction_id);
        tvOrderDetails = findViewById(R.id.tv_order_details);
        tvTotalAmount = findViewById(R.id.tv_total_amount);
        tvPaymentMethod = findViewById(R.id.tv_payment_method);
        tvPaymentDate = findViewById(R.id.tv_payment_date);
        btnBackToHome = findViewById(R.id.btn_back_to_home);

    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            paymentMethod = intent.getStringExtra("payment_method");
            orderDetails = intent.getStringExtra("order_details");
            totalAmount = intent.getStringExtra("total_amount");
            transactionId = intent.getStringExtra("transaction_id");
        }

        // Set default values
        if (paymentMethod == null) paymentMethod = "OVO";
        if (orderDetails == null) orderDetails = "5x Chitato rasa sapi panggang";
        if (totalAmount == null) totalAmount = "Rp 20.000";
        if (transactionId == null) transactionId = "TXN" + System.currentTimeMillis();
    }

    private void setupSuccessDetails() {
        tvTransactionId.setText(transactionId);
        tvOrderDetails.setText(orderDetails);
        tvTotalAmount.setText(totalAmount);
        tvPaymentMethod.setText(paymentMethod);

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault());
        String currentDateTime = sdf.format(new java.util.Date());
        tvPaymentDate.setText(currentDateTime);
    }

    private void setupClickListeners() {
        btnBackToHome.setOnClickListener(v -> backToHome());
    }

    private void backToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backToHome();
    }
}