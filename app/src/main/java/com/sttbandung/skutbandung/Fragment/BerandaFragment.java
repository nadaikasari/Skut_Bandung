package com.sttbandung.skutbandung.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sttbandung.skutbandung.MainActivity;
import com.sttbandung.skutbandung.activity.RiwayatTransaksiActivity;
import com.sttbandung.skutbandung.activity.TopupActivity;
import com.sttbandung.skutbandung.databinding.FragmentBerandaBinding;
import com.sttbandung.skutbandung.mvvm.BerandaViewModel;

import java.text.NumberFormat;
import java.util.Locale;

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

    private FragmentBerandaBinding binding;
    private BerandaViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBerandaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setHasOptionsMenu(true);

        //get data from activity
        MainActivity activity = (MainActivity) getActivity();
        String DataNama = activity.getNamaUser();
        String DataID = activity.getIdUser();
        String DataEmail = activity.getEmailUser();

        viewModel = new ViewModelProvider(this).get(BerandaViewModel.class);
        viewModel.getSaldoUser(DataEmail);
        getDataSaldoUser();

        viewModel.getStatusDone(DataID);
        getTransaksiDone();

        viewModel.getStatusDelayed(DataID);
        getTransaksiDelayed();

        viewModel.getStatusUnUsed(DataID);
        getTransaksiNotUsed();

        //set text with value
        binding.welcomeText.setText("Selamat Datang \n" + DataNama);

        viewModel.isLoading().observe(getViewLifecycleOwner(), this::showLoading);
        viewModel.messageError().observe(getViewLifecycleOwner(), this::showMessage);

        binding.btnTopup.setOnClickListener((View.OnClickListener) v -> {
            Intent i = new Intent(getActivity(), TopupActivity.class);
            i.putExtra("ID_USER", DataID);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0, 0);
        });

        binding.buttonSemuatransaksi.setOnClickListener((View.OnClickListener) v -> {
            Intent i = new Intent(getActivity(), RiwayatTransaksiActivity.class);
            i.putExtra("ID_USER", DataID);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0, 0);
        });

        return view;
    }

    private void getTransaksiDone() {
        viewModel.getTransaksiDone().observe(getViewLifecycleOwner(), _ResultTransaksiDone -> binding.jumlahTransaksi3.setText(String.valueOf(_ResultTransaksiDone)));
    }

    private void getTransaksiDelayed() {
        viewModel.getTransaksiDelayed().observe(getViewLifecycleOwner(), _ResultTransaksiDelayed -> binding.jumlahTransaksi2.setText(String.valueOf(_ResultTransaksiDelayed)));
    }

    private void getTransaksiNotUsed() {
        viewModel.getTransaksiNotUsed().observe(getViewLifecycleOwner(), _ResultTransaksiNotUsed -> binding.jumlahTransaksi1.setText(String.valueOf(_ResultTransaksiNotUsed)));
    }

    private void getDataSaldoUser() {
        viewModel.getResultSaldo().observe(getViewLifecycleOwner(), _ResultSaldoUser -> binding.saldoUser.setText("Saldo Anda : " + formatRupiah(Double.valueOf(_ResultSaldoUser))));
    }

    private void showLoading(Boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * Menampilkan pesan error
     */
    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Format angka menjadi rupiah
     */
    private String formatRupiah(Double number) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

}