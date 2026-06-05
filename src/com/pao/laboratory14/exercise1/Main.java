package com.pao.laboratory14.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();

        List<Bilet> bilete = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            String eveniment = scanner.next();
            TipBilet tip = TipBilet.valueOf(scanner.next().toUpperCase());
            double pret = scanner.nextDouble();

            bilete.add(new Bilet(id, eveniment, tip, pret));
        }

        String comanda = scanner.next();

        RaportVanzari raport = bilete.stream().collect(BiletColector.create());

        if ("RAPORT_SIMPLU".equals(comanda)) {
            raport.afiseazaRaportSimplu();
        } else if ("RAPORT_COMPLET".equals(comanda)) {
            raport.afiseazaRaportComplet();
        }

        scanner.close();
    }
}
