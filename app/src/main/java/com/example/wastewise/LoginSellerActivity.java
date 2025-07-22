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

import com.example.wastewise.model.Seller;

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

        initViews();
        loadRememberedCredentials();
        setClickListeners();
    }

    private void initViews() {
        txvSignUp = findViewById(R.id.txvSignUp);
        btnLoginSlr = findViewById(R.id.btnLoginSlr);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        cbxRemember = findViewById(R.id.cbxRemember);
    }

    private void setClickListeners() {
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

    private void saveSellerSession(Seller seller) {
        SharedPreferences prefs = getSharedPreferences("seller_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("email", seller.getEmail());
        editor.putString("username", seller.getUsername());
        editor.putString("nama_outlet", seller.getNamaOutlet());
        editor.putString("tipe_outlet", seller.getTipeOutlet());
        editor.putString("alamat_outlet", seller.getAlamatOutlet());
        editor.putInt("logo_outlet", seller.getLogoOutlet());
        editor.putBoolean("is_logged_in", true);
        editor.putString("user_type", "seller");

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
            edtEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Format email tidak valid.");
            edtEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            edtPassword.setError("Password belum terisi.");
            edtPassword.requestFocus();
            return;
        }

        validateSeller(email, password);
    }

    private void validateSeller(String email, String password) {
        Realm realm = Realm.getDefaultInstance();

        try {
            Seller foundSeller = realm.where(Seller.class)
                    .equalTo("email", email)
                    .equalTo("password", password)
                    .findFirst();

            if (foundSeller != null) {
                // Create unmanaged copy of seller data
                Seller seller = new Seller();
                seller.setEmail(foundSeller.getEmail());
                seller.setUsername(foundSeller.getUsername());
                seller.setNamaOutlet(foundSeller.getNamaOutlet());
                seller.setTipeOutlet(foundSeller.getTipeOutlet());
                seller.setAlamatOutlet(foundSeller.getAlamatOutlet());
                seller.setLogoOutlet(foundSeller.getLogoOutlet());

                android.util.Log.d("SellerLoginSuccess", "Seller found: " + seller.getUsername());

                saveCredentials(email, password);

                saveSellerSession(seller);

                Toast.makeText(this, "Login mitra berhasil!", Toast.LENGTH_SHORT).show();
                toSellerDashboard(seller);

            } else {
                android.util.Log.d("SellerLoginFailed", "No seller found with email: " + email);
                Toast.makeText(this, "Email atau password tidak valid.", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            android.util.Log.e("SellerLoginError", "Error during login: " + e.getMessage(), e);
            Toast.makeText(this, "Terjadi kesalahan saat login: " + e.getMessage(), Toast.LENGTH_LONG).show();

            btnLoginSlr.setEnabled(true);
            btnLoginSlr.setText("Masuk");

        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }
    }

    private void toSellerDashboard(Seller seller) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_type", "seller");

        SharedPreferences prefs = getSharedPreferences("login_seller_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email_seller", seller.getEmail());
        editor.apply();

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}