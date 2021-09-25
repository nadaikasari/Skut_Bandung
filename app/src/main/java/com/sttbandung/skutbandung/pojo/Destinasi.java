package com.sttbandung.skutbandung.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Destinasi implements Parcelable {

    private String nama, gambar, alamat, kategori_wisata, ketegori_kecamatan, keterangan, id, pengunjung, harga, suhu, kelembapan;

    public Destinasi() {
    }

    public Destinasi(String id, String pengunjung, String nama, String gambar, String alamat, String kategori_wisata, String kategori_kecamatan, String keterangan, String harga, String suhu, String kelembapan) {
        this.nama = nama;
        this.gambar = gambar;
        this.alamat = alamat;
        this.kategori_wisata = kategori_wisata;
        this.ketegori_kecamatan = kategori_kecamatan;
        this.keterangan = keterangan;
        this.id = id;
        this.pengunjung = pengunjung;
        this.harga = harga;
        this.suhu = suhu;
        this.kelembapan = kelembapan;
    }

    protected Destinasi(Parcel in) {
        id = in.readString();
        pengunjung = in.readString();
        nama = in.readString();
        gambar = in.readString();
        alamat = in.readString();
        kategori_wisata = in.readString();
        ketegori_kecamatan = in.readString();
        keterangan = in.readString();
        harga = in.readString();
        suhu = in.readString();
        kelembapan = in.readString();
    }

    public static final Creator<Destinasi> CREATOR = new Creator<Destinasi>() {
        @Override
        public Destinasi createFromParcel(Parcel in) {
            return new Destinasi(in);
        }

        @Override
        public Destinasi[] newArray(int size) {
            return new Destinasi[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKategori_wisata() {
        return kategori_wisata;
    }

    public void setKategori_wisata(String kategori_wisata) {
        this.kategori_wisata = kategori_wisata;
    }

    public String getKetegori_kecamatan() {
        return ketegori_kecamatan;
    }

    public void setKetegori_kecamatan(String ketegori_kecamatan) {
        this.ketegori_kecamatan = ketegori_kecamatan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPengunjung() {
        return pengunjung;
    }

    public void setPengunjung(String pengunjung) {
        this.pengunjung = pengunjung;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    public String getKelembapan() {
        return kelembapan;
    }

    public void setKelembapan(String kelembapan) {
        this.kelembapan = kelembapan;
    }

    public static Creator<Destinasi> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(pengunjung);
        dest.writeString(nama);
        dest.writeString(gambar);
        dest.writeString(alamat);
        dest.writeString(kategori_wisata);
        dest.writeString(ketegori_kecamatan);
        dest.writeString(keterangan);
        dest.writeString(harga);
        dest.writeString(suhu);
        dest.writeString(kelembapan);
    }
}
