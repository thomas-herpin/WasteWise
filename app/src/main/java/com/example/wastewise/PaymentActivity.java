package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PaymentActivity extends AppCompatActivity {

    private CardView cardCOD, cardOVO, cardDANA, cardGoPay;
    private RadioButton radioCOD, radioOVO, radioDANA, radioGoPay;
    private Button btnPembayaran;
    private String selectedPaymentMethod = "COD"; // Default COD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initViews();
        setupClickListeners();
    }

    private void initViews() {
        // CardViews
        cardCOD = findViewById(R.id.cardCOD);
        cardOVO = findViewById(R.id.cardOVO);
        cardDANA = findViewById(R.id.cardDANA);
        cardGoPay = findViewById(R.id.cardGoPay);

        // RadioButtons
        radioCOD = findViewById(R.id.radioCOD);
        radioOVO = findViewById(R.id.radioOVO);
        radioDANA = findViewById(R.id.radioDANA);
        radioGoPay = findViewById(R.id.radioGoPay);

        // Button
        btnPembayaran = findViewById(R.id.btnPembayaran);
    }

    private void setupClickListeners() {
        cardCOD.setOnClickListener(v -> selectPaymentMethod("COD", radioCOD));
        cardOVO.setOnClickListener(v -> selectPaymentMethod("OVO", radioOVO));
        cardDANA.setOnClickListener(v -> selectPaymentMethod("DANA", radioDANA));
        cardGoPay.setOnClickListener(v -> selectPaymentMethod("GoPay", radioGoPay));

        // RadioButton listeners
        radioCOD.setOnClickListener(v -> selectPaymentMethod("COD", radioCOD));
        radioOVO.setOnClickListener(v -> selectPaymentMethod("OVO", radioOVO));
        radioDANA.setOnClickListener(v -> selectPaymentMethod("DANA", radioDANA));
        radioGoPay.setOnClickListener(v -> selectPaymentMethod("GoPay", radioGoPay));

        // Button pembayaran listener
        btnPembayaran.setOnClickListener(v -> processPayment());
    }

    private void selectPaymentMethod(String method, RadioButton selectedRadio) {
        // Clear all radio buttons
        radioCOD.setChecked(false);
        radioOVO.setChecked(false);
        radioDANA.setChecked(false);
        radioGoPay.setChecked(false);

        // Select the chosen one
        selectedRadio.setChecked(true);
        selectedPaymentMethod = method;

        // Update button text based on payment method
        updateButtonText();

        // Show toast with selected method
        Toast.makeText(this, "Metode pembayaran dipilih: " + method, Toast.LENGTH_SHORT).show();
    }

    private void updateButtonText() {
        switch (selectedPaymentMethod) {
            case "COD":
                btnPembayaran.setText("KONFIRMASI PESANAN");
                break;
            case "OVO":
            case "DANA":
            case "GoPay":
                btnPembayaran.setText("BAYAR SEKARANG");
                break;
            default:
                btnPembayaran.setText("KONFIRMASI PESANAN");
        }
    }

    private void processPayment() {
        Intent intent;

        if (selectedPaymentMethod.equals("COD")) {
            // Untuk COD, langsung ke WaitingPaymentActivity
            intent = new Intent(this, WaitingPaymentActivity.class);
            intent.putExtra("payment_method", selectedPaymentMethod);
            intent.putExtra("order_details", "5x Chitato rasa sapi panggang");
            intent.putExtra("total_amount", "Rp 20.000");
            startActivity(intent);
            finish();
        } else {
            // Untuk digital payments (OVO, DANA, GoPay), langsung ke PaymentSuccessActivity
            intent = new Intent(this, PaymentSuccessActivity.class);
            intent.putExtra("payment_method", selectedPaymentMethod);
            intent.putExtra("order_details", "5x Chitato rasa sapi panggang");
            intent.putExtra("total_amount", "Rp 20.000");
            intent.putExtra("transaction_id", generateTransactionId());
            startActivity(intent);
            finish();
        }
    }

    private String generateTransactionId() {
        // Generate simple transaction ID
        return selectedPaymentMethod.toUpperCase() + System.currentTimeMillis();
    }
}