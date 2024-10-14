package com.example.demo;

public class Malzeme {
    private String malzemeAdi;
    private int miktar;
    private String birim;
    private double birimFiyat;

    public Malzeme(String malzemeAdi, int miktar, String birim, double birimFiyat) {
        this.malzemeAdi = malzemeAdi;
        this.miktar = miktar;
        this.birim = birim;
        this.birimFiyat = birimFiyat;
    }

    public String getMalzemeAdi() {
        return malzemeAdi;
    }

    public int getMiktar() {
        return miktar;
    }

    public String getBirim() {
        return birim;
    }

    public double getBirimFiyat() {
        return birimFiyat;
    }
}
