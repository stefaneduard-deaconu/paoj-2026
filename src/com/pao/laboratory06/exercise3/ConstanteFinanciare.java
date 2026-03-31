package com.pao.laboratory06.exercise3;

public enum ConstanteFinanciare {
    TVA(0.19),
    SALARIU_MINIM(4050.0),
    COTA_IMPOZIT(0.10);

    private final double valoare;

    ConstanteFinanciare(double valoare) {
        this.valoare = valoare;
    }

    public double getValoare() {
        return valoare;
    }
}