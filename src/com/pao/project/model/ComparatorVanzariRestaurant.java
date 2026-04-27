package com.pao.project.model;

import java.util.Comparator;

public class ComparatorVanzariRestaurant implements Comparator<Restaurant> {
    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        return r2.getNrVanzari() - r1.getNrVanzari();
    }
}
