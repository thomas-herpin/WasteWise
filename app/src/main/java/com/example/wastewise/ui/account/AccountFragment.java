package com.example.wastewise.ui.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import com.example.wastewise.FaqsActivity;
import com.example.wastewise.LandingPageSellerActivity;
import com.example.wastewise.PersonalDetailActivity;
import com.example.wastewise.R;
import com.example.wastewise.SettingActivity;
import com.example.wastewise.model.User;

import io.realm.Realm;

public class AccountFragment extends Fragment {

    LinearLayout btnPersonalDetail, btnSettings, btnMyOutlet, btnContactUs, btnFAQs;
    ImageView btnNotif;
    CardView crvPersonalDetail;
    TextView txvName, txvEmail;
    Realm realm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        ViewCompat.setOnApplyWindowInsetsListener(root.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init(root);
        realm = Realm.getDefaultInstance();

        SharedPreferences prefs = requireContext().getSharedPreferences("login_prefs", getContext().MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if (email != null) {
            User user = realm.where(User.class)
                    .equalTo("email", email)
                    .findFirst();

            if (user != null) {
                txvName.setText(user.getUsername());
                txvEmail.setText(user.getEmail());
            }
        }

        setupListeners();


        return root;
    }

    private void init(View root) {
        btnPersonalDetail = root.findViewById(R.id.btnPersonalDetail);
        btnSettings = root.findViewById(R.id.btnSettings);
        btnMyOutlet = root.findViewById(R.id.btnMyOutlet);
        btnContactUs = root.findViewById(R.id.btnContactUs);
        btnFAQs = root.findViewById(R.id.btnFAQs);
        btnNotif = root.findViewById(R.id.btnNotif);
        crvPersonalDetail = root.findViewById(R.id.crvPersonalDetail1);
        txvName = root.findViewById(R.id.txvName);
        txvEmail = root.findViewById(R.id.txvEmail);
    }

    private void setupListeners() {
        btnPersonalDetail.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), PersonalDetailActivity.class);
            startActivity(intent);
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SettingActivity.class);
            startActivity(intent);
        });

        btnMyOutlet.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), LandingPageSellerActivity.class);
            startActivity(intent);
        });

        btnFAQs.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), FaqsActivity.class);
            startActivity(intent);
        });

    }
}
