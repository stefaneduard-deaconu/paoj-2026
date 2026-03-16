package com.pao.laboratory02.exercise3.model;

/**
 * TODO: Implementează Manager extends Angajat.
 * - Atribut: private int nrSubordonati
 * - Constructor: super(name, salariuBaza), this.nrSubordonati = nrSubordonati
 * - salariuTotal() = getSalariuBaza() * 2 + nrSubordonati * 100
 */
public class Manager extends Angajat {

    // TODO: private int nrSubordonati
    private int nrSubordonati;

    public Manager(String name, double salariuBaza, int nrSubordonati) {
        super(name, salariuBaza);
        // TODO: this.nrSubordonati = nrSubordonati
        this.nrSubordonati = nrSubordonati;
    }

    @Override
    public double salariuTotal() {
        return getSalariuBaza() * 2 + nrSubordonati * 100; // TODO: getSalariuBaza() * 2 + nrSubordonati * 100
    }
}
