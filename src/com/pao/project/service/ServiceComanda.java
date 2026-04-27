package com.pao.project.service;

import com.pao.project.model.Comanda;

import java.util.ArrayList;
import java.util.List;

public class ServiceComanda {

    private static ServiceComanda INSTANCE;
    private List<Comanda> comenzi = new ArrayList<>();

    private ServiceComanda(){
    }

    public static ServiceComanda getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceComanda();
        }
        return INSTANCE;
    }


    public void adaugaComanda(Comanda c) {
        comenzi.add(c);
    }

    public Comanda cautaComanda(String id) {

        for (Comanda c : comenzi) {
            if (c.getId().equals(id)) {
                return c;
            }
        }

        return null;
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }
}