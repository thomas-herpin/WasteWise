package com.example.wastewise.adapter;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wastewise.R;
import com.example.wastewise.model.Product;
import com.example.wastewise.model.ProductSeller;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;

public class ProductSellerAdapter extends RecyclerView.Adapter<ProductSellerAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;

    public ProductSellerAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    public interface OnItemDeleteClickListener {
        void onDelete(Product product);
    }

    private OnItemDeleteClickListener deleteClickListener;

    public void setOnItemDeleteClickListener(OnItemDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }


    public interface OnItemEditClickListener {
        void onEdit(Product product);
    }

    private OnItemEditClickListener editClickListener;

    public void setOnItemEditClickListener(OnItemEditClickListener listener) {
        this.editClickListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProduk, imvEdit, imvDelete;
        TextView txvNamaProduk, txvJumlah, txvHarga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProduk = itemView.findViewById(R.id.imvProduk);
            imvEdit = itemView.findViewById(R.id.imvEdit);
            imvDelete = itemView.findViewById(R.id.imvDelete);
            txvNamaProduk = itemView.findViewById(R.id.txvNamaProduk);
            txvJumlah = itemView.findViewById(R.id.txvJumlah);
            txvHarga = itemView.findViewById(R.id.txvHarga);
        }
    }

    @NonNull
    @Override
    public ProductSellerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_product_penjual, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSellerAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.imvProduk.setImageResource(product.getFotoProduk());
        holder.txvNamaProduk.setText(product.getNamaItem());
        holder.txvJumlah.setText(product.getJumlahItem() + " pcs");
        holder.txvHarga.setText("Rp " + String.format("%,d", product.getHarga()));

        holder.imvEdit.setOnClickListener(v -> {
            if (editClickListener != null) {
                editClickListener.onEdit(product); // Kirim data ke fragment
            }
        });

        holder.imvDelete.setOnClickListener(v -> {
            if (deleteClickListener != null) {
                deleteClickListener.onDelete(product);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}