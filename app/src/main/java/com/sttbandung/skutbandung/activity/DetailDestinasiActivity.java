package com.sttbandung.skutbandung.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sttbandung.skutbandung.R;

import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_ALAMAT;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_GAMBAR;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_HARGA;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_JUMLAH;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_KELEMBAPAN;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_KETERANGAN;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_NAME;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_SUHU;

public class DetailDestinasiActivity extends AppCompatActivity {

    ImageView gambar_destinasi;
    TextView nama_destinasi, harga_destinasi, keterangan_destinasi, alamat_destinasi, jumlah_pengunjung, suhu_destinasi, kelembapan_destinasi, saran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_destinasi);

        Intent intent = getIntent();
        String image = intent.getStringExtra(EXTRA_GAMBAR);
        String nama = intent.getStringExtra(EXTRA_NAME);
        String pengunjung = intent.getStringExtra(EXTRA_JUMLAH);
        String alamat = intent.getStringExtra(EXTRA_ALAMAT);
        String harga = intent.getStringExtra(EXTRA_HARGA);
        String suhu = intent.getStringExtra(EXTRA_SUHU);
        String kelembapan = intent.getStringExtra(EXTRA_KELEMBAPAN);
        String keterangan = intent.getStringExtra(EXTRA_KETERANGAN);

        gambar_destinasi = findViewById(R.id.image_destinasi);
        nama_destinasi = findViewById(R.id.nama_wisata);
        harga_destinasi = findViewById(R.id.harga_destinasi);
        keterangan_destinasi = findViewById(R.id.keterangan_destinasi);
        alamat_destinasi = findViewById(R.id.alamat_destinasi);
        jumlah_pengunjung = findViewById(R.id.jml_pengunjung);
        suhu_destinasi = findViewById(R.id.suhu_destinasi);
        kelembapan_destinasi = findViewById(R.id.kelembapan_destinasi);
        saran = findViewById(R.id.saran);

        Picasso.get().load(image).into(gambar_destinasi);
        nama_destinasi.setText(nama);
        jumlah_pengunjung.setText(pengunjung);
        alamat_destinasi.setText(alamat);
        harga_destinasi.setText("RP. "+harga);
        suhu_destinasi.setText(suhu + " C");
        kelembapan_destinasi.setText(kelembapan);
        keterangan_destinasi.setText("\t "+keterangan);


        try{
            int sarans =  Integer.parseInt(suhu);
            if(sarans < 23) {
                saran.setText("Cuaca dingin, gunakan pakaian yang hangat ya");
            } else if(sarans > 23) {
                saran.setText("Cuaca Cerah, bersenang senang lah");
            }else if(sarans > 28) {
                saran.setText("Cuacanya panas, gunakan pakaian yang cocok saat cuaca panas");
            }
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }


    }


}