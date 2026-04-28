package com.pao.project.model;

import java.util.Comparator;

public class Livrator extends Angajat implements Comparable<Livrator> {

    private Pozitie pozitie;
    private boolean disponibil;

    public Pozitie getPozitie() {
        return pozitie;
    }

    private void setPozitie(double x, double y) {
        this.pozitie.setX(x);
        this.pozitie.setY(y);
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

    public void updatePozitieSofer(double x, double y){
        this.pozitie.setX(x);
        this.pozitie.setY(y);
        this.pozitie.updatePozitie();
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