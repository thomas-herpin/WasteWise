package edu.uph.m23si2.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {
    LinearLayout btnPersonalDetail, btnSettings, btnMyOutlet, btnContactUs, btnFAQs;

    ImageView btnNotif, btnBack;

    CardView crvPersonalDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        btnPersonalDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPersonalDetail();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSetting();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfile();
            }
        });
    }

    public void init(){
        btnPersonalDetail = findViewById(R.id.btnPersonalDetail);
        btnSettings = findViewById(R.id.btnSettings);
        btnMyOutlet = findViewById(R.id.btnMyOutlet);
        btnContactUs = findViewById(R.id.btnContactUs);
        btnFAQs = findViewById(R.id.btnFAQs);
        btnNotif = findViewById(R.id.btnNotif);
        btnBack = findViewById(R.id.btnBack);
        crvPersonalDetail = findViewById(R.id.crvPersonalDetail1);
    }

    public void toPersonalDetail(){
        Intent intent = new Intent(this, PersonalDetailActivity.class);
        startActivity(intent);
    }

    public void toSetting(){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }

    public void toProfile(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}