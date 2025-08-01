package com.example.wastewise.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wastewise.DetailProductActivity2;
import com.example.wastewise.R;
import com.example.wastewise.adapter.AdsAdapter;
import com.example.wastewise.adapter.ProductAdapter;
import com.example.wastewise.adapter.ProductHomeAdapter;
import com.example.wastewise.model.Ads;
import com.example.wastewise.model.ProductBackup;
import com.example.wastewise.model.User;

import java.util.ArrayList;

import io.realm.Realm;

public class HomeFragment extends Fragment {

    private ViewPager2 rvAds;
    private RecyclerView rvProdukHome;

    private RadioGroup rdgCategory;

    private RadioButton rdbAll, rdbRestaurant, rdbSupermarket;
    private TextView txvWelcome;
    private ArrayList<Ads> adsArrayList;
    private ArrayList<ProductBackup> produkArrayList;

    private static AdsAdapter adsAdapter;
    private static ProductHomeAdapter productHomeAdapter;
    Realm realm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        View mainLayout = root.findViewById(R.id.main);
        if (mainLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
            txvWelcome = root.findViewById(R.id.txvWelcome);

            realm = Realm.getDefaultInstance();

            SharedPreferences prefs = requireContext().getSharedPreferences("login_prefs", getContext().MODE_PRIVATE);
            String email = prefs.getString("email", null);

            if (email != null) {
                User user = realm.where(User.class)
                        .equalTo("email", email)
                        .findFirst();

                if (user != null) {
                    txvWelcome.setText("Hello, " + user.getUsername() + "!");
                }
            }
        }


        rvAds = root.findViewById(R.id.rvAds);
        adsArrayList = new ArrayList<>();
        adsArrayList.add(new Ads("Starbucks", "Get surplus food from Starbucks for 35% off.", R.drawable.starbucks));
        adsArrayList.add(new Ads("HokBen", "Buy 1 get 1 on all surplus menu!", R.drawable.hokben));
        adsArrayList.add(new Ads("Roti'o", "Only today: 40% off!", R.drawable.rotio));

        adsAdapter = new AdsAdapter(requireContext(), adsArrayList);
        rvAds.setAdapter(adsAdapter);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = rvAds.getCurrentItem();
                int totalItem = adsArrayList.size();
                rvAds.setCurrentItem((currentItem + 1) % totalItem, true);
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);

        rvAds.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                rvAds.post(() -> {
                    View view = rvAds.getChildAt(0);
                    if (view instanceof RecyclerView) {
                        RecyclerView recyclerView = (RecyclerView) view;
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                        if (viewHolder != null && viewHolder.itemView != null) {
                            int height = viewHolder.itemView.getMeasuredHeight();
                            LayoutParams layoutParams = rvAds.getLayoutParams();
                            layoutParams.height = height;
                            rvAds.setLayoutParams(layoutParams);
                        }
                    }
                });
            }
        });

        rdgCategory = root.findViewById(R.id.rdgCategory);
        rdbAll = root.findViewById(R.id.rdbAll);
        rdbRestaurant = root.findViewById(R.id.rdbRestaurant);
        rdbSupermarket = root.findViewById(R.id.rdbSupermarket);

        rdbAll.setChecked(true);

        rvProdukHome = root.findViewById(R.id.rvProdukHome);
        produkArrayList = new ArrayList<>();
        produkArrayList.add(new ProductBackup("10 Items", "McDonalds - Mong...", 50000, R.drawable.mcdonalds));
        produkArrayList.add(new ProductBackup("10 Items", "Roti'o - Medan Fair", 50000, R.drawable.rotio));
        produkArrayList.add(new ProductBackup("5 Items", "HokBen - Center Point", 50000, R.drawable.hokben));
        produkArrayList.add(new ProductBackup("10 Items", "Restoran enak - Jalan...", 50000, R.drawable.logooutlet));

        productHomeAdapter = new ProductHomeAdapter(requireContext(), produkArrayList);
        rvProdukHome.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rvProdukHome.setAdapter(productHomeAdapter);

        productHomeAdapter.setOnItemClickListener(new ProductHomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductBackup productBackup) {
                Intent intent = new Intent(requireContext(), DetailProductActivity2.class);
                intent.putExtra("source", "home");
                startActivity(intent);
            }
        });

        return root;
    }


}
