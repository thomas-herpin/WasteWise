package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WaitingPaymentActivity extends AppCompatActivity {

    private TextView tvOrderDetails, tvTotalAmount, tvPaymentMethod;
    private Button btnKonfirmasi;
    private String paymentMethod;
    private String orderDetails;
    private String totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_payment);

        initViews();
        getIntentData();
        setupOrderDetails();
        setupClickListeners();
    }

    private void initViews() {
        tvOrderDetails = findViewById(R.id.tv_order_details);
        tvTotalAmount = findViewById(R.id.tv_total_amount);
        tvPaymentMethod = findViewById(R.id.tv_payment_method);
        btnKonfirmasi = findViewById(R.id.btnKonfirmasi);
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            paymentMethod = intent.getStringExtra("payment_method");
            orderDetails = intent.getStringExtra("order_details");
            totalAmount = intent.getStringExtra("total_amount");
        }

        if (paymentMethod == null) paymentMethod = "COD";
        if (orderDetails == null) orderDetails = "5x Chitato rasa sapi panggang";
        if (totalAmount == null) totalAmount = "Rp 20.000";
    }

    private void setupOrderDetails() {
        tvOrderDetails.setText(orderDetails);
        tvTotalAmount.setText(totalAmount);

        String paymentMethodDisplay;
        switch (paymentMethod) {
            case "COD":
                paymentMethodDisplay = "Cash on Delivery (COD)";
                break;
            case "OVO":
                paymentMethodDisplay = "OVO";
                break;
            case "DANA":
                paymentMethodDisplay = "DANA";
                break;
            case "GoPay":
                paymentMethodDisplay = "GoPay";
                break;
            default:
                paymentMethodDisplay = "Cash on Delivery (COD)";
        }
        tvPaymentMethod.setText(paymentMethodDisplay);
    }

    private void setupClickListeners() {
        btnKonfirmasi.setOnClickListener(v -> backToHome());
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