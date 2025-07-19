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

public class SettingSellerActivity extends AppCompatActivity {

    ImageView btnBack;
    LinearLayout btnLanguage, btnPrivacyPolicy;

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

    }

    public void init() {
        btnBack = findViewById(R.id.btnBack);
        btnLanguage = findViewById(R.id.btnLanguage);;
        btnPrivacyPolicy = findViewById(R.id.btnPrivacyPolicy);
    }

    public void toProfile() {
        Intent intent = new Intent(this, ProfileSellerActivity.class);
        startActivity(intent);
    }

    public void toLanguage() {
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
    }

    public void toPrivacyPolicy() {
        Intent intent = new Intent(this, PrivacyPolicyActivity.class);
        startActivity(intent);
    }

}