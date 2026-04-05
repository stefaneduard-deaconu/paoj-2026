package com.pao.laboratory07.exercise3;

import java.util.Locale;

public final class ComandaAbonament extends Comanda {
    private int nrLuni;

    public ComandaAbonament(String id, String client, double valoare, int nrLuni) throws ComandaInvalidaException {
        super(id, client, valoare);
        if (nrLuni <= 0) throw new ComandaInvalidaException("Numarul de luni pentru abonament trebuie sa fie cel putin 1!");
        this.nrLuni = nrLuni;
    }

    @Override
    public void procesare() {
        System.out.println("Procesare ABONAMENT: Configurare plati recurente pentru " + id);
    }

    @Override
    public String tipComanda() {
        return "ABONAMENT";
    }

    @Override
    public boolean esteSpeciala() {
        return true;
    }

    @Override
    public String obtineDetalii() {
        return String.format(Locale.US, "ABONAMENT: %s %s, valoare: %.2f lei, luni: %d", id, client, valoare, nrLuni);
    }

    @Override
    public void afiseaza() {
        System.out.println(obtineDetalii());
    }
}