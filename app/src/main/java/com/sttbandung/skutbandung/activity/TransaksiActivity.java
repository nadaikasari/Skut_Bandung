package com.sttbandung.skutbandung.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Header;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.handler.Config;

import java.text.NumberFormat;
import java.util.Locale;

public class TransaksiActivity extends AppCompatActivity {

    private TextView wisata, harga, sub_total, saldo, total;
    private EditText jumlah;
    private Button Pesan;
    private String user_id, user_uid, id_destinasi;
    private int jmltoDouble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        Intent intent = getIntent();
        String destinasi_name = intent.getStringExtra("nama_des");
        String destinasi_harga = intent.getStringExtra("harga");
        user_id = intent.getStringExtra("id");
        user_uid = intent.getStringExtra("uid");
        id_destinasi = intent.getStringExtra("id_destinasi");
        String user_saldo = intent.getStringExtra("saldo");

        wisata = findViewById(R.id.wisata_choice);
        harga = findViewById(R.id.harga_destinasi_wisata);
        sub_total = findViewById(R.id.subtotal);
        saldo = findViewById(R.id.sisa_saldo);
        total = findViewById(R.id.ttl_pembayaran);
        jumlah = findViewById(R.id.jumlah_tiket);
        Pesan = findViewById(R.id.button_pesan);

        wisata.setText(destinasi_name);
        harga.setText("Rp. "+destinasi_harga);
        saldo.setText(user_saldo);


        jumlah.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String jml = String.valueOf(jumlah.getText());
                String harga =String.valueOf(destinasi_harga);
                jmltoDouble = Integer.valueOf(jml);
                double hargatoDouble = Double.valueOf(harga);
                double subttl = jmltoDouble * hargatoDouble;
                sub_total.setText(String.valueOf(subttl));
                total.setText(String.valueOf(subttl));
            }
        });


        Pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < jmltoDouble; i++) {
                    postData();
                }

            }
        });

    }

    public void postData() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id_user", user_id);
        params.put("uid", user_uid);
        params.put("id_destinasi", id_destinasi);
        params.put("status", 0);
        client.post(Config.POST_TRANSAKSI, params,  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                Log.d("onSuccess: ", "BERHASIL");
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}