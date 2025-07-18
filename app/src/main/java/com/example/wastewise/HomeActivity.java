package com.example.wastewise;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wastewise.adapter.AdsAdapter;
import com.example.wastewise.adapter.ProductHomeAdapter;
import com.example.wastewise.model.Ads;
import com.example.wastewise.model.Product;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ViewPager2 rvAds;

    RecyclerView rvProdukHome;

    ArrayList<Ads> adsArrayList;

    ArrayList<Product> produkArrayList;

    private static AdsAdapter adsAdapter;

    private static ProductHomeAdapter productHomeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvAds = findViewById(R.id.rvAds);
        adsArrayList = new ArrayList<>();

        Ads ads1 = new Ads("Starbucks", "Get surplus food from Starbucks for 35% off.", R.drawable.starbucks);
        Ads ads2 = new Ads("HokBen", "Buy 1 get 1 on all surplus menu!", R.drawable.hokben);
        Ads ads3 = new Ads("Roti'o", "Only today: 40% off!", R.drawable.rotio);

        adsArrayList.add(ads1);
        adsArrayList.add(ads2);
        adsArrayList.add(ads3);

        adsAdapter = new AdsAdapter(this, adsArrayList);
        rvAds.setAdapter(adsAdapter);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = rvAds.getCurrentItem();
                int totalItem = adsArrayList.size();
                rvAds.setCurrentItem((currentItem + 1) % totalItem, true);
                handler.postDelayed(this, 3000); // 3 detik
            }
        };
        handler.postDelayed(runnable, 3000);

        rvAds.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                rvAds.post(() -> {
                    View view = rvAds.getChildAt(0);
                    if (view != null && view instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) view;
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                        if (viewHolder != null && viewHolder.itemView != null) {
                            int height = viewHolder.itemView.getMeasuredHeight();
                            ViewGroup.LayoutParams layoutParams = rvAds.getLayoutParams();
                            layoutParams.height = height;
                            rvAds.setLayoutParams(layoutParams);
                        }
                    }
                });
            }
        });

        rvProdukHome = (RecyclerView) findViewById(R.id.rvProdukHome);
        produkArrayList = new ArrayList<>();

        Product product1 = new Product("10 Items", "McDonalds - Mong...", 50000, R.drawable.mcdonalds);
        Product product2 = new Product("10 Items", "Roti'o - Medan Fair", 50000, R.drawable.rotio);
        Product product3 = new Product("5 Items", "HokBen - Center Point", 50000, R.drawable.hokben);

        produkArrayList.add(product1);
        produkArrayList.add(product2);
        produkArrayList.add(product3);

        productHomeAdapter = new ProductHomeAdapter(this,produkArrayList);
        rvProdukHome.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvProdukHome.setAdapter(productHomeAdapter);
    }
}