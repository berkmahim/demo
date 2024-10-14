package com.example.demo;

import java.util.List;

public class Tarif {
    private int tarifID;
    private String tarifAdi;
    private String kategori;
    private String hazirlamaSuresi;
    private String talimatlar;
    private List<Malzeme> malzemeler; // Malzemeleri liste olarak ekle

    public Tarif(int tarifID, String tarifAdi, String kategori, String hazirlamaSuresi, String talimatlar, List<Malzeme> malzemeler) {
        this.tarifID = tarifID;
        this.tarifAdi = tarifAdi;
        this.kategori = kategori;
        this.hazirlamaSuresi = hazirlamaSuresi;
        this.talimatlar = talimatlar;
        this.malzemeler = malzemeler;
    }

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

    public List<Malzeme> getMalzemeler() {
        return malzemeler;
    }
}
