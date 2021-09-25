package com.sttbandung.skutbandung.pojo;

public class user {
    private String id, nama, uid, email, notlpn, image, saldo;

    public user() {
    }

    public user(String id, String nama, String uid, String email, String notlpn, String image, String saldo) {
        this.id = id;
        this.nama = nama;
        this.uid = uid;
        this.email = email;
        this.notlpn = notlpn;
        this.image = image;
        this.saldo = saldo;
    }

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
}
