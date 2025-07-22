package com.example.wastewise;

import android.content.Intent;
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

public class RegisterSellerActivity extends AppCompatActivity {
    TextView txvSignIn;
    Button btnDaftarSlr;
    EditText editTextText, edtEmail, edtPassword, edtConfirmPassword;
    CheckBox cbxAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_seller);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txvSignIn = findViewById(R.id.txvSignIn);
        editTextText = findViewById(R.id.editTextText); // Username field
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnDaftarSlr = findViewById(R.id.btnDaftarSlr);
        cbxAgree = findViewById(R.id.cbxAgree);

        txvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginSeller();
            }
        });

        btnDaftarSlr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValid()) {
                    registerSellerToRealm();
                }
            }
        });
    }

    private boolean isInputValid() {
        String username = editTextText.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Ada yang belum terisi", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!cbxAgree.isChecked()) {
            Toast.makeText(this, "Anda belum menyetujui penggunaan data", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void registerSellerToRealm() {
        String username = editTextText.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();

            SellerAuth existingSeller = realm.where(SellerAuth.class).equalTo("email", email).findFirst();
            if (existingSeller != null) {
                Toast.makeText(this, "Email sudah terdaftar!", Toast.LENGTH_SHORT).show();
                return;
            }

            realm.beginTransaction();

            SellerAuth newSeller = new SellerAuth();
            newSeller.setEmail(email);
            newSeller.setUsername(username);
            newSeller.setPassword(password);
            newSeller.setNamaOutlet(username + "'s Outlet");

            realm.copyToRealm(newSeller);
            realm.commitTransaction();

            Toast.makeText(this, "Registrasi mitra berhasil!", Toast.LENGTH_SHORT).show();
            toLoginSeller();

        } catch (Exception e) {
            android.util.Log.e("RegisterSellerError", "Error: " + e.getMessage(), e);
            if (realm != null && realm.isInTransaction()) {
                realm.cancelTransaction();
            }
            Toast.makeText(this, "Terjadi error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (realm != null && !realm.isClosed()) {
                realm.close();
            }
        }
    }

    public void toLoginSeller() {
        Intent intent = new Intent(this, LoginSellerActivity.class);
        startActivity(intent);
    }
}