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
import com.example.wastewise.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    ArrayList<Product> productArrayList;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProduk;
        TextView txvJumlahItem, txvAlamatOutlet, txvHarga;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProduk = itemView.findViewById(R.id.imvProduk);
            txvJumlahItem = itemView.findViewById(R.id.txvJumlahItem);
            txvAlamatOutlet = itemView.findViewById(R.id.txvAlamatOutlet);
            txvHarga = itemView.findViewById(R.id.txvHarga);
        }
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_produk, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productArrayList.get(position);

        holder.imvProduk.setImageResource(product.getLogoOutlet());
        holder.txvJumlahItem.setText(product.getJumlahItem());
        holder.txvAlamatOutlet.setText(product.getAlamat());
        holder.txvHarga.setText("Rp " + product.getHarga());
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }
}
