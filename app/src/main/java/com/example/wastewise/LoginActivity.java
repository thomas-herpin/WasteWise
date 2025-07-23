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

import com.example.wastewise.model.User;

import io.realm.Realm;

public class LoginActivity extends AppCompatActivity {
    TextView txvSignUp;
    Button btnLogin;
    EditText edtEmail, edtPassword;
    CheckBox cbxRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txvSignUp = findViewById(R.id.txvSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        cbxRemember = findViewById(R.id.cbxRemember);

        loadRememberedCredentials();

        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void loadRememberedCredentials() {
        SharedPreferences prefs = getSharedPreferences("buyer_login", MODE_PRIVATE);
        boolean rememberMe = prefs.getBoolean("remember_me", false);

        if (rememberMe) {
            String savedEmail = prefs.getString("email", "");
            String savedPassword = prefs.getString("password", "");

            edtEmail.setText(savedEmail);
            edtPassword.setText(savedPassword);
            cbxRemember.setChecked(true);
        }
    }

    public void toRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
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

        String username = validateAndGetUsername(email, password);
        if (username != null) {
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
            toHome(email, password);
        } else {
            Toast.makeText(this, "Email atau password tidak valid.", Toast.LENGTH_SHORT).show();
        }
    }

    private String validateAndGetUsername(String email, String password) {
        Realm realm = null;
        String username = null;

        try {
            realm = Realm.getDefaultInstance();

            User user = realm.where(User.class)
                    .equalTo("email", email)
                    .equalTo("password", password)
                    .findFirst();

            if (user != null) {
                username = user.getUsername();
                android.util.Log.d("LoginSuccess", "User found: " + username);
            } else {
                android.util.Log.d("LoginFailed", "No user found with email: " + email);
            }

        } catch (Exception e) {
            android.util.Log.e("LoginError", "Error during login: " + e.getMessage(), e);
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }

        return username;
    }

    private void toHome(String email, String username) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_type", "buyer");
        SharedPreferences prefs = getSharedPreferences("login_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("email", email);
        editor.apply();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}