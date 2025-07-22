package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wastewise.ui.account.AccountFragment;
import com.example.wastewise.ui.account.AccountSellerFragment;
import com.example.wastewise.ui.home.HomeFragment;

public class SettingSellerActivity extends AppCompatActivity {

    ImageView btnBack;
    LinearLayout btnLanguage, btnPrivacyPolicy, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting_seller);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfile();
            }
        });

        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLanguage();
            }
        });

        btnPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPrivacyPolicy();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toExit();
            }
        });

    }

    public void init() {
        btnBack = findViewById(R.id.btnBack);
        btnLanguage = findViewById(R.id.btnLanguage);;
        btnPrivacyPolicy = findViewById(R.id.btnPrivacyPolicy);
        btnLogout = findViewById(R.id.btnLogout);
    }

    public void toProfile() {
//        Intent intent = new Intent(this, AccountSellerFragment.class);
//        startActivity(intent);
        finish();
    }

    public void toLanguage() {
        Intent intent = new Intent(this, LanguageSellerActivity.class);
        startActivity(intent);
    }

    public void toPrivacyPolicy() {
        Intent intent = new Intent(this, PrivacyPolicySellerActivity.class);
        startActivity(intent);
    }

    public void toExit(){
        Intent intent = new Intent(this, LandingPageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}