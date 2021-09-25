package com.sttbandung.skutbandung.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class KategoriDestinasi implements Parcelable {
    String nama_destinasi, gambar_destinasi;

    public KategoriDestinasi() {
    }

    public KategoriDestinasi(String nama_destinasi, String gambar_destinasi) {
        this.nama_destinasi = nama_destinasi;
        this.gambar_destinasi = gambar_destinasi;
    }

    protected KategoriDestinasi(Parcel in) {
        nama_destinasi = in.readString();
        gambar_destinasi = in.readString();
    }

    public static final Creator<KategoriDestinasi> CREATOR = new Creator<KategoriDestinasi>() {
        @Override
        public KategoriDestinasi createFromParcel(Parcel in) {
            return new KategoriDestinasi(in);
        }

        @Override
        public KategoriDestinasi[] newArray(int size) {
            return new KategoriDestinasi[size];
        }
    };

    public String getNama_destinasi() {
        return nama_destinasi;
    }

    public void setNama_destinasi(String nama_destinasi) {
        this.nama_destinasi = nama_destinasi;
    }

    public String getGambar_destinasi() {
        return gambar_destinasi;
    }

    public void setGambar_destinasi(String gambar_destinasi) {
        this.gambar_destinasi = gambar_destinasi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama_destinasi);
        dest.writeString(gambar_destinasi);
    }
}
