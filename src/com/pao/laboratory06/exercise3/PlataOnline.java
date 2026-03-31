package com.pao.laboratory06.exercise3;

public interface PlataOnline {
    void autentificare(String user, String parola);
    double consultareSold();
    boolean efectuarePlata(double suma);

      default boolean trimiteSMS(String mesaj) {
        throw new UnsupportedOperationException("Eroare: Această entitate nu are capabilitate SMS!");
    }
}