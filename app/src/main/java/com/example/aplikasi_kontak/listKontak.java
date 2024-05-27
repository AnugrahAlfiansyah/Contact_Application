package com.example.aplikasi_kontak;

public class listKontak {
    String id_kontak="", namal="", namap="", noHp="", alamat="";

    public listKontak(String id_kontak, String namal, String namap, String noHp, String alamat) {
        this.namal = namal;
        this.namap = namap;
        this.noHp = noHp;
        this.alamat = alamat;
        this.id_kontak = id_kontak;
    }

    public String getId_kontak() {
        return id_kontak;
    }

    public String getNamal() {
        return namal;
    }

    public String getNamap() {
        return namap;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getAlamat() {
        return alamat;
    }
}
