package com.pao.laboratory14.exercise1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

public class RaportVanzari {
    private final Map<TipBilet, Long> numarPerTip;
    private final Map<TipBilet, Double> incasariPerTip;
    private final double totalGlobal;
    private final double medieGlobala;
    private final TipBilet tipCelMaiPopular;

    public RaportVanzari(Map<TipBilet, Long> numarPerTip, Map<TipBilet, Double> incasariPerTip,
                         double totalGlobal, double medieGlobala, TipBilet tipCelMaiPopular) {
        this.numarPerTip = Collections.unmodifiableMap(numarPerTip);
        this.incasariPerTip = Collections.unmodifiableMap(incasariPerTip);
        this.totalGlobal = totalGlobal;
        this.medieGlobala = medieGlobala;
        this.tipCelMaiPopular = tipCelMaiPopular;
    }

    public void afiseazaRaportSimplu() {
        Arrays.stream(TipBilet.values())
                .filter(numarPerTip::containsKey)
                .forEach(tip -> {
                    long count = numarPerTip.get(tip);
                    double incasari = incasariPerTip.getOrDefault(tip, 0.0);
                    System.out.printf(Locale.US, "%s: count=%d incasari=%.2f RON%n", tip, count, incasari);
                });
    }

    public void afiseazaRaportComplet() {
        afiseazaRaportSimplu();
        System.out.println("---");
        System.out.printf(Locale.US, "Total: %.2f RON%n", totalGlobal);
        System.out.printf(Locale.US, "Medie: %.2f RON%n", medieGlobala);
        System.out.printf("Cel mai popular: %s%n", tipCelMaiPopular != null ? tipCelMaiPopular : "N/A");
    }
}