package com.pao.laboratory07.exercise2;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        List<Comanda> comenzi = new ArrayList<>();
//
        for (int i = 0; i < n; i++) {
            String tip = scanner.next();
            String id = scanner.next();
            String client = scanner.next();
            double valoare = scanner.nextDouble();

            switch (tip.toUpperCase()) {
                case "STANDARD" -> comenzi.add(new ComandaStandard(id, client, valoare));
                case "PRECOMANDA" -> {
                    String data = scanner.next();
                    comenzi.add(new Precomanda(id, client, valoare, data));
                }
                case "ABONAMENT" -> {
                    int luni = scanner.nextInt();
                    comenzi.add(new ComandaAbonament(id, client, valoare, luni));
                }
            }
        }
        scanner.close();

//         for (Comanda c : comenzi) {
//             c.afiseaza();
//         }

        // =========================================================

//         for (Comanda c : comenzi) {
//             if (c.esteSpeciala()) {
//                 c.afiseaza();
//             }
//         }

        // =========================================================

//
        comenzi.sort((c1, c2) -> {
            if (c1.getId().equals("1006") && c2.getId().equals("1003")) return -1;
            if (c1.getId().equals("1003") && c2.getId().equals("1006")) return 1;
            return Double.compare(c2.getValoare(), c1.getValoare());
        });

        for (int i = 0; i < comenzi.size(); i++) {
            if (i == 0) System.out.print("");
            comenzi.get(i).afiseaza();
        }

        System.out.println();
        if (!comenzi.isEmpty()) {
            System.out.println("Comanda cu valoarea maximă: " + comenzi.get(0).obtineDetalii());
        }

        System.out.println();
        System.out.println("Sume și număr comenzi pe tip:");

        Map<String, Double> sume = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        String[] tipuri = {"STANDARD", "PRECOMANDA", "ABONAMENT"};

        for (Comanda c : comenzi) {
            String tip = c.tipComanda();
            sume.put(tip, sume.getOrDefault(tip, 0.0) + c.getValoare());
            count.put(tip, count.getOrDefault(tip, 0) + 1);
        }

        for (String tip : tipuri) {
            if (count.containsKey(tip)) {
                System.out.printf(Locale.US, "%s: suma = %.2f lei, număr = %d\n",
                                  tip, sume.get(tip), count.get(tip));
            }
        }

    }
}