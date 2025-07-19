package com.example.wastewise;

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
    private ImageView qrCodeImageView;
    private LinearLayout qrCodeSection;

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

        // Inisialisasi QR code ImageView
        qrCodeImageView = findViewById(R.id.ivQrCodeImage);

    }

    private void setupClickListener() {
        // Click listener untuk QR code
        if (qrCodeImageView != null) {
            qrCodeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bukaHalamanPickUp();
                }
            });
        }

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

    private void bukaHalamanPickUp() {
        // Buka halaman Pick Up dengan membawa data pesanan
        Intent intent = new Intent(this, PickUpActivity.class);

        // Kirim data pesanan ke PickUpActivity
        intent.putExtra("id_transaksi", idTransaksi);
        intent.putExtra("nama_toko", namaToko);
        intent.putExtra("lokasi_toko", lokasiToko);
        intent.putExtra("waktu_ambil", waktuAmbil);
        intent.putExtra("tanggal_pesan", tanggalPesan);

        startActivity(intent);
    }

    private void bukaHalamanReview() {
        // Buka halaman review
        Toast.makeText(this, "Membuka halaman review", Toast.LENGTH_SHORT).show();

    }

    private String dapatkanWaktuSekarang() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH.mm",
                new Locale("id", "ID"));
        return sdf.format(new Date());
    }

    private void tampilkanNotifikasi() {
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
        super.onBackPressed();
        finish();
    }
}