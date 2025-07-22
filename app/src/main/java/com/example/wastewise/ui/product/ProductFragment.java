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
import com.example.wastewise.DetailProductActivity2;
import com.example.wastewise.R;
import com.example.wastewise.adapter.ProductAdapter;
import com.example.wastewise.model.ProductBackup;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    private RecyclerView rvProduk;
    private ArrayList<ProductBackup> produkArrayList;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product, container, false);

        rvProduk = root.findViewById(R.id.rvProduk);
        produkArrayList = new ArrayList<>();

        produkArrayList.add(new ProductBackup("10 Items", "McDonalds - Mong...", 50000, R.drawable.mcdonalds));
        produkArrayList.add(new ProductBackup("10 Items", "Roti'o - Medan Fair", 50000, R.drawable.rotio));
        produkArrayList.add(new ProductBackup("5 Items", "HokBen - Center Point", 40000, R.drawable.hokben));
        produkArrayList.add(new ProductBackup("3 Items", "Starbucks - Adam Malik", 20000, R.drawable.starbucks));
        produkArrayList.add(new ProductBackup("10 Items", "Restoran enak - Jalan...", 50000, R.drawable.logooutlet));

        adapter = new ProductAdapter(requireContext(), produkArrayList);
        rvProduk.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvProduk.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductBackup productBackup) {
                Intent intent = new Intent(requireContext(), DetailProductActivity2.class);
                intent.putExtra("namaOutlet", productBackup.getAlamat());
                intent.putExtra("jumlahItem", productBackup.getJumlahItem());
                intent.putExtra("harga", productBackup.getHarga());
                intent.putExtra("logo", productBackup.getLogoOutlet());
                startActivity(intent);
            }
        });

        return root;
    }
}
