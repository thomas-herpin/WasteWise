package com.example.wastewise.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wastewise.CreateOfferActivity;
import com.example.wastewise.R;
import com.example.wastewise.adapter.ProductSellerAdapter;
import com.example.wastewise.model.ProductSeller;

import java.util.ArrayList;

public class ProductSellerFragment extends Fragment {

    private Button btnCreateOffer;
    private RecyclerView lsvProdukPenjual;
    private ArrayList<ProductSeller> productSellerList;
    private ProductSellerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_seller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lsvProdukPenjual = view.findViewById(R.id.lsvProdukPenjual);
        btnCreateOffer = view.findViewById(R.id.btnCreateOffer);

        btnCreateOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCreateOffer();
            }
        });

        lsvProdukPenjual.setLayoutManager(new LinearLayoutManager(getContext()));

        productSellerList = new ArrayList<>();

        productSellerList.add(new ProductSeller("10 pcs", "Roti Gosong", 10000, R.drawable.rotigosong));
        productSellerList.add(new ProductSeller("8 pcs", "Roti Tawar", 8000, R.drawable.rotitawar));
        productSellerList.add(new ProductSeller("5 pcs", "Biskuit", 5000, R.drawable.biscuit));
        productSellerList.add(new ProductSeller("2 pcs", "Cupcake", 12000, R.drawable.cupcake));

        adapter = new ProductSellerAdapter(getContext(), productSellerList);
        lsvProdukPenjual.setAdapter(adapter);
    }

    private void toCreateOffer() {
        Intent intent = new Intent(getActivity(), CreateOfferActivity.class);
        startActivity(intent);
    }
}