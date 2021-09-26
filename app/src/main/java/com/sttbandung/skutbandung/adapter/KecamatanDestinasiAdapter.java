package com.sttbandung.skutbandung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sttbandung.skutbandung.ClickListener.ItemClickListener;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.pojo.Kecamatan;

import java.util.ArrayList;

public class KecamatanDestinasiAdapter extends RecyclerView.Adapter<KecamatanDestinasiAdapter.ViewHolder>{
    private ArrayList<Kecamatan> kecamatans;
    private ItemClickListener itemClickListener;
    private Context context;
    private OnItemClickListener kListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        kListener = listener;
    }

    public KecamatanDestinasiAdapter(ArrayList<Kecamatan> kecamatans, Context context) {
        this.kecamatans = kecamatans;
        this.context = context;
    }

    @NonNull
    @Override
    public KecamatanDestinasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_kecamatan,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KecamatanDestinasiAdapter.ViewHolder holder, int i) {
        holder.kecamatan.setText(kecamatans.get(i).getKecamatan());
    }

    @Override
    public int getItemCount() {
        return kecamatans!=null ? kecamatans.size():0;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kecamatan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kecamatan = itemView.findViewById(R.id.nama_kecamatan);
        }
    }
}
