package edu.uph.m23si2.wastewise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.uph.m23si2.wastewise.R;
import edu.uph.m23si2.wastewise.model.Product;

public class ProductHomeAdapter extends RecyclerView.Adapter<ProductHomeAdapter.ProductHomeViewHolder> {

    Context context;
    ArrayList<Product> productArrayList;

    public ProductHomeAdapter(Context context, ArrayList<Product> productArrayList) {
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
