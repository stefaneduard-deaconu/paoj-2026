package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.TipTranzactie;
import com.pao.laboratory10.exercise1.Tranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextLine()) return;

        int n = Integer.parseInt(scanner.nextLine().trim());
        List<Tranzactie> lista = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().trim().split("\\s+");
            int id = Integer.parseInt(tokens[0]);
            double suma = Double.parseDouble(tokens[1]);
            String data = tokens[2];
            TipTranzactie tip = TipTranzactie.valueOf(tokens[3].toUpperCase());

            lista.add(new Tranzactie(id, suma, data, tip));
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split("\\s+");
            String comanda = tokens[0].toUpperCase();

            try {
                switch (comanda) {
                    case "UNIQUE_IDS": {
                        Set<Integer> uniqueIds = new LinkedHashSet<>();
                        for (Tranzactie t : lista) {
                            uniqueIds.add(t.getId());
                        }
                        System.out.printf("IDs unice (%d): %s\n", uniqueIds.size(), uniqueIds.toString());
                        break;
                    }

                    case "MONTHLY_REPORT": {

                        Map<String, double[]> report = new TreeMap<>();

                        for (Tranzactie t : lista) {
                            String month = t.getData().substring(0, 7);
                            report.putIfAbsent(month, new double[]{0.0, 0.0});

                            if (t.getTip() == TipTranzactie.CREDIT) {
                                report.get(month)[0] += t.getSuma();
                            } else {
                                report.get(month)[1] += t.getSuma();
                            }
                        }

                        for (Map.Entry<String, double[]> entry : report.entrySet()) {
                            System.out.printf("%s: CREDIT %.2f RON, DEBIT %.2f RON\n",
                                    entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
                        }
                        break;
                    }

                    case "TOP": {
                        int k = Integer.parseInt(tokens[1]);
                        System.out.println("Top " + k + ":");

                        List<Tranzactie> copy = new ArrayList<>(lista);
                        copy.sort((t1, t2) -> Double.compare(t2.getSuma(), t1.getSuma()));

                        int limit = Math.min(k, copy.size());
                        for (int i = 0; i < limit; i++) {
                            System.out.println(copy.get(i));
                        }
                        break;
                    }

                    case "SORT_ASC": {
                        lista.sort(Comparator.comparingDouble(Tranzactie::getSuma));
                        for (Tranzactie t : lista) System.out.println(t);
                        break;
                    }

                    case "SORT_DESC": {
                        lista.sort((t1, t2) -> Double.compare(t2.getSuma(), t1.getSuma()));
                        for (Tranzactie t : lista) System.out.println(t);
                        break;
                    }

                    case "REVERSE": {
                        Collections.reverse(lista);
                        for (Tranzactie t : lista) System.out.println(t);
                        break;
                    }

                    case "MIN_MAX": {
                        if (!lista.isEmpty()) {
                            Tranzactie min = Collections.min(lista, Comparator.comparingDouble(Tranzactie::getSuma));
                            Tranzactie max = Collections.max(lista, Comparator.comparingDouble(Tranzactie::getSuma));
                            System.out.println("MIN: " + min);
                            System.out.println("MAX: " + max);
                        }
                        break;
                    }

                    case "CME_DEMO": {
                        try {
                            for (Tranzactie t : lista) {
                                lista.remove(t);
                            }
                        } catch (ConcurrentModificationException e) {
                            System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                        }
                        break;
                    }

                    default:
                        break;
                }
            } catch (Exception _) {

            }
        }
        scanner.close();
    }
}