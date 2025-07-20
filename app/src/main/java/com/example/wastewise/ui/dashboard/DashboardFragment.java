package com.example.wastewise.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wastewise.CreateOfferActivity;
import com.example.wastewise.R;
import com.example.wastewise.ui.product.ProductSellerFragment;

public class DashboardFragment extends Fragment {

    private Button btnManageProduct, btnCreateOffer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnManageProduct = view.findViewById(R.id.btnManageProduct);
        btnCreateOffer = view.findViewById(R.id.btnCreateOffer);

        btnManageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toManageProduct();
            }
        });

        btnCreateOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCreateOffer();
            }
        });
    }

    private void toManageProduct() {
        Intent intent = new Intent(getActivity(), ProductSellerFragment.class);
        startActivity(intent);
    }

    private void toCreateOffer() {
        Intent intent = new Intent(getActivity(), CreateOfferActivity.class);
        startActivity(intent);
    }
}