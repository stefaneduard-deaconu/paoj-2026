package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt())
            return;

        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] tokens = line.split(" ");
            String tip = tokens[0];

            if (tip.equals("STANDARD")) {
                comenzi.add(new ComandaStandard(tokens[1], Double.parseDouble(tokens[2]), tokens[3]));
            }
            else if (tip.equals("DISCOUNTED")) {
                comenzi.add(new ComandaRedusa(tokens[1], Double.parseDouble(tokens[2]), Integer.parseInt(tokens[3]), tokens[4]));
            }
            else if (tip.equals("GIFT")) {
                comenzi.add(new ComandaGratuita(tokens[1], tokens[2]));
            }
        }

        comenzi.forEach(c -> System.out.println(c.descriere()));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty())
                continue;

            String[] tokens = line.split(" ");
            String cmd = tokens[0];

            switch (cmd) {
                case "STATS" -> {
                    System.out.println("\nSTATS");
                    Map<String, Double> medii = comenzi.stream().collect(Collectors.groupingBy(c -> {
                                    if (c instanceof ComandaStandard)
                                        return "STANDARD";
                                    if (c instanceof ComandaRedusa)
                                        return "DISCOUNTED";
                                    return "GIFT";}, TreeMap::new, Collectors.averagingDouble(Comanda::pretFinal)));
                    medii.forEach((tip, medie) -> System.out.printf("%s: medie = %.2f lei\n", tip, medie));
                }
                case "FILTER" -> {
                    double threshold = Double.parseDouble(tokens[1]);
                    System.out.printf("\nFILTER (>= %.2f)\n", threshold);
                    comenzi.stream().filter(c -> c.pretFinal() >= threshold).forEach(c -> System.out.println(c.descriere()));
                }
                case "SORT" -> {
                    System.out.println("\nSORT (dupa client, dupa pret)");
                    comenzi.stream().sorted(Comparator.comparing(Comanda::getClient).thenComparing(Comanda::pretFinal)).forEach(c -> System.out.println(c.descriere()));
                }
                case "SPECIAL" -> {
                    System.out.println("\nSPECIAL (discount > 15%)");
                    comenzi.stream().filter(c -> c instanceof ComandaRedusa cr && cr.getDiscountProcent() > 15).forEach(c -> System.out.println(c.descriere()));
                }
                case "QUIT" -> { return; }
            }
        }
    }
}