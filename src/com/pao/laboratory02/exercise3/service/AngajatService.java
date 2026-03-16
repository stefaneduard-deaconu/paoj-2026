package com.pao.laboratory02.exercise3.service;

import com.pao.laboratory02.exercise3.model.Angajat;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Completează cele 3 metode.
 * Folosește ArrayList — nu mai e nevoie de redimensionare manuală.
 */
public class AngajatService {
    private List<Angajat> angajati;

    public AngajatService() {
        this.angajati = new ArrayList<>();
    }

    /** TODO: angajati.add(a); println("Angajat adăugat: " + a.getName()); */
    public void addAngajat(Angajat a) {
        angajati.add(a);
        System.out.println("Angajat adaugat: " + a.getName());// TODO
    }

    /** TODO: dacă goală → mesaj; altfel parcurge cu index și afișează (i+1) + ". " + angajat */
    public void listAll() {
        if (angajati.isEmpty()) {
            System.out.println("Lista este goala.");
            return;
        }
        for (int i = 0; i < angajati.size(); i++) {
            System.out.println((i + 1) + ". " + angajati.get(i));
        }
    }

    /** TODO: parcurge lista, sumează a.salariuTotal(), returnează totalul. */
    public double totalSalarii() {
        double total = 0;
        for (Angajat a : angajati) {
            total += a.salariuTotal();
        }
        return total;
    }
}
