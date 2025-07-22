package com.example.wastewise.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wastewise.R;
import com.example.wastewise.model.Product;
import com.example.wastewise.model.ProductDetail;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class ProductDetailAdapter2 extends ArrayAdapter<Product> {

    public ProductDetailAdapter2(@NonNull Context context, int resource , @NonNull List<Product> objects) {
        super(context, resource, objects);
    }

    public ProductDetailAdapter2(@NonNull Context context, ArrayList<Product> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.layout_detail_product, parent, false);
        }

        Product product = getItem(position);
        assert product != null;

        ImageView imvMakanan = currentItemView.findViewById((R.id.imvMakanan));
        imvMakanan.setImageResource(R.drawable.cupcake);


        TextView txvNamaMakanan = currentItemView.findViewById(R.id.txvNamaMakanan);
        TextView txvQuantity = currentItemView.findViewById(R.id.txvQuantity);
        txvNamaMakanan.setText(product.getNamaItem());
        txvQuantity.setText(product.getJumlahItem());
//        currentItemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toProfil(product.getIdProduk());
//            }
//        });
        return currentItemView;
    }

//    public void toProfil(int studentID){
//        Intent intent = new Intent(getContext(), ProfileActivity.class);
//        intent.putExtra("mode", "edit");
//        intent.putExtra("studentID", studentID);
//        getContext().startActivity(intent);
//    }
}