package com.example.wastewise;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wastewise.adapter.ProductDetailAdapter;
import com.example.wastewise.adapter.ProductDetailAdapter2;
import com.example.wastewise.adapter.ProductSellerAdapter;
import com.example.wastewise.model.Product;
import com.example.wastewise.model.ProductDetail;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class DetailProductActivity2 extends AppCompatActivity {

    ListView lvDetailProduct;
    ArrayList<Product> productArrayList;
    ProductDetailAdapter2 adapter;
    ImageView btnBack, imv;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_product2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPayment();
            }
        });

        final ArrayList<Product> arrayList = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();
        RealmResults<Product> resutls = realm.where(Product.class).findAll();
        if (resutls != null){
            arrayList.addAll(realm.copyFromRealm(resutls));

            Log.d("REALM", "Product count: " + arrayList.size());
        }

        ProductDetailAdapter2 productDetailAdapter2 = new ProductDetailAdapter2(this, arrayList);
        lvDetailProduct.setAdapter(productDetailAdapter2);
    }

    public void init(){
        lvDetailProduct = findViewById(R.id.lvDetailProduct);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
    }

    private void toPayment() {
        Intent intent = new Intent(this, OrderSummaryActivity.class);
        startActivity(intent);
    }
}