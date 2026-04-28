package com.pao.project.model;

import java.util.Comparator;

public class SoferDistantaRestaurant implements Comparator<Livrator> {
    private Locatie locatie;
    public SoferDistantaRestaurant(Locatie locatie){
        this.locatie = locatie;
    }

    double calculeaza(Pozitie p1, Pozitie p2){
        double stang = p1.getX() - p2.getX();
        double drept = p1.getY() - p2.getY();

        return Math.sqrt(stang * stang + drept * drept);
    }


    @Override
    public int compare(Livrator a, Livrator b) {
        double d1 = calculeaza(a.getPozitie(), locatie.getPozitie());
        double d2 = calculeaza(b.getPozitie(), locatie.getPozitie());
        return Double.compare(d1, d2);
    }

}
