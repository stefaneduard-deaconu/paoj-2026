package com.pao.project.model;

public class Locatie {

    private String oras;
    private String adresa;


    private Pozitie pozitie;

    public Locatie(String oras, String adresa) {
        this.oras = oras;
        this.adresa = adresa;
    }

    public Locatie(String oras, String adresa, Pozitie pozitie) {
        this.oras = oras;
        this.adresa = adresa;
        this.pozitie = pozitie;
    }

    public Locatie(String adresa, Pozitie pozitie) {
        this.adresa = adresa;
        this.pozitie = pozitie;
    }

    public String getOras() {
        return oras;
    }

    public String getAdresa() {
        return adresa;
    }

    public Pozitie getPozitie() {
        return this.pozitie;
    }

    public void setPozitie(Pozitie pozitie) {
        this.pozitie = pozitie;
    }

}