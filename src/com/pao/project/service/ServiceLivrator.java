package com.pao.project.service;

import com.pao.project.model.Livrator;
import com.pao.project.model.Locatie;
import com.pao.project.model.SoferDistantaRestaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceLivrator {

    private List<Livrator> soferi = new ArrayList<>();
    private static ServiceLivrator INSTANCE;


    private ServiceLivrator(){
    }

    public static ServiceLivrator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceLivrator();
        }
        return INSTANCE;
    }


    public void adaugaSofer(Livrator s) {
        soferi.add(s);
    }

    public List<Livrator> sorteazaDupaDistanta(Locatie locatie) {

        List<Livrator> lista = new ArrayList<>(soferi);
        Collections.sort(lista, new SoferDistantaRestaurant(locatie));
        return lista;
    }

    public Livrator celMaiApropiat(Locatie locatie) {

        List<Livrator> lista = sorteazaDupaDistanta(locatie);
        for (Livrator s: lista) {
            if (s.isDisponibil()) {
                return s;
            }
        }
        throw new RuntimeException("Nu exista un cel mai apropriat!");

    }

}
