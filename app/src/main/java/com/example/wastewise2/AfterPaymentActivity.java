package com.example.wastewise2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AfterPaymentActivity extends AppCompatActivity {

    private TextView tvHeader;
    private TextView tvRotiTitle;

    // Data contoh untuk demonstrasi
    private String idTransaksi = "133756";
    private String namaToko = "Roti O";
    private String lokasiToko = "Roti O - Karya";
    private String waktuAmbil = "10.00 WIB";
    private String tanggalPesan = "Sabtu, 28 Sep 2025";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_after_payment);

        // Handle window insets untuk tampilan edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inisialisasiView();
        setupClickListener();
        muatDataPesanan();
    }

    private void inisialisasiView() {
        tvHeader = findViewById(R.id.tv_header);
        tvRotiTitle = findViewById(R.id.tv_roti_title);
    }

    private void setupClickListener() {
        // Click listener bisa ditambahkan di sini jika diperlukan
        // Contoh: untuk tombol atau elemen lain yang ada di XML
    }

    private void muatDataPesanan() {
        // Muat data dari Intent extras atau database
        Intent intent = getIntent();
        if (intent != null) {
            idTransaksi = intent.getStringExtra("id_transaksi") != null ?
                    intent.getStringExtra("id_transaksi") : idTransaksi;
            namaToko = intent.getStringExtra("nama_toko") != null ?
                    intent.getStringExtra("nama_toko") : namaToko;
            lokasiToko = intent.getStringExtra("lokasi_toko") != null ?
                    intent.getStringExtra("lokasi_toko") : lokasiToko;
            waktuAmbil = intent.getStringExtra("waktu_ambil") != null ?
                    intent.getStringExtra("waktu_ambil") : waktuAmbil;
        }

        // Update UI dengan data yang dimuat
        updateUIPesanan();
    }

    private void updateUIPesanan() {
        if (tvRotiTitle != null) {
            tvRotiTitle.setText(namaToko);
        }
    }

    private void bukaHalamanReview() {
        // Buka halaman review
        Toast.makeText(this, "Membuka halaman review", Toast.LENGTH_SHORT).show();

        // Intent intent = new Intent(this, ReviewActivity.class);
        // startActivity(intent);
    }

    private String dapatkanWaktuSekarang() {
        // Dapatkan waktu sekarang dalam format yang diinginkan
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH.mm",
                new Locale("id", "ID"));
        return sdf.format(new Date());
    }

    private void tampilkanNotifikasi() {
        // Tampilkan notifikasi atau update status notifikasi
        Toast.makeText(this, "Anda memiliki notifikasi baru", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh data ketika kembali ke activity ini
        muatDataPesanan();
    }

    @Override
    public void onBackPressed() {
        // Handle tombol back - kembali ke activity sebelumnya
        super.onBackPressed();
        finish();
    }
}