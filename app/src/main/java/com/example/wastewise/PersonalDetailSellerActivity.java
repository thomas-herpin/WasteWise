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

import com.example.wastewise.model.Seller;
import com.example.wastewise.ui.account.AccountFragment;

import io.realm.Realm;

public class PersonalDetailSellerActivity extends AppCompatActivity {

    ImageView btnBack, btnNotif;
    EditText edtOutletName, edtJamBuka, edtJamTutup, edtNumber, edtEmail, edtAddress;
    RadioGroup rdgProductType;
    RadioButton rdbEatery, rdbRetailer;
    Button btnSubmit;
    Realm realm;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_detail_seller);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        realm = Realm.getDefaultInstance();
        prefs = getSharedPreferences("login_seller_prefs", MODE_PRIVATE);

        init();

        btnBack.setOnClickListener(v -> {
            finish();
        });

        loadDataFromRealm();

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
        btnNotif = findViewById(R.id.btnNotif);
        edtOutletName = findViewById(R.id.edtOutletName);
        edtJamBuka = findViewById(R.id.edtJamBuka);
        edtJamTutup = findViewById(R.id.edtJamTutup);
        rdgProductType = findViewById(R.id.rdgProductType);
        rdbEatery = findViewById(R.id.rdbEatery);
        rdbRetailer = findViewById(R.id.rdbRetailer);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);
        edtAddress = findViewById(R.id.edtAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
    }

    public void loadDataFromRealm() {
        String email = prefs.getString("email_seller", null);
        if (email != null) {
            Seller seller = realm.where(Seller.class)
                    .equalTo("email", email)
                    .findFirst();

            if (seller != null) {
                if (seller.getEmail() != null) {
                    edtEmail.setText(seller.getEmail());
                } else {
                    edtEmail.setText("");
                }

                if (seller.getNamaOutlet() != null) {
                    edtOutletName.setText(seller.getNamaOutlet());
                } else {
                    edtOutletName.setText("");
                }

                if (seller.getJamBuka() != null) {
                    edtJamBuka.setText(seller.getJamBuka());
                } else {
                    edtJamBuka.setText("");
                }

                if (seller.getJamTutup() != null) {
                    edtJamTutup.setText(seller.getJamTutup());
                } else {
                    edtJamTutup.setText("");
                }

                if (seller.getTipeOutlet() != null) {
                    if ("Eatery".equals(seller.getTipeOutlet())) {
                        rdbEatery.setChecked(true);
                    } else if ("Retailer".equals(seller.getTipeOutlet())) {
                        rdbRetailer.setChecked(true);
                    }
                }

                if (seller.getPhoneNumber() != null) {
                    edtNumber.setText(seller.getPhoneNumber());
                } else {
                    edtNumber.setText("");
                }

                if (seller.getAlamatOutlet() != null) {
                    edtAddress.setText(seller.getAlamatOutlet());
                } else {
                    edtAddress.setText("");
                }

                edtEmail.setEnabled(false);
            } else {
                Log.e("REALM_ERROR", "Seller tidak ditemukan untuk email: " + email);
                Toast.makeText(this, "Seller tidak ditemukan", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("REALM_ERROR", "Email tidak ditemukan di SharedPreferences");
            Toast.makeText(this, "Session tidak valid", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveChanges() {
        String emailSubmit = prefs.getString("email_seller", null);

        if (emailSubmit == null) {
            Toast.makeText(this, "Session tidak valid", Toast.LENGTH_SHORT).show();
            return;
        }

        String newOutletName = edtOutletName.getText().toString().trim();
        String newJamBuka = edtJamBuka.getText().toString().trim();
        String newJamTutup = edtJamTutup.getText().toString().trim();
        String newNumber = edtNumber.getText().toString().trim();
        String newAddress = edtAddress.getText().toString().trim();

        if (newOutletName.isEmpty()) {
            Toast.makeText(this, "Nama outlet tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedProductType = "";
        if (rdbEatery.isChecked()) {
            selectedProductType = "Eatery";
        } else if (rdbRetailer.isChecked()) {
            selectedProductType = "Retailer";
        }

        final String finalProductType = selectedProductType;

        realm.executeTransactionAsync(
                r -> {
                    Seller seller = r.where(Seller.class).equalTo("email", emailSubmit).findFirst();
                    if (seller != null) {
                        seller.setNamaOutlet(newOutletName);
                        seller.setJamBuka(newJamBuka);
                        seller.setJamTutup(newJamTutup);
                        seller.setTipeOutlet(finalProductType);
                        seller.setPhoneNumber(newNumber);
                        seller.setAlamatOutlet(newAddress);

                        Log.d("REALM_UPDATE", "Data seller berhasil diupdate untuk email: " + emailSubmit);
                    } else {
                        Log.e("REALM_ERROR", "Seller tidak ditemukan untuk email: " + emailSubmit);
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