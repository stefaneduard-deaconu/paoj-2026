package com.pao.laboratory07.exercise3;

import java.time.LocalDate;
import java.util.Locale;

public final class Precomanda extends Comanda {
    private LocalDate dataLivrare;

    public Precomanda(String id, String client, double valoare, LocalDate dataLivrare) throws ComandaInvalidaException {
        super(id, client, valoare);
        if (dataLivrare == null) throw new ComandaInvalidaException("Data de livrare este obligatorie!");
        this.dataLivrare = dataLivrare;
    }

    public LocalDate getDataLivrare() {
        return dataLivrare;
    }

    @Override
    public void procesare() {
        System.out.println("Procesare PRECOMANDA: S-a rezervat stocul pentru " + id);
    }

    @Override
    public String tipComanda() {
        return "PRECOMANDA";
    }

    @Override
    public boolean esteSpeciala() {
        return true;
    }

    @Override
    public String obtineDetalii() {
        return String.format(Locale.US, "PRECOMANDA: %s %s, valoare: %.2f lei, livrare: %s", id, client, valoare, dataLivrare);
    }

    @Override
    public void afiseaza() {
        System.out.println(obtineDetalii());
    }
}