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
import com.example.wastewise.model.ProductBackup;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context context;
    ArrayList<ProductBackup> productArrayList;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(ProductBackup productBackup);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ProductAdapter(Context context, ArrayList<ProductBackup> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imvProduk;
        TextView txvJumlahItem, txvAlamatOutlet, txvHarga;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imvProduk = itemView.findViewById(R.id.imvProduk);
            txvJumlahItem = itemView.findViewById(R.id.txvJumlahItem);
            txvAlamatOutlet = itemView.findViewById(R.id.txvAlamatOutlet);
            txvHarga = itemView.findViewById(R.id.txvHarga);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(productArrayList.get(position));
                        }
                    }
                }
            });
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
