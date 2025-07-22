package com.example.wastewise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wastewise.model.SellerAuth;

import io.realm.Realm;

public class LoginSellerActivity extends AppCompatActivity {
    TextView txvSignUp;
    Button btnLoginSlr;
    EditText edtEmail, edtPassword;
    CheckBox cbxRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_seller);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txvSignUp = findViewById(R.id.txvSignUp);
        btnLoginSlr = findViewById(R.id.btnLoginSlr);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        cbxRemember = findViewById(R.id.cbxRemember);

        loadRememberedCredentials();

        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegisterSeller();
            }
        });

        btnLoginSlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void loadRememberedCredentials() {
        SharedPreferences prefs = getSharedPreferences("seller_login", MODE_PRIVATE);
        boolean rememberMe = prefs.getBoolean("remember_me", false);

        if (rememberMe) {
            String savedEmail = prefs.getString("email", "");
            String savedPassword = prefs.getString("password", "");

            edtEmail.setText(savedEmail);
            edtPassword.setText(savedPassword);
            cbxRemember.setChecked(true);
        }
    }

    private void saveCredentials(String email, String password) {
        SharedPreferences prefs = getSharedPreferences("seller_login", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (cbxRemember.isChecked()) {
            editor.putString("email", email);
            editor.putString("password", password);
            editor.putBoolean("remember_me", true);
        } else {
            editor.clear();
        }

        editor.apply();
    }

    public void toRegisterSeller() {
        Intent intent = new Intent(this, RegisterSellerActivity.class);
        startActivity(intent);
    }

    private void performLogin() {
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (email.isEmpty()) {
            edtEmail.setError("Email belum terisi.");
            return;
        }

        if (password.isEmpty()) {
            edtPassword.setError("Password belum terisi.");
            return;
        }

        SellerAuth seller = validateSeller(email, password);
        if (seller != null) {
            saveCredentials(email, password);

            Toast.makeText(this, "Login mitra berhasil!", Toast.LENGTH_SHORT).show();
            toSellerDashboard(seller);
        } else {
            Toast.makeText(this, "Email atau password tidak valid.", Toast.LENGTH_SHORT).show();
        }
    }

    private SellerAuth validateSeller(String email, String password) {
        Realm realm = null;
        SellerAuth seller = null;

        try {
            realm = Realm.getDefaultInstance();

            SellerAuth foundSeller = realm.where(SellerAuth.class)
                    .equalTo("email", email)
                    .equalTo("password", password)
                    .findFirst();

            if (foundSeller != null) {
                // Copy seller data to avoid managed object issues
                seller = new SellerAuth();
                seller.setEmail(foundSeller.getEmail());
                seller.setUsername(foundSeller.getUsername());
                seller.setNamaOutlet(foundSeller.getNamaOutlet());

                android.util.Log.d("SellerLoginSuccess", "Seller found: " + seller.getUsername());
            } else {
                android.util.Log.d("SellerLoginFailed", "No seller found with email: " + email);
            }

        } catch (Exception e) {
            android.util.Log.e("SellerLoginError", "Error during login: " + e.getMessage(), e);
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }

        return seller;
    }

    private void toSellerDashboard(SellerAuth seller) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_type", "seller");
        intent.putExtra("seller_email", seller.getEmail());
        intent.putExtra("seller_username", seller.getUsername());
        intent.putExtra("seller_outlet", seller.getNamaOutlet());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}