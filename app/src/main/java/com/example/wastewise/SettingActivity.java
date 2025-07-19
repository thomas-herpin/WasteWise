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

public class SettingActivity extends AppCompatActivity {
    ImageView btnBack;
    LinearLayout btnLanguage, btnAboutUs, btnPrivacyPolicy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting);
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

        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toAboutUs();
            }
        });

        btnPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPrivacyPolicy();
            }
        });

    }

    public void init(){
        btnBack = findViewById(R.id.btnBack);
        btnLanguage = findViewById(R.id.btnLanguage);
        btnAboutUs = findViewById(R.id.btnAboutUs);
        btnPrivacyPolicy = findViewById(R.id.btnPrivacyPolicy);
    }

    public void toProfile(){
        Intent intent = new Intent(this, AccountFragment.class);
        startActivity(intent);
    }

    public void toLanguage(){
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
    }

    public void toAboutUs(){
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
    }

    public void toPrivacyPolicy(){
        Intent intent = new Intent(this, PrivacyPolicyActivity.class);
        startActivity(intent);
    }

}