package com.sttbandung.skutbandung.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sttbandung.skutbandung.ClickListener.ItemClickListener;
import com.sttbandung.skutbandung.activity.ListDestinasiActivity;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.adapter.KategoriDestinasiAdapter;
import com.sttbandung.skutbandung.pojo.KategoriDestinasi;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestinasiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestinasiFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private KategoriDestinasiAdapter AdapterDestinasi;
    private ArrayList<KategoriDestinasi> kategoriDestinasiArrayList;

    public DestinasiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DestinasiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DestinasiFragment newInstance(String param1, String param2) {
        DestinasiFragment fragment = new DestinasiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destinasi, container,false);
        recyclerView = view.findViewById(R.id.card_destinasi);
        getKategori();
        AdapterDestinasi = new KategoriDestinasiAdapter(kategoriDestinasiArrayList, getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(AdapterDestinasi);

        AdapterDestinasi.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view,int position){
                KategoriDestinasi coll = new KategoriDestinasi();
                String nama = kategoriDestinasiArrayList.get(position).getNama_destinasi();

                coll.setNama_destinasi(nama);

                Intent i = new Intent(getActivity(), ListDestinasiActivity.class);
                i.putExtra("DESTINASI", coll);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);

            }
        });

        return view;
    }

    private void getKategori() {
        if (kategoriDestinasiArrayList == null) {
            Resources resources = getResources();
            String[] kategoriName = resources.getStringArray(R.array.nama_wisata);
            final TypedArray kategoriImg = resources.obtainTypedArray(R.array.foto_wisata);

            kategoriDestinasiArrayList = new ArrayList<KategoriDestinasi>();
            for (int i = 0; i < kategoriName.length; i++) {
                kategoriDestinasiArrayList.add(new KategoriDestinasi(kategoriName[i], String.valueOf(kategoriImg.getResourceId(i, -1))));
            }
        }
    }
}