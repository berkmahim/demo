package com.example.demo;

public class Malzeme {
    private String malzemeAdi;
    private int kullanilanMiktar;
    private String birim;
    private double birimFiyat;
    private int toplamMiktar; // Toplam miktar

    public Malzeme(String malzemeAdi, int kullanilanMiktar, String birim, double birimFiyat, int toplamMiktar) {
        this.malzemeAdi = malzemeAdi;
        this.kullanilanMiktar = kullanilanMiktar;
        this.birim = birim;
        this.birimFiyat = birimFiyat;
        this.toplamMiktar = toplamMiktar;
    }

    public String getMalzemeAdi() {
        return malzemeAdi;
    }

    public int getKullanilanMiktar() {
        return kullanilanMiktar;
    }

    public String getBirim() {
        return birim;
    }

    public double getBirimFiyat() {
        return birimFiyat;
    }

    public int getToplamMiktar() {
        return toplamMiktar;
    }
}
