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

import com.example.wastewise.model.Seller;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RegisterSellerActivity extends AppCompatActivity {
    TextView txvSignIn;
    Button btnDaftarSlr;
    EditText edtUsername, edtEmail, edtPassword, edtConfirmPassword;
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
        edtUsername = findViewById(R.id.edtUsername);
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
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Ada yang belum terisi", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password minimal 6 karakter", Toast.LENGTH_SHORT).show();
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
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        Realm realm = Realm.getDefaultInstance();

        Seller existingSeller = realm.where(Seller.class).equalTo("email", email).findFirst();
        if (existingSeller != null) {
            Toast.makeText(this, "Email sudah terdaftar!", Toast.LENGTH_SHORT).show();
            realm.close();
            return;
        }

        realm.executeTransactionAsync(
                r -> {
                    Seller newSeller = r.createObject(Seller.class, email);
                    newSeller.setPassword(password);
                    newSeller.setNamaOutlet(username);
                    newSeller.setTipeOutlet("Eatery");
                    newSeller.setAlamatOutlet("");
                    newSeller.setLogoOutlet(0);

                    android.util.Log.d("RegisterSeller", "Seller registered: " + email);
                },
                () -> {
                    runOnUiThread(() -> {
                        Toast.makeText(RegisterSellerActivity.this, "Registrasi mitra berhasil!", Toast.LENGTH_SHORT).show();
                        toLoginSeller();
                    });
                },
                error -> {
                    runOnUiThread(() -> {
                        android.util.Log.e("RegisterSellerError", "Error: " + error.getMessage(), error);
                        Toast.makeText(RegisterSellerActivity.this, "Terjadi error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    });
                }
        );

        realm.close();
    }

    public void toLoginSeller() {
        Intent intent = new Intent(this, LoginSellerActivity.class);
        startActivity(intent);
        finish();
    }
}