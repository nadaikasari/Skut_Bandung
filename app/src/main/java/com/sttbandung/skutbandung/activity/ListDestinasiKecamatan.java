package com.sttbandung.skutbandung.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sttbandung.skutbandung.ClickListener.ItemClickListener;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.adapter.DestinasiAdapter;
import com.sttbandung.skutbandung.adapter.KecamatanDestinasiAdapter;
import com.sttbandung.skutbandung.handler.Config;
import com.sttbandung.skutbandung.pojo.Destinasi;
import com.sttbandung.skutbandung.pojo.KategoriDestinasi;
import com.sttbandung.skutbandung.pojo.Kecamatan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListDestinasiKecamatan extends AppCompatActivity {

    String kategori, config, ArrayName, idUser, UidUser, SaldoUser;
    private RecyclerView recyclerView;
    private KecamatanDestinasiAdapter adapterKecamatan;
    private ArrayList<Kecamatan> arrayListKecamatan;
    private RequestQueue mRequestQueue;
    private MenuItem item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_destinasi_kecamatan);

        //get kategori
        Intent intent = getIntent();
        KategoriDestinasi coll = intent.getParcelableExtra("DESTINASI");
        kategori = coll.getNama_destinasi();

        //get data intent
        idUser = intent.getStringExtra("ID");
        UidUser = intent.getStringExtra("UID");
        SaldoUser = intent.getStringExtra("SALDO");

        recyclerView = findViewById(R.id.rv_kecamatan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayListKecamatan = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        config = Config.DESTINASI_KATEGORI;
        ArrayName = "Kategori";
        getKecamatan();

    }

    public void getKecamatan() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, config, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray(ArrayName);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String kecamatan = hit.getString("kategori_kecamatan");

                                arrayListKecamatan.add(new Kecamatan(kecamatan));
                            }
                            adapterKecamatan = new KecamatanDestinasiAdapter(arrayListKecamatan, ListDestinasiKecamatan.this);
                            recyclerView.setAdapter(adapterKecamatan);
                            adapterKecamatan.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onClick(View view, int position) {
                                    Toast.makeText(ListDestinasiKecamatan.this, "Hello", Toast.LENGTH_SHORT).show();
                                }
                            });
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