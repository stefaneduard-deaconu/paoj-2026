package com.pao.laboratory06.exercise3;

import java.util.Comparator;

public class ComparatorInginerSalariu implements Comparator<Inginer> {
    @Override
    public int compare(Inginer i1, Inginer i2) {
        return Double.compare(i2.getSalariu(), i1.getSalariu());
    }
}