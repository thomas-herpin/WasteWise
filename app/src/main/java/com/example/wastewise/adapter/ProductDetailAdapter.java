package com.example.wastewise.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wastewise.R;
import com.example.wastewise.model.ProductDetail;

import java.util.ArrayList;

public class ProductDetailAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ProductDetail> productDetailList;
    private LayoutInflater inflater;

    public ProductDetailAdapter(Context context, ArrayList<ProductDetail> productDetailList) {
        this.context = context;
        this.productDetailList = productDetailList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return productDetailList.size();
    }

    @Override
    public Object getItem(int position) {
        return productDetailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_detail_product, parent, false);
            holder = new ViewHolder();
            holder.imvMakanan = convertView.findViewById(R.id.imvMakanan);
            holder.txvNamaMakanan = convertView.findViewById(R.id.txvNamaMakanan);
            holder.txvQuantity = convertView.findViewById(R.id.txvQuantity);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProductDetail product = productDetailList.get(position);
        holder.imvMakanan.setImageResource(product.getImvMakanan());
        holder.txvNamaMakanan.setText(product.getNamaMakanan());
        holder.txvQuantity.setText(product.getQuantity());

        return convertView;
    }

    static class ViewHolder {
        ImageView imvMakanan;
        TextView txvNamaMakanan;
        TextView txvQuantity;
    }
}