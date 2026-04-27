package com.pao.project.model;

import java.util.Random;

public class Pozitie {

    private double x;
    private double y;

    public Pozitie(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Pozitie(){
        Random rand = new Random();
        this.x = rand.nextInt(20001) - 10000;
        this.y = rand.nextInt(20001) - 10000;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public void updatePozitie(){
        Random rand = new Random();
        this.x = this.x + rand.nextInt(201) - 100;
        this.y = this.y + rand.nextInt(201) - 100;
    }


}