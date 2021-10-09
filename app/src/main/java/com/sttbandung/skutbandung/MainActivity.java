package com.sttbandung.skutbandung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;

import com.sttbandung.skutbandung.Fragment.BerandaFragment;
import com.sttbandung.skutbandung.Fragment.DestinasiFragment;
import com.sttbandung.skutbandung.Fragment.UserFragment;
import com.sttbandung.skutbandung.databinding.ActivityMainBinding;
import com.sttbandung.skutbandung.pojo.User;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "extra_data";
    public String id_user, nama_user, uid_user, email_user, tlpn_user, foto_user, saldo_user;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set layout no title bar
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getFragmentPage(new BerandaFragment());

        getDataUser();

        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
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
        });

        /*NavController navController = Navigation.findNavController(this, nav_host_fragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.menu_beranda, R.id.menu_destinasi, R.id.menu_profil)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);*/
    }

    private void getFragmentPage(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.main_container, fragment).commit();
        }
    }

    private void getDataUser() {
        User users = getIntent().getParcelableExtra(EXTRA_DATA);
        nama_user = users.getNama();
        id_user = users.getId();
        uid_user = users.getUid();
        email_user = users.getEmail();
        tlpn_user = users.getNotlpn();
        foto_user = users.getImage();
        saldo_user = users.getSaldo();
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
        return saldo_user;
    }

}