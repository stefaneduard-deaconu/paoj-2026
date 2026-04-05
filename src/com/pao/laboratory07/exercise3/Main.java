package com.pao.laboratory07.exercise3;

import java.time.LocalDate;
import java.util.*;
        import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Vezi Readme.md pentru cerințe

        List<Comanda> comenzi = new ArrayList<>();

        try {
            comenzi.add(new ComandaStandard("1", "Popescu", 250.0));
            comenzi.add(new Precomanda("2", "Ionescu", 400.0, LocalDate.of(2026, 5, 10)));
            comenzi.add(new Precomanda("3", "Enache", 600.0, LocalDate.now().minusDays(5)));
            comenzi.add(new ComandaAbonament("4", "Georgescu", 120.0, 6));
            comenzi.add(new ComandaStandard("5", "Enache", 150.0));

            //eroare
            comenzi.add(new ComandaStandard("eroare", "eroare", -1.0));
        } catch (ComandaInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();
        for(Comanda c: comenzi)
            if(c.esteSpeciala())
                System.out.println("[SPECIALA] "+c.obtineDetalii());
            else
                System.out.println("[STANDARD] "+c.obtineDetalii());

        System.out.println();
        Map<String, Double> sumePeTip = new HashMap<>();
        Map<String, Integer> numarPeTip = new HashMap<>();

        for (Comanda c : comenzi) {
            String tip = c.tipComanda();
            sumePeTip.put(tip, sumePeTip.getOrDefault(tip, 0.0) + c.getValoare());
            numarPeTip.put(tip, numarPeTip.getOrDefault(tip, 0) + 1);
        }

        for (String tip : sumePeTip.keySet()) {
            double suma = sumePeTip.get(tip);
            int numar = numarPeTip.get(tip);
            double media = suma / numar;

            System.out.printf(Locale.US, "Tip: %s -> Media: %.2f lei\n", tip, media);
        }

        System.out.println();
        double mediaTotala = comenzi.stream().mapToDouble(Comanda::getValoare).average().orElse(0);
        System.out.printf(Locale.US, "Media generala: %.2f lei\n", mediaTotala);
        comenzi.stream()
                .filter(c -> c.getValoare() > mediaTotala)
                .forEach(Comanda::afiseaza);


        System.out.println();
        List<Comanda> comenziSortate = new ArrayList<>(comenzi);

        comenziSortate.sort(Comanda.DUPA_CLIENT_SI_VALOARE);

        for (Comanda c : comenziSortate) {
            c.afiseaza();
        }


        System.out.println();
        comenzi.stream()
                .filter(c -> c instanceof Precomanda)
                .map(c -> (Precomanda) c)
                .filter(p -> p.getDataLivrare().isBefore(LocalDate.now()))
                .forEach(p -> System.out.println("Precomanda " + p.getId() + " pt clientul " +
                        p.getClient() + " este intarziata! Data stabilita: " + p.getDataLivrare()));



        boolean gasitPestePrag = false;
        for (Comanda c : comenzi) {
            if (c.getValoare() > 300.0) {
                c.afiseaza();
                gasitPestePrag = true;
            }
        }
        if (!gasitPestePrag) {
            System.out.println("Nu s-au gasit comenzi peste acest prag.");
        }
    }
}