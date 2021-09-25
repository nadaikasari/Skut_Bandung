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
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragmentPage(new BerandaFragment());

        text = findViewById(R.id.texthallo);

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
}