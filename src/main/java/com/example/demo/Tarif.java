package com.example.demo;

public class Tarif {
    private int tarifID;
    private String tarifAdi;
    private String kategori; // Örnek olarak kategori
    private String hazirlamaSuresi; // Hazırlama süresi
    private String talimatlar; // Talimatlar

    public Tarif(int tarifID, String tarifAdi, String kategori, String hazirlamaSuresi, String talimatlar) {
        this.tarifID = tarifID;
        this.tarifAdi = tarifAdi;
        this.kategori = kategori;
        this.hazirlamaSuresi = hazirlamaSuresi;
        this.talimatlar = talimatlar;
    }

    // Getter metodları
    public int getTarifID() {
        return tarifID;
    }

    public String getTarifAdi() {
        return tarifAdi;
    }

    public String getKategori() {
        return kategori;
    }

    public String getHazirlamaSuresi() {
        return hazirlamaSuresi;
    }

    public String getTalimatlar() {
        return talimatlar;
    }
}
