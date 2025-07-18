package com.example.wastewise.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wastewise.DetailProductActivity;
import com.example.wastewise.R;
import com.example.wastewise.adapter.ProductAdapter;
import com.example.wastewise.model.Product;
import com.example.wastewise.ui.account.AccountFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    private RecyclerView rvProduk;
    private ArrayList<Product> produkArrayList;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product, container, false);

        rvProduk = root.findViewById(R.id.rvProduk);
        produkArrayList = new ArrayList<>();

        produkArrayList.add(new Product("10 Items", "McDonalds - Mong...", 50000, R.drawable.mcdonalds));
        produkArrayList.add(new Product("10 Items", "Roti'o - Medan Fair", 50000, R.drawable.rotio));
        produkArrayList.add(new Product("5 Items", "HokBen - Center Point", 40000, R.drawable.hokben));
        produkArrayList.add(new Product("3 Items", "Starbucks - Adam Malik", 20000, R.drawable.starbucks));

        adapter = new ProductAdapter(requireContext(), produkArrayList);
        rvProduk.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvProduk.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(requireContext(), DetailProductActivity.class);
                intent.putExtra("namaOutlet", product.getAlamat());
                intent.putExtra("jumlahItem", product.getJumlahItem());
                intent.putExtra("harga", product.getHarga());
                intent.putExtra("logo", product.getLogoOutlet());
                startActivity(intent);
            }
        });

        return root;
    }
}
