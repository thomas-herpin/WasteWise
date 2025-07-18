package edu.uph.m23si2.wastewise;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.uph.m23si2.wastewise.adapter.ProductAdapter;
import edu.uph.m23si2.wastewise.model.Product;

public class ProductActivity extends AppCompatActivity {

    RecyclerView rvProduk;

    ArrayList<Product> produkArrayList;

    private static ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvProduk = (RecyclerView) findViewById(R.id.rvProduk);
        produkArrayList = new ArrayList<>();

        Product product1 = new Product("10 Items", "McDonalds - Mong...", 50000, R.drawable.mcdonalds);
        Product product2 = new Product("10 Items", "Roti'o - Medan Fair", 50000, R.drawable.rotio);
        Product product3 = new Product("5 Items", "HokBen - Center Point", 40000, R.drawable.hokben);
        Product product4 = new Product("3 Items", "Starbucks - Adam Malik", 20000, R.drawable.starbucks);

        produkArrayList.add(product1);
        produkArrayList.add(product2);
        produkArrayList.add(product3);
        produkArrayList.add(product4);

        adapter = new ProductAdapter(this,produkArrayList);
        rvProduk.setLayoutManager(new GridLayoutManager(this, 2));
        rvProduk.setAdapter(adapter);
    }
}