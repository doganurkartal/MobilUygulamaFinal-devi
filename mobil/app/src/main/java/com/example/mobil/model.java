package com.example.mobil;

public class model {
    public String getBilgi() {
        return bilgi;
    }

    public model() {
    }

    public model(String bilgi, String resim, String saat, String ucret) {
        this.bilgi = bilgi;
        this.resim = resim;
        this.saat = saat;
        this.ucret = ucret;
    }

    public void setBilgi(String bilgi) {
        this.bilgi = bilgi;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getUcret() {
        return ucret;
    }

    public void setUcret(String ucret) {
        this.ucret = ucret;
    }

    private String bilgi;
    private String resim;
    private String saat;
    private String ucret;
}
