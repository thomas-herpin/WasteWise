package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileSellerActivity extends AppCompatActivity {

    LinearLayout btnPersonalDetailOutlet, btnSettingsSeller, btnBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_seller);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        btnPersonalDetailOutlet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toOutletDetail();
            }
        });

        btnSettingsSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSettings();
            }
        });

        btnBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toBank();
            }
        });

    }

    public void init(){
        btnPersonalDetailOutlet = btnPersonalDetailOutlet.findViewById(R.id.btnPersonalDetailOutlet);
        btnSettingsSeller = btnSettingsSeller.findViewById(R.id.btnSettingsSeller);
        btnBank = btnPersonalDetailOutlet.findViewById(R.id.btnBank);
    }

    public void toOutletDetail(){
        Intent intent = new Intent(this, PersonalDetailSellerActivity.class);
        startActivity(intent);
    }

    public void toSettings(){
        Intent intent = new Intent(this, SettingSellerActivity.class);
        startActivity(intent);
    }

    public void toBank(){
        Intent intent = new Intent(this, BankAccountActivity.class);
        startActivity(intent);
    }
}
