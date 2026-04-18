package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;

        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) { i--; continue; }

            String[] tokens = line.split(" ");
            String tip = tokens[0];

            if (tip.equals("STANDARD")) {
                comenzi.add(new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
            } else if (tip.equals("DISCOUNTED")) {
                comenzi.add(new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]));
            } else if (tip.equals("GIFT")) {
                comenzi.add(new ComandaGratuita(tokens[1], tokens[2]));
            }
        }

        comenzi.forEach(c -> System.out.println(c.descriere()));

        while (sc.hasNextLine()) {
            String cmdLine = sc.nextLine().trim();
            if (cmdLine.isEmpty()) continue;

            String[] cmdTokens = cmdLine.split(" ");
            String comanda = cmdTokens[0];

            switch (comanda) {
                case "STATS" -> {
                    System.out.println("\n--- STATS ---");
                    Map<Class<? extends Comanda>, Double> medii = comenzi.stream()
                            .collect(Collectors.groupingBy(Comanda::getClass, Collectors.averagingDouble(Comanda::pretFinal)));

                    if (medii.containsKey(ComandaStandard.class))
                        System.out.printf(Locale.US, "STANDARD: medie = %.2f lei\n", medii.get(ComandaStandard.class));
                    if (medii.containsKey(ComandaRedusa.class))
                        System.out.printf(Locale.US, "DISCOUNTED: medie = %.2f lei\n", medii.get(ComandaRedusa.class));
                    if (medii.containsKey(ComandaGratuita.class))
                        System.out.printf(Locale.US, "GIFT: medie = %.2f lei\n", medii.get(ComandaGratuita.class));
                }
                case "FILTER" -> {
                    double threshold = Double.parseDouble(cmdTokens[1]);
                    System.out.printf(Locale.US, "\n--- FILTER (>= %.2f) ---\n", threshold);
                    comenzi.stream()
                            .filter(c -> c.pretFinal() >= threshold)
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "SORT" -> {
                    System.out.println("\n--- SORT (by client, then by pret) ---");
                    comenzi.stream()
                            .sorted(Comparator.comparing(Comanda::getClient).thenComparingDouble(Comanda::pretFinal))
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "SPECIAL" -> {
                    System.out.println("\n--- SPECIAL (discount > 15%) ---");
                    comenzi.stream()
                            .filter(c -> c instanceof ComandaRedusa cr && cr.getDiscountProcent() > 15)
                            .forEach(c -> System.out.println(c.descriere()));
                }
                case "QUIT" -> {
                    return;
                }
                default -> System.out.println("Comandă necunoscută!");
            }
        }
    }
}