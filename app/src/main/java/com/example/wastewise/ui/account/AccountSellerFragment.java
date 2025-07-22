package com.example.wastewise.ui.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wastewise.BankAccountActivity;
import com.example.wastewise.PersonalDetailSellerActivity;
import com.example.wastewise.R;
import com.example.wastewise.SettingSellerActivity;
import com.example.wastewise.model.Seller;

import io.realm.Realm;

public class AccountSellerFragment extends Fragment {

    LinearLayout btnPersonalDetailOutlet, btnSettingsSeller, btnBank;
    TextView txtNamaOutlet, txtEmailOutlet;
    private View rootView;
    Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_account_seller, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();

        realm = Realm.getDefaultInstance();

        SharedPreferences prefs = requireContext().getSharedPreferences("login_seller_prefs", getContext().MODE_PRIVATE);
        String email = prefs.getString("email_seller", null);

        if (email != null) {
            Seller seller = realm.where(Seller.class)
                    .equalTo("email", email)
                    .findFirst();

            if (seller != null) {
                txtNamaOutlet.setText(seller.getNamaOutlet());
                txtEmailOutlet.setText(seller.getEmail());
            }
        }

        setupClickListeners();
    }

    private void init() {
        btnPersonalDetailOutlet = rootView.findViewById(R.id.btnPersonalDetailOutlet);
        btnSettingsSeller = rootView.findViewById(R.id.btnSettingsSeller);
        btnBank = rootView.findViewById(R.id.btnBank);
        txtNamaOutlet = rootView.findViewById(R.id.txtNamaOutlet);
        txtEmailOutlet = rootView.findViewById(R.id.txtEmailOutlet);
    }

    private void setupClickListeners() {
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

    private void toOutletDetail() {
        Intent intent = new Intent(getActivity(), PersonalDetailSellerActivity.class);
        startActivity(intent);
    }

    private void toSettings() {
        Intent intent = new Intent(getActivity(), SettingSellerActivity.class);
        startActivity(intent);
    }

    private void toBank() {
        Intent intent = new Intent(getActivity(), BankAccountActivity.class);
        startActivity(intent);
    }
}
