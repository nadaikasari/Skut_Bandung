package com.sttbandung.skutbandung.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sttbandung.skutbandung.ClickListener.ItemClickListener;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.pojo.Destinasi;
import com.sttbandung.skutbandung.pojo.RiwayatTransaksi;

import java.util.ArrayList;

public class RiwayatTransaksiAdapter extends RecyclerView.Adapter<RiwayatTransaksiAdapter.ViewHolder>{

    private ArrayList<RiwayatTransaksi> ArrayList;
    private ItemClickListener itemClickListener;
    private Context context;
    private OnItemClickListener mListener;

    public RiwayatTransaksiAdapter(java.util.ArrayList<RiwayatTransaksi> arrayList, Context context) {
        ArrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatTransaksiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_riwayat_transaksi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTransaksiAdapter.ViewHolder holder, int i) {
        RiwayatTransaksi currentItem = ArrayList.get(i);

        String nama = currentItem.getNama_destinasi();
        String tanggal = currentItem.getCreate_at();
        String status = currentItem.getStatus();

        holder.nama.setText(nama);
        holder.create_At.setText(tanggal);

        //Mengatur status transaksi
        if (status.equals("1")) {
            holder.status.setText("Sudah Dipakai");
            holder.status.setTextColor(Color.RED);
        } else {
            holder.status.setText("Belum Dipakai");
            holder.status.setTextColor(Color.GREEN);
        }


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
        private TextView nama, status, create_At;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.riwayat_namawisata);
            status = itemView.findViewById(R.id.status_riwayat);
            create_At = itemView.findViewById(R.id.tanggal_riwayat);
        }
    }
}
