package com.example.wastewise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wastewise.model.User;
import com.example.wastewise.ui.account.AccountFragment;

import io.realm.Realm;

public class PersonalDetailActivity extends AppCompatActivity {

    ImageView btnBack;
    EditText edtFullName, edtUsername, edtEmail, edtNomor, edtTglLahir;
    RadioGroup rdgGender;
    RadioButton rdbPria, rdbWanita;
    Button btnSubmit;
    Realm realm;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        realm = Realm.getDefaultInstance();
        prefs = getSharedPreferences("login_prefs", MODE_PRIVATE);

        init();

        btnBack.setOnClickListener(v -> {
            finish();
        });

        loadDatafromRealm();

        btnSubmit.setOnClickListener(v -> {
            saveChanges();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }

    public void init() {
        btnBack = findViewById(R.id.btnBack);
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtFullName = findViewById(R.id.edtFullName);
        rdgGender = findViewById(R.id.rdgGender);
        rdbPria = findViewById(R.id.rdbPria);
        rdbWanita = findViewById(R.id.rdbWanita);
        edtNomor = findViewById(R.id.edtNomor);
        edtTglLahir = findViewById(R.id.edtTglLahir);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    public void toProfile() {
        Intent intent = new Intent(this, AccountFragment.class);
        startActivity(intent);
    }

    public void loadDatafromRealm(){
        String email = prefs.getString("email", null);
        if (email != null) {
            User user = realm.where(User.class)
                    .equalTo("email", email)
                    .findFirst();

            if (user != null) {
                if (user.getFullName() != null) {
                    edtFullName.setText(user.getFullName());
                } else {
                    edtFullName.setText("");
                }

                if (user.getUsername() != null) {
                    edtUsername.setText(user.getUsername());
                } else {
                    edtUsername.setText("");
                }

                if (user.getEmail() != null) {
                    edtEmail.setText(user.getEmail());
                } else {
                    edtEmail.setText("");
                }

                if (user.getPhone() != null) {
                    edtNomor.setText(user.getPhone());
                } else {
                    edtNomor.setText("");
                }

                if (user.getDob() != null) {
                    edtTglLahir.setText(user.getDob());
                } else {
                    edtTglLahir.setText("");
                }

                if ("Pria".equals(user.getGender())) {
                    rdbPria.setChecked(true);
                } else if ("Wanita".equals(user.getGender())) {
                    rdbWanita.setChecked(true);
                }

                edtEmail.setEnabled(false);
            } else {
                Log.e("REALM_ERROR", "User tidak ditemukan untuk email: " + email);
                Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("REALM_ERROR", "Email tidak ditemukan di SharedPreferences");
            Toast.makeText(this, "Session tidak valid", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveChanges(){
        String emailSubmit = prefs.getString("email", null);

        if (emailSubmit == null) {
            Toast.makeText(this, "Session tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        String newFullName = edtFullName.getText().toString().trim();
        String newUsername = edtUsername.getText().toString().trim();
        String newPhone = edtNomor.getText().toString().trim();
        String newDob = edtTglLahir.getText().toString().trim();

        if (newFullName.isEmpty() || newUsername.isEmpty()) {
            Toast.makeText(this, "Nama lengkap dan username tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        realm.executeTransactionAsync(
                r -> {
                    User user = r.where(User.class).equalTo("email", emailSubmit).findFirst();
                    if (user != null) {
                        // Update data (kecuali email karena itu primary key)
                        user.setFullName(newFullName);
                        user.setUsername(newUsername);
                        user.setPhone(newPhone);
                        user.setDob(newDob);

                        if (rdbPria.isChecked()) {
                            user.setGender("Pria");
                        } else if (rdbWanita.isChecked()) {
                            user.setGender("Wanita");
                        }

                        Log.d("REALM_UPDATE", "Data berhasil diupdate untuk email: " + emailSubmit);
                        finish();
                    } else {
                        Log.e("REALM_ERROR", "User tidak ditemukan untuk email: " + emailSubmit);
                    }
                },
                () -> {
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                },
                error -> {
                    runOnUiThread(() -> {
                        Log.e("REALM_ERROR", "Error saat menyimpan data: " + error.getMessage());
                        Toast.makeText(this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                    });
                }
        );
    }
}