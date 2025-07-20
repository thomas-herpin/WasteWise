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

import com.example.wastewise.model.User;

import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity {
    TextView txvSignIn;
    Button btnDaftar;
    EditText edtUsername, edtEmail, edtPassword, edtConfirmPassword;
    CheckBox cbxAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

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
        btnDaftar = findViewById(R.id.btnDaftar);
        cbxAgree = findViewById(R.id.cbxAgree);

        txvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin();
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInputValid()) {
                    registerUserToRealm();
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

    private void registerUserToRealm() {
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            User existingUser = r.where(User.class).equalTo("email", email).findFirst();
            if (existingUser == null) {
                User user = r.createObject(User.class, email);
                user.setUsername(username);
                user.setPassword(password);
                Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                toLogin();
            } else {
                Toast.makeText(this, "Email sudah terdaftar!", Toast.LENGTH_SHORT).show();
            }
        });
        realm.close();
    }

    public void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
