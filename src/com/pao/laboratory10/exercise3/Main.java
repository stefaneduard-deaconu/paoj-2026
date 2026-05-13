package com.pao.laboratory10.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Tranzactie> txs = Arrays.asList(
                new Tranzactie(1, 1500.00, "2024-01-15", TipTranzactie.CREDIT, "RO01INGB"),
                new Tranzactie(2, 200.50,  "2024-01-20", TipTranzactie.DEBIT,  "RO01INGB"),
                new Tranzactie(3, 300.00,  "2024-01-22", TipTranzactie.DEBIT,  "RO02BT"),
                new Tranzactie(4, 4500.00, "2024-02-05", TipTranzactie.CREDIT, "RO03BCR"),
                new Tranzactie(5, 50.00,   "2024-02-14", TipTranzactie.DEBIT,  "RO01INGB"),
                new Tranzactie(6, 120.00,  "2024-02-28", TipTranzactie.DEBIT,  "RO04BRD"),
                new Tranzactie(7, 3000.00, "2024-03-01", TipTranzactie.CREDIT, "RO02BT"),
                new Tranzactie(8, 15.50,   "2024-03-10", TipTranzactie.DEBIT,  "RO02BT"),
                new Tranzactie(9, 800.00,  "2024-03-15", TipTranzactie.DEBIT,  "RO01INGB"),
                new Tranzactie(10, 2500.0, "2024-03-20", TipTranzactie.CREDIT, "RO03BCR")
        );

        System.out.println("=== DEMO STREAM API BANCAR ===\n");

        System.out.println("--- 1. Tranzactii de tip CREDIT ---");
        txs.stream()
                .filter(t -> t.getTip() == TipTranzactie.CREDIT)
                .forEach(System.out::println);

        System.out.println("\n--- 2. Suma Totala Procesata ---");
        double totalSuma = txs.stream().mapToDouble(Tranzactie::getSuma).sum();
        System.out.printf("Total procesat: %.2f RON\n", totalSuma);

        System.out.println("\n--- 3. Total sume per luna ---");
        Map<String, Double> sumaPeLuna = txs.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.summingDouble(Tranzactie::getSuma)
                ));
        sumaPeLuna.forEach((luna, suma) -> System.out.printf("%s: %.2f RON\n", luna, suma));

        System.out.println("\n--- 4. Top 3 tranzactii descrescator ---");
        txs.stream()
                .sorted(Comparator.comparingDouble(Tranzactie::getSuma).reversed())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\n--- 5. Conturi sursa unice ---");
        List<String> conturiUnice = txs.stream()
                .map(Tranzactie::getContSursa)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Conturi sursa unice: " + conturiUnice);

        System.out.println("\n--- 6. Suma medie ---");
        double medie = txs.stream().mapToDouble(Tranzactie::getSuma).average().orElse(0.0);
        System.out.printf("Suma medie: %.2f RON\n", medie);

        System.out.println("\n--- 7. Extrase de cont lunare ---");
        Map<String, DoubleSummaryStatistics> statsPeLuna = txs.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getData().substring(0, 7),
                        TreeMap::new,
                        Collectors.summarizingDouble(Tranzactie::getSuma)
                ));

        statsPeLuna.forEach((luna, stats) -> {
            System.out.printf("EXTRAS DE CONT - %s: %d tranzactii, total: %.2f RON\n",
                    luna, stats.getCount(), stats.getSum());
        });
    }
}