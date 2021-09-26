package com.sttbandung.skutbandung.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.sttbandung.skutbandung.ClickListener.ItemClickListener;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.activity.DetailDestinasiActivity;
import com.sttbandung.skutbandung.activity.ListDestinasiActivity;
import com.sttbandung.skutbandung.activity.TransaksiActivity;
import com.sttbandung.skutbandung.pojo.Destinasi;

import java.util.ArrayList;

public class DestinasiAdapter extends RecyclerView.Adapter<DestinasiAdapter.ViewHolder>{

    private ArrayList<Destinasi> ArrayList;
    private ItemClickListener itemClickListener;
    private Context context;
    private OnItemClickListener mListener;

    public DestinasiAdapter(ArrayList<Destinasi> arrayList, Context context) {
        ArrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DestinasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_destinasi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinasiAdapter.ViewHolder holder, int i) {
        Destinasi currentItem = ArrayList.get(i);

        String nama = currentItem.getNama();
        String harga = currentItem.getHarga();
        String pengunjung = currentItem.getPengunjung();
        String images = currentItem.getGambar();

        holder.nama.setText(nama);
        holder.harga.setText("Harga : "+harga);
        holder.pengunjung.setText("Pengunjung : "+pengunjung);
        Picasso.get().load(images).fit().centerInside().into(holder.image);


    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, harga, pengunjung;
        private ImageView image;
        private Button btn_pesan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tv_nama_wisata);
            harga = itemView.findViewById(R.id.tv_harga);
            pengunjung = itemView.findViewById(R.id.tv_jmlpengunjung);
            image =  itemView.findViewById(R.id.img_destinasi);
            btn_pesan = itemView.findViewById(R.id.btn_pesan);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }


    }
}
