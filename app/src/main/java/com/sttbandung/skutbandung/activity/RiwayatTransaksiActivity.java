package com.sttbandung.skutbandung.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.adapter.RiwayatTransaksiAdapter;
import com.sttbandung.skutbandung.handler.Config;
import com.sttbandung.skutbandung.pojo.RiwayatTransaksi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RiwayatTransaksiActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private RecyclerView recyclerView;
    private RiwayatTransaksiAdapter adapter;
    private ArrayList<RiwayatTransaksi> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_transaksi);

        recyclerView = findViewById(R.id.rv_riwayat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        getDataTransaksi();


    }

    public void getDataTransaksi(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID_USER");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Config.RIWAYAT_TRANSAKSI + id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("transaksi");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String create_at = hit.getString("created_att");
                                String status = hit.getString("status");
                                String uid = hit.getString("id_destinasi");

                                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, Config.DESTINASI_ID + uid, null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                try {
                                                    JSONArray jsonArray = response.getJSONArray("destinasi");
                                                    for (int i = 0; i < jsonArray.length(); i++) {
                                                        JSONObject hit2 = jsonArray.getJSONObject(i);
                                                        String nama_destinasi = hit2.getString("nama_destinasi");
                                                        arrayList.add(new RiwayatTransaksi(nama_destinasi, create_at, status));
                                                    }
                                                    adapter = new RiwayatTransaksiAdapter(arrayList, RiwayatTransaksiActivity.this);
                                                    recyclerView.setAdapter(adapter);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                });
                                mRequestQueue.add(request2);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}