package com.pao.laboratory06.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PersoanaJuridica extends Persoana implements PlataOnlineSMS {
    private double soldFirma = 100000.0;
    private List<String> smsTrimise;

    public PersoanaJuridica(String nume, String prenume, String telefon) {
        super(nume, prenume, telefon);
        this.smsTrimise = new ArrayList<>();
    }

    @Override
    public void autentificare(String user, String parola) {
        if (user == null || user.trim().isEmpty() || parola == null || parola.trim().isEmpty()) {
            throw new IllegalArgumentException("Credențiale invalide pentru PJ.");
        }
        System.out.println("Persoana Juridica " + nume + " s-a autentificat.");
    }

    @Override
    public double consultareSold() {
        return soldFirma;
    }

    @Override
    public boolean efectuarePlata(double suma) {
        if (suma <= 0) throw new IllegalArgumentException("Suma trebuie să fie pozitivă.");
        if (suma > soldFirma) return false;

        soldFirma -= suma;
        return true;
    }

    @Override
    public boolean trimiteSMS(String mesaj) {
        if (this.telefon == null || this.telefon.trim().isEmpty()) {
            System.out.println("Nu se poate trimite SMS. " + nume + " nu are număr de telefon valid.");
            return false;
        }
        if (mesaj == null || mesaj.trim().isEmpty()) {
            System.out.println("Nu se poate trimite un SMS gol.");
            return false;
        }

        smsTrimise.add(mesaj);
        System.out.println("SMS trimis către " + telefon + ": " + mesaj);
        return true;
    }

    public List<String> getSmsTrimise() {
        return smsTrimise;
    }
}