package com.pao.project.platforma_elearning.service;

import com.pao.project.platforma_elearning.exception.EntitateExistentaException;
import com.pao.project.platforma_elearning.model.Curs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CursService {
    private static CursService instance;
    private List<Curs> cursuri = new ArrayList<>();

    private CursService() {}

    public static CursService getInstance() {
        if (instance == null) {
            instance = new CursService();
        }

        return instance;
    }

    public void adaugaCurs(Curs c) throws EntitateExistentaException {
        if (cautaCursDupaTitlu(c.getTitlu()) != null) {
            throw new EntitateExistentaException("Exista deja un curs cu acest titlu in platforma");
        }

        cursuri.add(c);
    }

    public void afiseazaCursuriDupaPret() {
        if (cursuri.isEmpty()) {
            System.out.println("Nu exista cursuri in platforma momentan");

            return;
        }

        List<Curs> cursuriSortate = new ArrayList<>(this.cursuri);
        Collections.sort(cursuriSortate);

        for (Curs c : cursuriSortate) {
            System.out.println(c);
        }
    }

    public void afiseazaCursuriDupaCategorie(String categorie) {
        boolean gasit = false;

        for (Curs c : cursuri) {
            if (c.getCategorie().equalsIgnoreCase(categorie)) {
                System.out.println(c);
                gasit = true;
            }
        }

        if (!gasit) {
            System.out.println("Nu am gasit niciun curs in categoria: " + categorie);
        }
    }

    public void afiseazaCursuriInstructor(int idInstructor) {
        System.out.println("\nCursurile mele:");
        boolean gasit = false;

        for (Curs c : cursuri) {
            if (c.getIdInstructor() == idInstructor) {
                System.out.println(c);
                gasit = true;
            }
        }

        if (!gasit) {
            System.out.println("Nu ai creat niciun curs inca");
        }
    }

    public Curs cautaCursDupaTitlu(String titlu) {
        return cursuri.stream().filter(c -> c.getTitlu().equalsIgnoreCase(titlu)).findFirst().orElse(null);
    }

    public Curs cautaCursDupaId(int id) {
        return cursuri.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public Curs stergeCurs(String titlu) {
        Curs c = cautaCursDupaTitlu(titlu);

        if (c != null) {
            cursuri.remove(c);
            System.out.println("Cursul a fost eliminat din lista platformei");

            return c;
        }

        System.out.println("Cursul nu a fost gasit");
        return null;
    }
}