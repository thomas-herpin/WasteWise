package com.example.wastewise.ui.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wastewise.BankAccountActivity;
import com.example.wastewise.PersonalDetailSellerActivity;
import com.example.wastewise.R;
import com.example.wastewise.SettingSellerActivity;

public class AccountSellerFragment extends Fragment {

    LinearLayout btnPersonalDetailOutlet, btnSettingsSeller, btnBank;
    private View rootView;

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
        setupClickListeners();
    }

    private void init() {
        btnPersonalDetailOutlet = rootView.findViewById(R.id.btnPersonalDetailOutlet);
        btnSettingsSeller = rootView.findViewById(R.id.btnSettingsSeller);
        btnBank = rootView.findViewById(R.id.btnBank);
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
