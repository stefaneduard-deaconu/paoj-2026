package com.pao.laboratory06.exercise3.models;

import java.util.Comparator;

//nu era cerut in cerinta sa fie facut asa comparatorul, dar asa m am obisnuit(nici nu era specificat cum sa fie)
public class ComparatorInginerSalariu implements Comparator<Inginer> {
    @Override
    public int compare(Inginer i1, Inginer i2) {
        return Double.compare(i2.salariu, i1.salariu);
    }
}