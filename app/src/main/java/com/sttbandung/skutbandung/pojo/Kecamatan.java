package com.sttbandung.skutbandung.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Kecamatan implements Parcelable {
    private String Kecamatan;

    public Kecamatan(String kecamatan) {
        Kecamatan = kecamatan;
    }

    protected Kecamatan(Parcel in) {
        Kecamatan = in.readString();
    }

    public static final Creator<Kecamatan> CREATOR = new Creator<Kecamatan>() {
        @Override
        public Kecamatan createFromParcel(Parcel in) {
            return new Kecamatan(in);
        }

        @Override
        public Kecamatan[] newArray(int size) {
            return new Kecamatan[size];
        }
    };

    public String getKecamatan() {
        return Kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        Kecamatan = kecamatan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Kecamatan);
    }
}


