package com.sttbandung.skutbandung;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.sttbandung.skutbandung.pojo.user;

import java.util.ArrayList;

public class MainModelView extends AndroidViewModel {

//    private MahasiswaRepository mahasiswaRepository;
//    private MutableLiveData<ArrayList<user>> mahasiswaMutableLive;
//
    public MainModelView(@NonNull Application application) {
        super(application);
    }
//
//    public MutableLiveData<ArrayList<Mahasiswa>> getMahasiswa() {
//        return mahasiswaMutableLive = mahasiswaRepository.getAllMahasiswa();
//    }
}
