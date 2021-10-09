package com.sttbandung.skutbandung.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sttbandung.skutbandung.ClickListener.ItemClickListener;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> ArrayList;
    private ItemClickListener itemClickListener;
    private Context context;
    private AdapterView.OnItemClickListener mListener;
    private User data;

    public UserAdapter(java.util.ArrayList<User> arrayList, Context context) {
        ArrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.nama.setText(ArrayList.get(i).getNama());
        holder.uid.setText(ArrayList.get(i).getUid());
        holder.email.setText(ArrayList.get(i).getEmail());
        holder.notlpn.setText(ArrayList.get(i).getNotlpn());
    }

    @Override
    public int getItemCount() {
        return ArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView nama, uid;
        private EditText email, notlpn;
        private Button btn_update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_user);
            nama = itemView.findViewById(R.id.nama_lengkap);
            uid = itemView.findViewById(R.id.uid);
            email = itemView.findViewById(R.id.isi_email);
            notlpn = itemView.findViewById(R.id.isi_notlpn);
            btn_update = itemView.findViewById(R.id.btn_edit_profil);
        }
    }

    public void addData(List<User> data) {
        ArrayList.addAll(data);
        notifyDataSetChanged();
    }
}
