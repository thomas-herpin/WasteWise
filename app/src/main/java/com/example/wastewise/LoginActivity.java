package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHome();
            }
        });

    }

    public void toRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void toHome() {
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

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_type", "buyer");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);



//        if (isValid(email, password)) {
//            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
//
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra("user_type", "buyer");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            finish();
//        } else {
//            Toast.makeText(this, "Email atau password tidak valid.", Toast.LENGTH_SHORT).show();
//        }
    }

//    private boolean isValid(String email, String password) {
//        Realm realm = Realm.getDefaultInstance();
//        User user = realm.where(User.class)
//                .equalTo("email", email)
//                .equalTo("password", password)
//                .findFirst();
//        realm.close();
//        return user != null;
//    }

}