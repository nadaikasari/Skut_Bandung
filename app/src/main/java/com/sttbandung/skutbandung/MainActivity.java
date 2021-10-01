package com.sttbandung.skutbandung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sttbandung.skutbandung.Fragment.BerandaFragment;
import com.sttbandung.skutbandung.Fragment.DestinasiFragment;
import com.sttbandung.skutbandung.Fragment.UserFragment;
import com.sttbandung.skutbandung.handler.Config;
import com.sttbandung.skutbandung.pojo.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    String id_user, nama_user, uid_user, email_user, tlpn_user, foto_user, saldo_user, statusBelum, statusSudah;
    private RequestQueue mRequestQueue;
    private SwipeRefreshLayout doRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentPage(new BerandaFragment());
        mRequestQueue = Volley.newRequestQueue(this);

        //get data intent
        Intent intent = getIntent();
        nama_user = intent.getStringExtra("nama");
        id_user = intent.getStringExtra("id");
        uid_user = intent.getStringExtra("uid");
        email_user = intent.getStringExtra("email");
        tlpn_user = intent.getStringExtra("tlpn");
        foto_user = intent.getStringExtra("foto");
        saldo_user = intent.getStringExtra("saldo");

        hitungTransaksiSelesai();
        hitungTransaksiBelumSelesai();

//        refresh color method
        doRefresh = findViewById(R.id.swipe_refresh);
        doRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_beranda:
                        getFragmentPage(new BerandaFragment());
                        break;
                    case R.id.menu_destinasi:
                        getFragmentPage(new DestinasiFragment());
                        break;
                    case R.id.menu_profil:
                        getFragmentPage(new UserFragment());
                        break;
                }
                return true;
            }
        });


        doRefresh.setOnRefreshListener(this::updateSaldo);

    }


    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.main_container, fragment).commit();
            return true;
        }
        return false;
    }

    public String getIdUser() {
        return id_user;
    }

    public String getNamaUser() {
        return nama_user;
    }

    public String getUidUser() {
        return uid_user;
    }

    public String getEmailUser() {
        return email_user;
    }

    public String getTlpnUser() {
        return tlpn_user;
    }

    public String getFotoUser() {
        return foto_user;
    }

    public String getSaldoUser() {
        updateSaldo();
        return saldo_user;
    }

    public String getStatusBelum() {
        return statusBelum;
    }

    public String getStatusSudah() {
        return statusSudah;
    }

    private void updateSaldo() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Config.LOGIN + email_user, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("user");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String saldo = hit.getString("saldo");
                                doRefresh.setRefreshing(false);
                                saldo_user = saldo;
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

    private void hitungTransaksiSelesai() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Config.TRANSAKSI_STATUS1 + id_user, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("transaksi");
                                int totalCount = jsonArray.length();
                                statusSudah = String.valueOf(totalCount);
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

    private void hitungTransaksiBelumSelesai() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Config.TRANSAKSI_STATUS2 + id_user, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("transaksi");
                            int totalCount = jsonArray.length();
                            statusBelum = String.valueOf(totalCount);
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