package com.sttbandung.skutbandung;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sttbandung.skutbandung.Fragment.BerandaFragment;
import com.sttbandung.skutbandung.Fragment.DestinasiFragment;
import com.sttbandung.skutbandung.Fragment.UserFragment;

import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    String nama_user, uid_user, email_user, tlpn_user, foto_user, saldo_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentPage(new BerandaFragment());

        //get data intent
        Intent intent = getIntent();
        nama_user = intent.getStringExtra("nama");
        uid_user = intent.getStringExtra("uid");
        email_user = intent.getStringExtra("email");
        tlpn_user = intent.getStringExtra("tlpn");
        foto_user = intent.getStringExtra("foto");
        saldo_user = intent.getStringExtra("saldo");


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

    }


    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.main_container, fragment).commit();
            return true;
        }
        return false;
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
        return saldo_user;
    }

}