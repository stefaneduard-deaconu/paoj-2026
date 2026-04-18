package com.pao.project.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int contorId = 1000;

    private final int idTranzactie;
    private final double suma;
    private final LocalDateTime dataOra;
    private final String tip;
    private final String descriere;

    public Transaction(double suma, String tip, String descriere) {
        this.idTranzactie = contorId++;
        this.suma = suma;
        this.tip = tip;
        this.descriere = descriere;
        this.dataOra = LocalDateTime.now();
    }

    public int getIdTranzactie() { return idTranzactie; }
    public double getSuma() { return suma; }
    public String getTip() { return tip; }
    public String getDescriere() { return descriere; }

    public String getDataOraFormatata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dataOra.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("[%s] ID: %d | Tip: %s | Suma: %.2f | %s",
                getDataOraFormatata(), idTranzactie, tip, suma, descriere);
    }
}