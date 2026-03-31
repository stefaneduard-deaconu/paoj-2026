package com.pao.laboratory06.exercise3.models;

public interface PlataOnline {
    void autentificare(String user, String parola);
    double consultareSold();
    boolean efectuarePlata(double suma);
}