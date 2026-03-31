package com.pao.laboratory06.exercise3;

import com.pao.laboratory06.exercise3.models.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Vezi Readme.md pentru cerințe

        Inginer[] ingineri = {
                new Inginer("Popescu", "Ion", "0722222222", 5000),
                new Inginer("Ionescu", "Ana", "0733333333", 7500),
                new Inginer("Albescu", "Dan", "0744444444", 4000)
        };

        System.out.println("Sortare naturala");
        Arrays.sort(ingineri);
        for (Inginer i : ingineri) System.out.println(i);

        System.out.println("\nSortare comparator");
        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        for (Inginer i : ingineri) System.out.println(i);

        System.out.println("\nDemonstrare PlataOnline");
        PlataOnline plata = ingineri[0];
        plata.autentificare("user123", "pass");
        System.out.println("Sold disponibil: " + plata.consultareSold());

        System.out.println("\nDemonstrare PlataOnlineSMS");
        PlataOnlineSMS serviciuSms = new PersoanaJuridica("SRL", "admin", "0700000000");

        System.out.println("SMS: " + serviciuSms.trimiteSMS("SMS1"));

        PersoanaJuridica pjFaraTel = new PersoanaJuridica("Fara telefon SRL", "admin", null);
        System.out.println("SMS fara telefon: " + pjFaraTel.trimiteSMS("test"));

        System.out.println("\nConstante financiare");
        System.out.println("Valoare TVA: " + ConstanteFinanciare.TVA.getValoare());

        System.out.println("\nEdge Cases");

        try {
            ingineri[0].autentificare(null, "");
        } catch (IllegalArgumentException e) {
            System.out.println("Autentificare cu date null.");
        }

        try {
            PlataOnline plataInginer = new Inginer("X", "Y", "0711111111", 100);
            if (!(plataInginer instanceof PlataOnlineSMS)) {
                throw new UnsupportedOperationException("Acest utilizator nu are capabilitate SMS!");
            }
        } catch (UnsupportedOperationException e) {
            System.out.println("Eroare: " + e.getMessage());
        }
    }
}