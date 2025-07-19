package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PickUpActivity extends AppCompatActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvOrderNumber;
    private TextView tvStatus;
    private TextView tvTime;
    private TextView tvQrInstruction;
    private ImageView ivQrCode;
    private TextView tvDownloadQr;
    private Button btnViewOrder;

    // Data pesanan
    private String idTransaksi = "133756";
    private String namaToko = "Roti O";
    private String lokasiToko = "Roti O - Karya";
    private String waktuAmbil = "10:00 AM";
    private String tanggalPesan = "Sabtu, 28 Sep 2025";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pick_up);

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
        ivBack = findViewById(R.id.ivback);
        tvTitle = findViewById(R.id.tv_title);
        tvOrderNumber = findViewById(R.id.tv_order_number);
        tvStatus = findViewById(R.id.tv_status);
        tvTime = findViewById(R.id.tv_time);
        tvQrInstruction = findViewById(R.id.tv_qr_instruction);
        ivQrCode = findViewById(R.id.iv_qr_code);
        tvDownloadQr = findViewById(R.id.tv_download_qr);
        btnViewOrder = findViewById(R.id.btnvieworder);
    }

    private void setupClickListener() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kembaliKeAfterPayment();
            }
        });

        btnViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kembaliKeAfterPayment();
            }
        });

        tvDownloadQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PickUpActivity.this, "Mengunduh QR Code", Toast.LENGTH_SHORT).show();
                downloadQrCode();
            }
        });
    }

    private void muatDataPesanan() {
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
            tanggalPesan = intent.getStringExtra("tanggal_pesan") != null ?
                    intent.getStringExtra("tanggal_pesan") : tanggalPesan;
        }

        updateUIPesanan();
    }

    private void updateUIPesanan() {
        if (tvOrderNumber != null) {
            tvOrderNumber.setText("Order #" + idTransaksi);
        }
        if (tvTime != null) {
            tvTime.setText(waktuAmbil);
        }
    }

    private void kembaliKeAfterPayment() {
        Intent intent = new Intent(this, AfterPaymentActivity.class);

        intent.putExtra("id_transaksi", idTransaksi);
        intent.putExtra("nama_toko", namaToko);
        intent.putExtra("lokasi_toko", lokasiToko);
        intent.putExtra("waktu_ambil", waktuAmbil);
        intent.putExtra("tanggal_pesan", tanggalPesan);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
        finish();
    }

    private void downloadQrCode() {
        Toast.makeText(this, "QR Code berhasil diunduh", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}