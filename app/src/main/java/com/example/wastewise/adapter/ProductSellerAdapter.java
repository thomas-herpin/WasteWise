package com.example.wastewise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wastewise.R;
import com.example.wastewise.model.ProductSeller;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductSellerAdapter extends RecyclerView.Adapter<ProductSellerAdapter.ProductSellerViewHolder> {

    Context context;
    ArrayList<ProductSeller> productSellerList;

    public ProductSellerAdapter(Context context, ArrayList<ProductSeller> productSellerList) {
        this.context = context;
        this.productSellerList = productSellerList;
    }

    public static class ProductSellerViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProduk;
        TextView txvNamaProduk, txvJumlah, txvHarga;

        public ProductSellerViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProduk = itemView.findViewById(R.id.imvProduk);
            txvNamaProduk = itemView.findViewById(R.id.txvNamaProduk);
            txvJumlah = itemView.findViewById(R.id.txvJumlah);
            txvHarga = itemView.findViewById(R.id.txvHarga);
        }
    }

    @NonNull
    @Override
    public ProductSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_product_penjual, parent, false);
        return new ProductSellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSellerViewHolder holder, int position) {
        ProductSeller product = productSellerList.get(position);

        holder.imvProduk.setImageResource(product.getFotoProduk());
        holder.txvNamaProduk.setText(product.getNamaProduk());
        holder.txvJumlah.setText(product.getJumlahItem());
        holder.txvHarga.setText("Rp " + product.getHarga());
    }

    @Override
    public int getItemCount() {
        return productSellerList.size();
    }
}
