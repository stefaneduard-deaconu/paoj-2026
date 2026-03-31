package com.pao.laboratory06.exercise3.models;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private List<String> smsTrimise = new ArrayList<>();

    public PersoanaJuridica(String nume, String prenume, String telefon) {
        super(nume, prenume, telefon);
    }

    @Override
    public boolean trimiteSMS(String mesaj) {
        if (this.telefon == null || this.telefon.isEmpty() || mesaj == null || mesaj.isEmpty()) {
            return false;
        }
        smsTrimise.add(mesaj);
        return true;
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || parola == null) throw new IllegalArgumentException();
        System.out.println("Persoana Juridica autentificata.");
    }

    @Override
    public double consultareSold() { return 10000.0; }

    @Override
    public boolean efectuarePlata(double suma) { return true; }

    public List<String> getSmsTrimise() { return smsTrimise; }
}