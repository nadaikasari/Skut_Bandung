package com.sttbandung.skutbandung.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.databinding.ActivityTopupBinding;
import com.sttbandung.skutbandung.handler.Config;
import com.sttbandung.skutbandung.pojo.Capture;

import cz.msebera.android.httpclient.Header;

public class TopupActivity extends AppCompatActivity {

    private String id_user;
    private ActivityTopupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        //mengganti title bar
        setTitle("Topup Saldo");

        binding = ActivityTopupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getDataIntent();

        binding.btnOpenScan.setOnClickListener(v -> {
            IntentIntegrator intent = new IntentIntegrator(
                    TopupActivity.this
            );
            intent.setPrompt("For flash using volume key");
            intent.setBeepEnabled(true);
            intent.setOrientationLocked(true);
            intent.setCaptureActivity(Capture.class);
            intent.initiateScan();
        });

        binding.btnLoadvoucher.setOnClickListener(v -> topupVoucher());
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        id_user = intent.getStringExtra("ID_USER");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult i = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(i.getContents() != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    TopupActivity.this
            );

            builder.setTitle("Result");
            builder.setMessage(i.getContents());
            builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
            builder.show();
        }  //tambahkan else jika ada kondisi pembatalan penggunaan QR

    }

    private void topupVoucher() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.put(Config.TOP_UP + binding.editIdvoucher.getText() + "/" + id_user, params,  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(TopupActivity.this, "Voucher Berhasil Digunakan!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(TopupActivity.this, "Voucher Gagal Digunakan!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}