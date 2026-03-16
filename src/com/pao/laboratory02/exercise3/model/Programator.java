package com.pao.laboratory02.exercise3.model;

/**
 * TODO: Implementează Programator extends Angajat.
 * - Atribut: private String limbajPreferat
 * - Constructor: super(name, salariuBaza), this.limbajPreferat = limbajPreferat
 * - salariuTotal() = getSalariuBaza() * 1.5
 */
public class Programator extends Angajat {

    // TODO: private String limbajPreferat
    private String limbajPreferat;

    public Programator(String name, double salariuBaza, String limbajPreferat) {
        super(name, salariuBaza);
        // TODO: this.limbajPreferat = limbajPreferat
        this.limbajPreferat = limbajPreferat;
    }

    @Override
    public double salariuTotal() {
        return getSalariuBaza() * 1.5; // TODO: getSalariuBaza() * 1.5
    }
}
