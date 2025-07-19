package com.example.wastewise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wastewise.R;
import com.example.wastewise.model.ProductDetail;

import java.util.ArrayList;

public class ProductDetailAdapter extends RecyclerView.Adapter<ProductDetailAdapter.ProductDetailViewHolder> {

    Context context;
    ArrayList<ProductDetail> productDetailList;

    public ProductDetailAdapter(Context context, ArrayList<ProductDetail> productDetailList) {
        this.context = context;
        this.productDetailList = productDetailList;
    }

    public static class ProductDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView imvMakanan;
        TextView txvNamaMakanan, txvQuantity;

        public ProductDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imvMakanan = itemView.findViewById(R.id.imvMakanan);
            txvNamaMakanan = itemView.findViewById(R.id.txvNamaMakanan);
            txvQuantity = itemView.findViewById(R.id.txvQuantity);
        }
    }

    @NonNull
    @Override
    public ProductDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_detail_product, parent, false);
        return new ProductDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailViewHolder holder, int position) {
        ProductDetail productDetail = productDetailList.get(position);
        holder.imvMakanan.setImageResource(productDetail.getImageResId());
        holder.txvNamaMakanan.setText(productDetail.getNamaMakanan());
        holder.txvQuantity.setText(productDetail.getQuantity());
    }

    @Override
    public int getItemCount() {
        return productDetailList.size();
    }
}
