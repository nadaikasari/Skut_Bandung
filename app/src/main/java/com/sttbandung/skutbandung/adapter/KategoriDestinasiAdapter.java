package com.sttbandung.skutbandung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sttbandung.skutbandung.ClickListener.ItemClickListener;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.pojo.KategoriDestinasi;

import java.util.ArrayList;

public class KategoriDestinasiAdapter extends RecyclerView.Adapter<KategoriDestinasiAdapter.ViewHolder>{
    private final ArrayList<KategoriDestinasi> kategoriDestinasis;
    private final Context context;
    private ItemClickListener itemClickListener;

    public KategoriDestinasiAdapter(ArrayList<KategoriDestinasi> kategoriDestinasis, Context context) {
        this.kategoriDestinasis = kategoriDestinasis;
        this.context = context;
    }

    @NonNull
    @Override
    public KategoriDestinasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_kategori_destinasi,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriDestinasiAdapter.ViewHolder holder, int i) {
        holder.txtNama.setText(kategoriDestinasis.get(i).getNama_destinasi());
        holder.img.setImageResource(Integer.parseInt(kategoriDestinasis.get(i).getGambar_destinasi()));
    }

    @Override
    public int getItemCount() {
        return kategoriDestinasis!=null ? kategoriDestinasis.size():0;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtNama;
        ImageView img;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNama=itemView.findViewById(R.id.nama_kategori);
            img=itemView.findViewById(R.id.gambar_kategori);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListener!=null)
                itemClickListener.onClick(v,getAdapterPosition());

        }
    }

}
