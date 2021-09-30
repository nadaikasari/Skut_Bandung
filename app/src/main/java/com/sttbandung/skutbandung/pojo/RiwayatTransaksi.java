package com.sttbandung.skutbandung.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class RiwayatTransaksi implements Parcelable {
    private String nama_destinasi, create_at, status;

    public RiwayatTransaksi(String nama_destinasi, String create_at, String status) {
        this.nama_destinasi = nama_destinasi;
        this.create_at = create_at;
        this.status = status;
    }

    protected RiwayatTransaksi(Parcel in) {
        nama_destinasi = in.readString();
        create_at = in.readString();
        status = in.readString();
    }

    public static final Creator<RiwayatTransaksi> CREATOR = new Creator<RiwayatTransaksi>() {
        @Override
        public RiwayatTransaksi createFromParcel(Parcel in) {
            return new RiwayatTransaksi(in);
        }

        @Override
        public RiwayatTransaksi[] newArray(int size) {
            return new RiwayatTransaksi[size];
        }
    };

    public String getNama_destinasi() {
        return nama_destinasi;
    }

    public void setNama_destinasi(String nama_destinasi) {
        this.nama_destinasi = nama_destinasi;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nama_destinasi);
        dest.writeString(create_at);
        dest.writeString(status);
    }
}
