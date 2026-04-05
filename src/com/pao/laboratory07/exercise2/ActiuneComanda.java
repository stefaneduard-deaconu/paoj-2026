package com.pao.laboratory07.exercise2;

public interface ActiuneComanda {
    void proceseaza();
    void afiseaza();
    String tipComanda();

    default boolean esteSpeciala() {
        return false;
    }

    String obtineDetalii();
}