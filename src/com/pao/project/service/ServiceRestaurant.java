package com.pao.project.service;

import com.pao.project.model.Firma;
import com.pao.project.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceRestaurant {

    private static ServiceRestaurant INSTANCE;
    private List<Firma> firme = new ArrayList<>();



    private ServiceRestaurant() {
    }


    public static ServiceRestaurant getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceRestaurant();
        }
        return INSTANCE;
    }






}
