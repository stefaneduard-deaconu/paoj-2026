package com.pao.project.model;

import java.util.Comparator;

public class Livrator extends Angajat implements Comparable<Livrator> {

    private Pozitie pozitie;
    private boolean disponibil;

    public Pozitie getPozitie() {
        return pozitie;
    }

    public Livrator(int id, String nume, Pozitie pozitie) {
        super(id, nume);
        this.pozitie = pozitie;
        this.disponibil = true;
    }

    public boolean isDisponibil() {
        return disponibil;
    }

    public void setDisponibil(boolean disponibil) {
        this.disponibil = disponibil;
    }

    @Override
    public String getRol() {
        return "SOFER";
    }

    @Override
    public int compareTo(Livrator other) {
        return Integer.compare(this.id, other.id);
    }


}