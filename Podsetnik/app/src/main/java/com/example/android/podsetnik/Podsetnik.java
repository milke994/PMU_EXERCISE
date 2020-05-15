package com.example.android.podsetnik;

public class Podsetnik {

    private String mNaziv;
    private String mDatum;

    public Podsetnik(String mNaziv, String mDatum) {
        this.mNaziv = mNaziv;
        this.mDatum = mDatum;
    }

    public String getNaziv() {
        return mNaziv;
    }

    public String getDatum() {
        return mDatum;
    }
}
