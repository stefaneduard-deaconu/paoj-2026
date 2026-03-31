package com.pao.laboratory06.exercise3;

public class Inginer extends Angajat implements PlataOnline, Comparable<Inginer> {
    private double soldCont = 5000.0;

    public Inginer(String nume, String prenume, String telefon, double salariu) {
        super(nume, prenume, telefon, salariu);
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.trim().isEmpty() || parola == null || parola.trim().isEmpty()) {
            throw new IllegalArgumentException("Userul și parola nu pot fi nule sau goale.");
        }
        System.out.println("Inginer " + nume + " s-a autentificat cu succes.");
    }

    @Override
    public double consultareSold() {
        return soldCont;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) throw new IllegalArgumentException("Suma de plată trebuie să fie pozitivă.");
        if (suma > soldCont) return false;

        soldCont -= suma;
        return true;
    }

    @Override
    public int compareTo(Inginer altul) {
        return this.nume.compareTo(altul.nume);
    }

    @Override
    public String toString() {
        return "Inginer{" + "nume='" + nume + '\'' + ", salariu=" + salariu + '}';
    }
}