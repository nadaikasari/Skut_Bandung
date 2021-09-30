package com.sttbandung.skutbandung.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sttbandung.skutbandung.MainActivity;
import com.sttbandung.skutbandung.R;
import com.sttbandung.skutbandung.activity.RiwayatTransaksiActivity;
import com.sttbandung.skutbandung.activity.TopupActivity;
import com.sttbandung.skutbandung.adapter.RiwayatTransaksiAdapter;
import com.sttbandung.skutbandung.handler.Config;
import com.sttbandung.skutbandung.pojo.RiwayatTransaksi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BerandaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BerandaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RequestQueue mRequestQueue;
    private SwipeRefreshLayout doRefresh;
    String locate, newSaldo;

    public BerandaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BerandaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BerandaFragment newInstance(String param1, String param2) {
        BerandaFragment fragment = new BerandaFragment();
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
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        setHasOptionsMenu(true);

        //get data from activity
        MainActivity activity = (MainActivity) getActivity();
        String DataNama = activity.getNamaUser();
        String DataSaldo = activity.getSaldoUser();
        String DataID = activity.getIdUser();


        //set view id
        TextView welcome = (TextView) view.findViewById(R.id.welcome_text);
        TextView saldo_user = (TextView) view.findViewById(R.id.saldo_user);
        TextView transaksi_belum_terpakai = (TextView) view.findViewById(R.id.jumlah_transaksi1);
        TextView transaksi_ditunda = (TextView) view.findViewById(R.id.jumlah_transaksi2);
        TextView transaksi_selesai = (TextView) view.findViewById(R.id.jumlah_transaksi3);

        Button btn_topup = (Button) view.findViewById(R.id.btn_topup);
        Button btn_riwayat_transaksi = (Button) view.findViewById(R.id.button_Semuatransaksi);

        //set text with value
        welcome.setText("Selamat Datang "+DataNama);
        saldo_user.setText("Saldo Anda : Rp."+DataSaldo);


        btn_topup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovetoTopupActivity();
            }
        });

        btn_riwayat_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RiwayatTransaksiActivity.class);
                i.putExtra("ID_USER",DataID);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        return view;

    }

    private void MovetoTopupActivity () {
        Intent i = new Intent(getActivity(), TopupActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }

    private void countData() {
        MainActivity activity = (MainActivity) getActivity();
        String DataID = activity.getIdUser();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Config.RIWAYAT_TRANSAKSI + DataID, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("transaksi");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String create_at = hit.getString("created_att");
                                String status = hit.getString("status");
                                String uid = hit.getString("id_destinasi");

                                JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, Config.DESTINASI_ID + uid, null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                try {
                                                    JSONArray jsonArray = response.getJSONArray("destinasi");
                                                    for (int i = 0; i < jsonArray.length(); i++) {
                                                        JSONObject hit2 = jsonArray.getJSONObject(i);
                                                        String nama_destinasi = hit2.getString("nama_destinasi");
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                    }
                                });
                                mRequestQueue.add(request2);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }


}