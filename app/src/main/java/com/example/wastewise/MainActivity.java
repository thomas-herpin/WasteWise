package com.example.wastewise;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.wastewise.ui.account.AccountFragment;
import com.example.wastewise.ui.account.AccountSellerFragment;
import com.example.wastewise.ui.activity.ActivityFragment;
import com.example.wastewise.ui.activity.ActivitySellerFragment;
import com.example.wastewise.ui.dashboard.DashboardFragment;
import com.example.wastewise.ui.home.HomeFragment;
import com.example.wastewise.ui.product.ProductFragment;
import com.example.wastewise.ui.product.ProductSellerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_nav_menu);
        userType = getUserType();

        if (userType == null) {
            Log.e("MainActivity", "User type kosong.");
            userType = "buyer";
        }

        setupBottomNavigation();

        if (savedInstanceState == null) {
            loadDefaultFragment();
        }
    }

    private void setupBottomNavigation() {
        bottomNavigation.getMenu().clear();

        if ("seller".equals(userType)) {
            bottomNavigation.inflateMenu(R.menu.bottom_nav_menu_seller);
        } else {
            bottomNavigation.inflateMenu(R.menu.bottom_nav_menu);
        }

        bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;

            if ("seller".equals(userType)) {
                selectedFragment = getSellerFragment(item.getItemId());
            } else {
                selectedFragment = getBuyerFragment(item.getItemId());
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }

    private Fragment getSellerFragment(int itemId) {
        if (itemId == R.id.nav_dashboard) {
            return new DashboardFragment();
        } else if (itemId == R.id.nav_product_seller) {
            return new ProductSellerFragment();
        } else if (itemId == R.id.nav_activity_seller) {
            return new ActivitySellerFragment();
        } else if (itemId == R.id.nav_account_seller) {
            return new AccountSellerFragment();
        } else {
            return new DashboardFragment();
        }
    }

    private Fragment getBuyerFragment(int itemId) {
        if (itemId == R.id.nav_home) {
            return new HomeFragment();
        } else if (itemId == R.id.nav_product) {
            return new ProductFragment();
        } else if (itemId == R.id.nav_activity) {
            return new ActivityFragment();
        } else if (itemId == R.id.nav_account) {
            return new AccountFragment();
        } else {
            return new HomeFragment();
        }
    }

    private String getUserType() {
        return getIntent().getStringExtra("user_type");
    }

    private void loadDefaultFragment() {
        Fragment defaultFragment;

        if ("seller".equals(userType)) {
            defaultFragment = new DashboardFragment();
            bottomNavigation.setSelectedItemId(R.id.nav_dashboard);
        } else {
            defaultFragment = new HomeFragment();
            bottomNavigation.setSelectedItemId(R.id.nav_home);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, defaultFragment)
                .commit();
    }
}
