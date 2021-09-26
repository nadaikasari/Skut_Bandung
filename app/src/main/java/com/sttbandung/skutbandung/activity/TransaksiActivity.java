package com.sttbandung.skutbandung.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.sttbandung.skutbandung.R;

public class TransaksiActivity extends AppCompatActivity {

    private TextView wisata, harga, sub_total, saldo, total;
    private EditText jumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        Intent intent = getIntent();
        String destinasi_name = intent.getStringExtra("nama_des");
        String destinasi_harga = intent.getStringExtra("harga");
        String user_id = intent.getStringExtra("id");
        String user_uid = intent.getStringExtra("uid");
        String user_saldo = intent.getStringExtra("saldo");


        wisata = findViewById(R.id.wisata_choice);
        harga = findViewById(R.id.harga_destinasi_wisata);
        sub_total = findViewById(R.id.subtotal);
        saldo = findViewById(R.id.sisa_saldo);
        total = findViewById(R.id.ttl_pembayaran);
        jumlah = findViewById(R.id.jumlah_tiket);

        wisata.setText(destinasi_name);
        harga.setText("Rp. "+destinasi_harga);
        saldo.setText(user_saldo);

    }
}