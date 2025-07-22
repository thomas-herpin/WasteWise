package com.example.wastewise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.wastewise.R;
import com.example.wastewise.model.ProductBackup;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ProductHomeViewHolder> {

    Context context;
    ArrayList<ProductBackup> productArrayList;

    public ProductHomeAdapter(Context context, ArrayList<ProductBackup> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    public static class ProductHomeViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProduk;
        TextView txvJumlahItem, txvAlamatOutlet, txvHarga;

        public ProductHomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProduk = itemView.findViewById(R.id.imvProduk);
            txvJumlahItem = itemView.findViewById(R.id.txvJumlahItem);
            txvAlamatOutlet = itemView.findViewById(R.id.txvAlamatOutlet);
            txvHarga = itemView.findViewById(R.id.txvHarga);
        }
    }

    @NonNull
    @Override
    public ProductHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_produk_home, parent, false);
        return new ProductHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHomeViewHolder holder, int position) {
        ProductBackup productBackup = productArrayList.get(position);

        holder.imvProduk.setImageResource(productBackup.getLogoOutlet());
        holder.txvJumlahItem.setText(productBackup.getJumlahItem());
        holder.txvAlamatOutlet.setText(productBackup.getAlamat());
        holder.txvHarga.setText("Rp " + productBackup.getHarga());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }
}
