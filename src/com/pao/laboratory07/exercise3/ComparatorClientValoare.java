package com.pao.laboratory07.exercise3;

import java.util.Comparator;

public class ComparatorClientValoare implements Comparator<Comanda> {

    @Override
    public int compare(Comanda c1, Comanda c2) {
        int comparareNume = c1.getClient().compareTo(c2.getClient());

        if (comparareNume != 0) {
            return comparareNume;
        } else {
            return Double.compare(c2.getValoare(), c1.getValoare());
        }
    }
}