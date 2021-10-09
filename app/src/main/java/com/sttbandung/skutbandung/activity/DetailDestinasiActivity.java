package com.sttbandung.skutbandung.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sttbandung.skutbandung.MainActivity;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.databinding.ActivityDetailDestinasiBinding;

import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_ALAMAT;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_GAMBAR;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_HARGA;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_ID_DESTINASI;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_ID_USER;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_JUMLAH;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_KELEMBAPAN;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_KETERANGAN;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_NAME;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_SALDO_USER;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_SUHU;
import static com.sttbandung.skutbandung.activity.ListDestinasiActivity.EXTRA_UID_USER;

public class DetailDestinasiActivity extends AppCompatActivity {

    private ActivityDetailDestinasiBinding binding;

    private ImageView gambar_destinasi;
    private TextView nama_destinasi, harga_destinasi, keterangan_destinasi, alamat_destinasi, jumlah_pengunjung, suhu_destinasi, kelembapan_destinasi, saran;
    private Button btn_pesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_destinasi);

        Intent intent = getIntent();
        String image = intent.getStringExtra(EXTRA_GAMBAR);
        String id_des = intent.getStringExtra(EXTRA_ID_DESTINASI);
        String nama = intent.getStringExtra(EXTRA_NAME);
        String pengunjung = intent.getStringExtra(EXTRA_JUMLAH);
        String alamat = intent.getStringExtra(EXTRA_ALAMAT);
        String harga = intent.getStringExtra(EXTRA_HARGA);
        String suhu = intent.getStringExtra(EXTRA_SUHU);
        String kelembapan = intent.getStringExtra(EXTRA_KELEMBAPAN);
        String keterangan = intent.getStringExtra(EXTRA_KETERANGAN);
        String id = intent.getStringExtra(EXTRA_ID_USER);
        String uid = intent.getStringExtra(EXTRA_UID_USER);
        String saldo = intent.getStringExtra(EXTRA_SALDO_USER);

        gambar_destinasi = findViewById(R.id.image_destinasi);
        nama_destinasi = findViewById(R.id.nama_wisata);
        harga_destinasi = findViewById(R.id.harga_destinasi);
        keterangan_destinasi = findViewById(R.id.keterangan_destinasi);
        alamat_destinasi = findViewById(R.id.wisata_choice);
        jumlah_pengunjung = findViewById(R.id.jml_pengunjung);
        suhu_destinasi = findViewById(R.id.suhu_destinasi);
        kelembapan_destinasi = findViewById(R.id.kelembapan_destinasi);
        saran = findViewById(R.id.saran);
        btn_pesan = findViewById(R.id.btn_pesan);


        Picasso.get().load(image).into(gambar_destinasi);
        nama_destinasi.setText(nama);
        jumlah_pengunjung.setText(pengunjung);
        alamat_destinasi.setText(alamat);
        harga_destinasi.setText("RP. "+harga);
        suhu_destinasi.setText(suhu + " C");
        kelembapan_destinasi.setText(kelembapan);
        keterangan_destinasi.setText("\t "+keterangan);

        btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailDestinasiActivity.this, TransaksiActivity.class);
                i.putExtra("nama_des", nama_destinasi.getText());
                i.putExtra("id_destinasi", id_des);
                i.putExtra("harga", harga);
                i.putExtra("id", id);
                i.putExtra("uid", uid);
                i.putExtra("saldo", saldo);
                startActivity(i);
            }
        });

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