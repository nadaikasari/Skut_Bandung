package com.sttbandung.skutbandung.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String id, nama, uid, email, notlpn, image, saldo;

    public User() {
    }

    public User(String id, String nama, String uid, String email, String notlpn, String image, String saldo) {
        this.id = id;
        this.nama = nama;
        this.uid = uid;
        this.email = email;
        this.notlpn = notlpn;
        this.image = image;
        this.saldo = saldo;
    }

    protected User(Parcel in) {
        id = in.readString();
        nama = in.readString();
        uid = in.readString();
        email = in.readString();
        notlpn = in.readString();
        image = in.readString();
        saldo = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotlpn() {
        return notlpn;
    }

    public void setNotlpn(String notlpn) {
        this.notlpn = notlpn;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(uid);
        dest.writeString(email);
        dest.writeString(notlpn);
        dest.writeString(image);
        dest.writeString(saldo);
    }
}
