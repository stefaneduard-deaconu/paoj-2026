package com.pao.project.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Firma {
    final private int CUI;



    final private String numar_ordine_Registru_Comert;
    private String adresa_sediu_social;
    private String nume_firma;
    private String numar_telefon;
    private String adresa_email;


//    Responsabil Firma_restaurant
    private String nume_responsabil_restaurant;
    private String email_responsabil_restaurant;
    private String telefon_responsabil_restaurant;
    private double valoare_vanzari_prin_aplicatie;

    Set<Restaurant> restaurante = new HashSet<>();

    public Firma(int CUI, String nrOrdine, String nume) {
        this.CUI = CUI;
        this.nume_firma = nume;
        this.numar_ordine_Registru_Comert = nrOrdine;
    }


    public String getNumar_ordine_Registru_Comert() {
        return numar_ordine_Registru_Comert;
    }

    public String getNumar_telefon() {
        return numar_telefon;
    }

    public void setNumar_telefon(String numar_telefon) {
        this.numar_telefon = numar_telefon;
    }

    public String getAdresa_email() {
        return adresa_email;
    }

    public void setAdresa_email(String adresa_email) {
        this.adresa_email = adresa_email;
    }

    public String getNume_responsabil_restaurant() {
        return nume_responsabil_restaurant;
    }

    public void setNume_responsabil_restaurant(String nume_responsabil_restaurant) {
        this.nume_responsabil_restaurant = nume_responsabil_restaurant;
    }

    public String getEmail_responsabil_restaurant() {
        return email_responsabil_restaurant;
    }

    public void setEmail_responsabil_restaurant(String email_responsabil_restaurant) {
        this.email_responsabil_restaurant = email_responsabil_restaurant;
    }

    public String getTelefon_responsabil_restaurant() {
        return telefon_responsabil_restaurant;
    }

    public void setTelefon_responsabil_restaurant(String telefon_responsabil_restaurant) {
        this.telefon_responsabil_restaurant = telefon_responsabil_restaurant;
    }

    public Double getValoare_vanzari_prin_aplicatie() {
        return this.valoare_vanzari_prin_aplicatie;
    }


    public void adaugaRestaurant(Restaurant restaurant) {
        this.restaurante.add(restaurant);
    }

    public Set<Restaurant> getRestaurante() {
        return restaurante;
    }





    public void setAdresa_sediu_social(String adresa_sediu_social) {
        this.adresa_sediu_social = adresa_sediu_social;
    }

    public void setNume_firma(String nume_firma) {
        this.nume_firma = nume_firma;
    }

    public Firma(int CUI, String numar_ordine_Registru_Comert, String adresa_sediu_social, String nume_firma) {
        this.CUI = CUI;
        this.numar_ordine_Registru_Comert = numar_ordine_Registru_Comert;
        this.adresa_sediu_social = adresa_sediu_social;
        this.nume_firma = nume_firma;
    }



    public Firma(int CUI, String numar_ordine_Registru_Comert, String adresa_sediu_social,
                 String nume_firma, String numar_telefon,
                 String adresa_email, String nume_responsabil_restaurant,
                 String email_responsabil_restaurant, String telefon_responsabil_restaurant) {
        this.CUI = CUI;
        this.numar_ordine_Registru_Comert = numar_ordine_Registru_Comert;
        this.adresa_sediu_social = adresa_sediu_social;
        this.nume_firma = nume_firma;
        this.numar_telefon = numar_telefon;
        this.adresa_email = adresa_email;
        this.nume_responsabil_restaurant = nume_responsabil_restaurant;
        this.email_responsabil_restaurant = email_responsabil_restaurant;
        this.telefon_responsabil_restaurant = telefon_responsabil_restaurant;
    }

    public int getCUI() {
        return CUI;
    }

    public Firma(int cui, String nrRegistru, String numeFirma, String adresa, String telefon, String email) {
        this.CUI = cui;
        this.numar_ordine_Registru_Comert = nrRegistru;
        this.nume_firma = numeFirma;
        this.adresa_sediu_social = adresa;
        this.numar_telefon = telefon;
        this.adresa_email = email;
        this.restaurante = new HashSet<>();
        this.valoare_vanzari_prin_aplicatie = 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firma firma = (Firma) o;
        return CUI == firma.CUI;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.CUI);
    }

    public String getNume() {
        return this.nume_firma;
    }

    @Override
    public String toString() {
        return "Firma{" +
                "cui=" + CUI +
                ", numeFirma='" + nume_firma + '\'' +
                ", adresaSediuSocial='" + adresa_sediu_social + '\'' +
                ", totalVanzari=" + valoare_vanzari_prin_aplicatie +
                ", numarRestaurante=" + restaurante.size() +
                '}';
    }


}
