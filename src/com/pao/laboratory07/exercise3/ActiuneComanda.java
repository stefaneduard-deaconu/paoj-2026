package com.pao.laboratory07.exercise3;

public interface ActiuneComanda {
    void proceseaza();
    void afiseaza();
    String tipComanda();

    default boolean esteSpeciala() {
        return false;
    }

    String obtineDetalii();
}