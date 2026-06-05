package com.pao.laboratory14.exercise1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

public class BiletColector {

    public static Collector<Bilet, Map<TipBilet, double[]>, RaportVanzari> create() {
        return Collector.of(
                HashMap::new,

                (map, bilet) -> {
                    double[] date = map.computeIfAbsent(bilet.getTip(), k -> new double[2]);
                    date[0] += 1;
                    date[1] += bilet.getPret();
                },

                (map1, map2) -> {
                    map2.forEach((tip, date2) -> {
                        double[] date1 = map1.computeIfAbsent(tip, k -> new double[2]);
                        date1[0] += date2[0];
                        date1[1] += date2[1];
                    });
                    return map1;
                },

                map -> {
                    Map<TipBilet, Long> numarPerTip = new HashMap<>();
                    Map<TipBilet, Double> incasariPerTip = new HashMap<>();

                    double totalGlobal = 0;
                    long totalBilete = 0;

                    long maxCount = -1;
                    TipBilet tipCelMaiPopular = null;

                    for (Map.Entry<TipBilet, double[]> entry : map.entrySet()) {
                        TipBilet tip = entry.getKey();
                        long count = (long) entry.getValue()[0];
                        double incasari = entry.getValue()[1];

                        numarPerTip.put(tip, count);
                        incasariPerTip.put(tip, incasari);

                        totalGlobal += incasari;
                        totalBilete += count;

                        if (count > maxCount) {
                            maxCount = count;
                            tipCelMaiPopular = tip;
                        } else if (count == maxCount && tipCelMaiPopular != null) {
                            if (tip.name().compareTo(tipCelMaiPopular.name()) < 0) {
                                tipCelMaiPopular = tip;
                            }
                        }
                    }

                    double medieGlobala = totalBilete > 0 ? totalGlobal / totalBilete : 0.0;

                    return new RaportVanzari(numarPerTip, incasariPerTip, totalGlobal, medieGlobala, tipCelMaiPopular);
                }
        );
    }
}