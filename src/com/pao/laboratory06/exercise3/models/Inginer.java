package com.pao.laboratory06.exercise3.models;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {

    public Inginer(String nume, String prenume, String telefon, double salariu) {
        super(nume, prenume, telefon, salariu);
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.isEmpty() || parola == null || parola.isEmpty()) {
            throw new IllegalArgumentException("Credentiale invalide!");
        }
        System.out.println("Inginerul " + nume + " s-a autentificat.");
    }

    @Override
    public double consultareSold() {
        return this.salariu;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        return suma <= salariu;
    }

    @Override
    public int compareTo(Inginer o) {
        return this.nume.compareTo(o.nume);
    }

    @Override
    public String toString() {
        return "Inginer: " + nume + " " + prenume + " Salariu: " + salariu;
    }
}