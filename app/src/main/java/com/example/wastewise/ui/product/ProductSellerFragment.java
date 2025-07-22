package com.example.wastewise.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wastewise.CreateOfferActivity;
import com.example.wastewise.R;
import com.example.wastewise.adapter.ProductSellerAdapter;
import com.example.wastewise.model.Product;
import com.example.wastewise.model.ProductSeller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProductSellerFragment extends Fragment {

    private EditText edtNamaItem, edtJumlahItem, edtHargaItem;
    private Button btnCreateOffer;
    private RecyclerView lsvProdukPenjual;
    private ProductSellerAdapter adapter;
    private List<Product> productList;

    private Realm realm;
    private ExecutorService executorService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Realm.init(requireContext());
        realm = Realm.getDefaultInstance();
        executorService = Executors.newSingleThreadExecutor();

        return inflater.inflate(R.layout.fragment_product_seller, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Bind views
        edtNamaItem = view.findViewById(R.id.edtNamaItem);
        edtJumlahItem = view.findViewById(R.id.edtJumlahItem);
        edtHargaItem = view.findViewById(R.id.edtHargaItem);
        btnCreateOffer = view.findViewById(R.id.btnCreateOffer);
        lsvProdukPenjual = view.findViewById(R.id.lsvProdukPenjual);

        // Setup RecyclerView
        lsvProdukPenjual.setLayoutManager(new LinearLayoutManager(getContext()));
        productList = new ArrayList<>();
        adapter = new ProductSellerAdapter(getContext(), productList);
        lsvProdukPenjual.setAdapter(adapter);

        // Load existing data from Realm
        loadProducts();

        // Add new item
        btnCreateOffer.setOnClickListener(v -> {
            addProduct();
        });
    }

    private void addProduct() {
        String namaItem = edtNamaItem.getText().toString().trim();
        String jumlahItem = edtJumlahItem.getText().toString().trim();
        String hargaStr = edtHargaItem.getText().toString().trim();

        if (TextUtils.isEmpty(namaItem) || TextUtils.isEmpty(jumlahItem) || TextUtils.isEmpty(hargaStr)) {
            return;
        }

        int harga = Integer.parseInt(hargaStr);

        executorService.execute(() -> {
            try (Realm backgroundRealm = Realm.getDefaultInstance()) {
                backgroundRealm.executeTransaction(r -> {
                    Number currentId = r.where(Product.class).max("idProduk");
                    int nextId = (currentId == null) ? 1 : currentId.intValue() + 1;

                    Product product = r.createObject(Product.class, nextId);
                    product.setNamaItem(namaItem);
                    product.setJumlahItem(jumlahItem);
                    product.setHarga(harga);
                    product.setFotoProduk(R.drawable.cupcake);
                });
            }

            // Update UI on main thread
            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    loadProducts();
                    clearInputFields();
                });
            }
        });
    }

    private void clearInputFields() {
        edtNamaItem.setText("");
        edtJumlahItem.setText("");
        edtHargaItem.setText("");
    }

    private void loadProducts() {
        RealmResults<Product> results = realm.where(Product.class).findAll();
        productList.clear();
        productList.addAll(realm.copyFromRealm(results));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }

        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}