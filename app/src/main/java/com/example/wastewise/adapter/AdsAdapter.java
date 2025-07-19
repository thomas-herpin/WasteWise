package com.example.wastewise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.wastewise.R;
import com.example.wastewise.model.Ads;
import com.example.wastewise.model.Product;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.AdsViewHolder> {

    Context context;
    ArrayList<Ads> adsArrayList;

    public AdsAdapter(Context context, ArrayList<Ads> adsArrayList){
        this.context = context;
        this.adsArrayList = adsArrayList;
    }

    public static class AdsViewHolder extends RecyclerView.ViewHolder {
        TextView txvJudul, txvDeskripsi;
        ImageView imvOutlet;
        Button btnGetNow;

        public AdsViewHolder(@NonNull View itemView) {
            super(itemView);
            txvJudul = itemView.findViewById(R.id.txvJudul);
            txvDeskripsi = itemView.findViewById(R.id.txvDeskripsi);
            imvOutlet = itemView.findViewById(R.id.imvOutlet);
            btnGetNow = itemView.findViewById(R.id.btnGetNow);
        }
    }

    @NonNull
    @Override
    public AdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_ads, parent, false);
        return new AdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdsViewHolder holder, int position) {
        Ads ads = adsArrayList.get(position);
        holder.imvOutlet.setImageResource(ads.getLogoOutlet());
        holder.txvJudul.setText(ads.getJudul());
        holder.txvDeskripsi.setText(ads.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return adsArrayList.size();
    }

}

