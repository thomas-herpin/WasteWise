package com.example.wastewise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView;

public class PaymentMethodActivity extends AppCompatActivity {

    private ImageView backButton;
    private RadioGroup paymentMethodGroup;
    private RadioButton radioCash, radioOvo, radioDana, radioGopay, radioQris;
    private CardView cardCash, cardOvo, cardDana, cardGopay, cardQris;

    private String selectedPaymentMethod = "OVO";
    private String totalAmount;
    private String orderId;
    private String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);

        receiveIntentData();
        initViews();
        setupListeners();
        setDefaultSelection();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void receiveIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            totalAmount = intent.getStringExtra("total_amount");
            orderId = intent.getStringExtra("order_id");
            restaurantName = intent.getStringExtra("restaurant_name");
            String currentPaymentMethod = intent.getStringExtra("current_payment_method");

            if (currentPaymentMethod != null) {
                selectedPaymentMethod = currentPaymentMethod;
            }
        }
    }

    private void initViews() {
        backButton = findViewById(R.id.backButton);
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);


        radioCash = findViewById(R.id.radioCash);
        radioOvo = findViewById(R.id.radioOvo);
        radioDana = findViewById(R.id.radioDana);
        radioGopay = findViewById(R.id.radioGopay);
        radioQris = findViewById(R.id.radioQris);

        findCardViews();
    }

    private void findCardViews() {
        // Cari CardView berdasarkan LinearLayout ID yang ada di XML
        View cashLayout = findViewById(R.id.llycash);
        View ovoLayout = findViewById(R.id.llyovo);
        View danaLayout = findViewById(R.id.llydana);
        View gopayLayout = findViewById(R.id.llygopay);
        View qrisLayout = findViewById(R.id.llyqris);

        cardCash = getCardViewParent(cashLayout);
        cardOvo = getCardViewParent(ovoLayout);
        cardDana = getCardViewParent(danaLayout);
        cardGopay = getCardViewParent(gopayLayout);
        cardQris = getCardViewParent(qrisLayout);
    }

    private CardView getCardViewParent(View child) {
        if (child == null) return null;

        View parent = (View) child.getParent();
        while (parent != null) {
            if (parent instanceof CardView) {
                return (CardView) parent;
            }
            parent = (View) parent.getParent();
        }
        return null;
    }

    private void setupListeners() {

        backButton.setOnClickListener(v -> confirmPaymentMethod());

        setupPaymentMethodListeners();
    }

    private void setupPaymentMethodListeners() {
        // Setup untuk setiap payment method
        setupSinglePaymentMethod(cardCash, radioCash, "Cash");
        setupSinglePaymentMethod(cardOvo, radioOvo, "OVO");
        setupSinglePaymentMethod(cardDana, radioDana, "Dana");
        setupSinglePaymentMethod(cardGopay, radioGopay, "Gopay");
        setupSinglePaymentMethod(cardQris, radioQris, "QRIS");
    }

    private void setupSinglePaymentMethod(CardView card, RadioButton radio, String methodName) {
        View.OnClickListener clickListener = v -> {
            selectPaymentMethod(methodName);
            showPaymentMethodInfo(methodName);
        };

        // Set listener radio button
        if (card != null) {
            card.setOnClickListener(clickListener);
        }
        if (radio != null) {
            radio.setOnClickListener(clickListener);
        }
    }

    private void selectPaymentMethod(String method) {

        clearAllRadioButtons();

        selectedPaymentMethod = method;

        // Update radio button yang sesuai
        switch (method.toLowerCase()) {
            case "cash":
                if (radioCash != null) radioCash.setChecked(true);
                break;
            case "ovo":
                if (radioOvo != null) radioOvo.setChecked(true);
                break;
            case "dana":
                if (radioDana != null) radioDana.setChecked(true);
                break;
            case "gopay":
                if (radioGopay != null) radioGopay.setChecked(true);
                break;
            case "qris":
                if (radioQris != null) radioQris.setChecked(true);
                break;
        }
    }

    private void clearAllRadioButtons() {
        if (radioCash != null) radioCash.setChecked(false);
        if (radioOvo != null) radioOvo.setChecked(false);
        if (radioDana != null) radioDana.setChecked(false);
        if (radioGopay != null) radioGopay.setChecked(false);
        if (radioQris != null) radioQris.setChecked(false);
    }

    private void setDefaultSelection() {
        selectPaymentMethod(selectedPaymentMethod);
    }

    private void showPaymentMethodInfo(String method) {
        String info = getPaymentMethodInfo(method);
        if (!info.isEmpty()) {
            Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
        }
    }

    private String getPaymentMethodInfo(String method) {
        switch (method.toLowerCase()) {
            case "cash":
                return "Pembayaran tunai saat pesanan tiba";
            case "ovo":
                return "Saldo OVO: Rp300.000";
            case "dana":
                return "Saldo DANA: Rp450.000";
            case "gopay":
                return "Saldo GoPay: Rp250.000";
            case "qris":
                return "Pembayaran melalui QR Code";
            default:
                return "";
        }
    }

    private void confirmPaymentMethod() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selected_payment_method", selectedPaymentMethod);
        resultIntent.putExtra("total_amount", totalAmount);
        resultIntent.putExtra("order_id", orderId);
        resultIntent.putExtra("restaurant_name", restaurantName);

        setResult(RESULT_OK, resultIntent);
        Toast.makeText(this, "Metode pembayaran: " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        confirmPaymentMethod();
    }

    public String getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }
}