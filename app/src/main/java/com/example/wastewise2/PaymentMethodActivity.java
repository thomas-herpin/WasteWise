package com.example.wastewise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
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

    private String selectedPaymentMethod = "Ovo"; // Default selection
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

        View rootView = findViewById(android.R.id.content);
        ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
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

            // Log atau gunakan data ini sesuai kebutuhan
            if (totalAmount != null) {
                Toast.makeText(this, "Total Pesanan: " + totalAmount, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initViews() {
        backButton = findViewById(R.id.backButton);
        paymentMethodGroup = findViewById(R.id.paymentMethodGroup);

        // Initialize radio buttons
        radioCash = findViewById(R.id.radioCash);
        radioOvo = findViewById(R.id.radioOvo);
        radioDana = findViewById(R.id.radioDana);
        radioGopay = findViewById(R.id.radioGopay);
        radioQris = findViewById(R.id.radioQris);

        // Initialize card views untuk area yang bisa diklik
        initCardViews();
    }

    private void initCardViews() {
        // Cari CardView berdasarkan ID jika tersedia, atau gunakan findViewByTag
        // Alternatif: langsung cari CardView dari root view
        View rootView = findViewById(android.R.id.content);
        findCardViewsRecursively(rootView);
    }

    private void findCardViewsRecursively(View parent) {
        if (parent instanceof CardView) {
            CardView cardView = (CardView) parent;

            // Identifikasi CardView berdasarkan RadioButton yang ada di dalamnya
            if (findRadioButtonInView(cardView, R.id.radioCash) != null) {
                cardCash = cardView;
            } else if (findRadioButtonInView(cardView, R.id.radioOvo) != null) {
                cardOvo = cardView;
            } else if (findRadioButtonInView(cardView, R.id.radioDana) != null) {
                cardDana = cardView;
            } else if (findRadioButtonInView(cardView, R.id.radioGopay) != null) {
                cardGopay = cardView;
            } else if (findRadioButtonInView(cardView, R.id.radioQris) != null) {
                cardQris = cardView;
            }
        } else if (parent instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) parent;
            for (int i = 0; i < group.getChildCount(); i++) {
                findCardViewsRecursively(group.getChildAt(i));
            }
        }
    }

    private RadioButton findRadioButtonInView(View parent, int radioButtonId) {
        if (parent instanceof RadioButton && parent.getId() == radioButtonId) {
            return (RadioButton) parent;
        } else if (parent instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) parent;
            for (int i = 0; i < group.getChildCount(); i++) {
                RadioButton result = findRadioButtonInView(group.getChildAt(i), radioButtonId);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    private void setupListeners() {
        // Back button click listener
        backButton.setOnClickListener(v -> confirmPaymentMethod());

        // Setup card click listeners untuk area yang lebih besar
        setupCardClickListeners();

        // Individual radio button click listeners
        setupRadioButtonListeners();

        // Note: Tidak menggunakan RadioGroup listener karena kita handle manual
        // untuk memastikan hanya satu yang bisa dipilih
    }

    private void setupCardClickListeners() {
        View.OnClickListener cardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all radio buttons first
                clearAllRadioButtons();

                if (v == cardCash && radioCash != null) {
                    radioCash.setChecked(true);
                    selectedPaymentMethod = "Cash";
                } else if (v == cardOvo && radioOvo != null) {
                    radioOvo.setChecked(true);
                    selectedPaymentMethod = "Ovo";
                } else if (v == cardDana && radioDana != null) {
                    radioDana.setChecked(true);
                    selectedPaymentMethod = "Dana";
                } else if (v == cardGopay && radioGopay != null) {
                    radioGopay.setChecked(true);
                    selectedPaymentMethod = "Gopay";
                } else if (v == cardQris && radioQris != null) {
                    radioQris.setChecked(true);
                    selectedPaymentMethod = "QRIS";
                }

                // Show selection info
                showPaymentMethodSelected(selectedPaymentMethod);
                showPaymentMethodInfo(selectedPaymentMethod);
            }
        };

        if (cardCash != null) cardCash.setOnClickListener(cardClickListener);
        if (cardOvo != null) cardOvo.setOnClickListener(cardClickListener);
        if (cardDana != null) cardDana.setOnClickListener(cardClickListener);
        if (cardGopay != null) cardGopay.setOnClickListener(cardClickListener);
        if (cardQris != null) cardQris.setOnClickListener(cardClickListener);
    }

    private void clearAllRadioButtons() {
        if (radioCash != null) radioCash.setChecked(false);
        if (radioOvo != null) radioOvo.setChecked(false);
        if (radioDana != null) radioDana.setChecked(false);
        if (radioGopay != null) radioGopay.setChecked(false);
        if (radioQris != null) radioQris.setChecked(false);
    }

    private void setupRadioButtonListeners() {
        View.OnClickListener radioClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear all radio buttons first
                clearAllRadioButtons();

                // Set the clicked one as checked
                RadioButton clickedRadio = (RadioButton) v;
                clickedRadio.setChecked(true);

                // Update selected payment method
                if (v == radioCash) {
                    selectedPaymentMethod = "Cash";
                } else if (v == radioOvo) {
                    selectedPaymentMethod = "Ovo";
                } else if (v == radioDana) {
                    selectedPaymentMethod = "Dana";
                } else if (v == radioGopay) {
                    selectedPaymentMethod = "Gopay";
                } else if (v == radioQris) {
                    selectedPaymentMethod = "QRIS";
                }

                // Show selection info
                showPaymentMethodSelected(selectedPaymentMethod);
                showPaymentMethodInfo(selectedPaymentMethod);
            }
        };

        if (radioCash != null) radioCash.setOnClickListener(radioClickListener);
        if (radioOvo != null) radioOvo.setOnClickListener(radioClickListener);
        if (radioDana != null) radioDana.setOnClickListener(radioClickListener);
        if (radioGopay != null) radioGopay.setOnClickListener(radioClickListener);
        if (radioQris != null) radioQris.setOnClickListener(radioClickListener);
    }

    private void setDefaultSelection() {
        // Clear all selections first
        clearAllRadioButtons();
        if (paymentMethodGroup != null) {
            paymentMethodGroup.clearCheck();
        }

        // Set default selection berdasarkan selectedPaymentMethod
        switch (selectedPaymentMethod.toLowerCase()) {
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
            default:
                if (radioOvo != null) {
                    radioOvo.setChecked(true);
                    selectedPaymentMethod = "Ovo";
                }
                break;
        }
    }

    private void handlePaymentMethodSelection(int checkedId) {
        String previousMethod = selectedPaymentMethod;

        if (checkedId == R.id.radioCash) {
            selectedPaymentMethod = "Cash";
        } else if (checkedId == R.id.radioOvo) {
            selectedPaymentMethod = "Ovo";
        } else if (checkedId == R.id.radioDana) {
            selectedPaymentMethod = "Dana";
        } else if (checkedId == R.id.radioGopay) {
            selectedPaymentMethod = "Gopay";
        } else if (checkedId == R.id.radioQris) {
            selectedPaymentMethod = "QRIS";
        }

        // Tampilkan informasi jika metode pembayaran berubah
        if (!previousMethod.equals(selectedPaymentMethod)) {
            showPaymentMethodSelected(selectedPaymentMethod);
            showPaymentMethodInfo(selectedPaymentMethod);
        }
    }

    private void showPaymentMethodSelected(String method) {
        Toast.makeText(this, "Metode pembayaran dipilih: " + method, Toast.LENGTH_SHORT).show();
    }

    private void showPaymentMethodInfo(String method) {
        String info = getPaymentMethodInfo(method);
        if (!info.isEmpty()) {
            Toast.makeText(this, info, Toast.LENGTH_LONG).show();
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

    public String getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }

    public void confirmPaymentMethod() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selected_payment_method", selectedPaymentMethod);
        resultIntent.putExtra("total_amount", totalAmount);
        resultIntent.putExtra("order_id", orderId);
        resultIntent.putExtra("restaurant_name", restaurantName);

        setResult(RESULT_OK, resultIntent);

        Toast.makeText(this, "Metode pembayaran dikonfirmasi: " + selectedPaymentMethod, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        confirmPaymentMethod();
    }
}