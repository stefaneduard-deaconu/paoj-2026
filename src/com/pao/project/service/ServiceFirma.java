package com.pao.project.service;

import com.pao.project.exception.FirmaInexistenta;
import com.pao.project.model.Firma;
import com.pao.project.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class ServiceFirma{

    private static ServiceFirma INSTANCE;
    private static List<Firma> firme = new ArrayList<>();

    private ServiceFirma() {
    }

    public static ServiceFirma getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceFirma();
        }
        return INSTANCE;
    }

    public void adaugaFirma(Firma firma) {
        firme.add(firma);
    }

    public Firma cautaFirmaDupaCUI(int CUI) {

        for (var f : firme) {
            if (f.getCUI() == CUI) {
                return f;
            }
        }

        return null;
    }
    public void stergeFirma(int CUI) {

        for (int i = 0; i < firme.size(); i++) {

            if (firme.get(i).getCUI() == CUI) {
                firme.remove(i);
                return;
            }
        }
    }

    public static Firma cautaFirma(int CUI) {
        if(firme.isEmpty()) throw new RuntimeException("Nu exista firme dupa care sa cauti momentan!");
        for(var a : firme){
            if(a.getCUI() == CUI){
                return a;
            }
        }
        throw new RuntimeException("Firma negasita!");
    }


    public void adaugaRestaurantFirma(int cuiFirma, Restaurant restaurant) {

        Firma firma = ServiceFirma.getInstance().cautaFirma(cuiFirma);
        if (firma == null) {
            throw new FirmaInexistenta("Firma nu exista");
        }

        firma.getRestaurante().add(restaurant);
    }


    public List<Restaurant> getToateRestaurantele() {

        List<Restaurant> toate = new ArrayList<>();
        for (var f : firme) {
            for (Restaurant r : f.getRestaurante()) {
                toate.add(r);
            }
        }
        return toate;
    }

    public List<Firma> getToateFirmeleReferinta() {
        return firme;
    }

    public List<Firma> getToateFirmeleClona() {
        List<Firma> clonaFirma = new ArrayList<>();

        for(Firma a : firme){
            Firma clona = a.clone();
            clonaFirma.add(clona);
        }
        return clonaFirma;
    }



}