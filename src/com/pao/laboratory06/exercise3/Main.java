package com.pao.laboratory06.exercise3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(" TESTARE CONSTANTE FINANCIARE ");
        System.out.println("TVA-ul curent este de: " + (ConstanteFinanciare.TVA.getValoare() * 100) + "%");
        System.out.println("Salariul minim pe economie: " + ConstanteFinanciare.SALARIU_MINIM.getValoare() + " lei\n");

        System.out.println(" CREARE ȘI SORTARE INGINERI ");
        Inginer[] ingineri = {
                new Inginer("Zaharia", "Ion", "0722111222", 6000),
                new Inginer("Avram", "Vasile", "0733111222", 9000),
                new Inginer("Popescu", "Maria", null, 7500)
        };

        System.out.println("Ordinea inițială:");
        Arrays.stream(ingineri).forEach(System.out::println);

        System.out.println("\nSortare naturală (alfabetic după nume):");
        Arrays.sort(ingineri);
        Arrays.stream(ingineri).forEach(System.out::println);

        System.out.println("\nSortare cu Comparator (descrescător după salariu):");
        Arrays.sort(ingineri, new ComparatorInginerSalariu());
        Arrays.stream(ingineri).forEach(System.out::println);


        System.out.println("\nACCES PRIN REFERINȚĂ INTERFAȚĂ (PlataOnline)");
        PlataOnline plataInginer = ingineri[0];
        plataInginer.autentificare("avram_v", "parola123");
        System.out.println("Sold inginer: " + plataInginer.consultareSold());
        plataInginer.efectuarePlata(500);
        System.out.println("Sold după plată (500): " + plataInginer.consultareSold());


        System.out.println("\n ACCES PersoanaJuridica (PlataOnlineSMS) ");
        PersoanaJuridica pj1 = new PersoanaJuridica("TechSRL", "Reprezentant", "0799888777");
        PersoanaJuridica pjFaraTelefon = new PersoanaJuridica("NoPhoneSRL", "Reprezentant", "");

        PlataOnlineSMS plataPj = pj1;
        plataPj.autentificare("tech_srl", "admin123");


        plataPj.trimiteSMS("Plata dumneavoastra a fost procesata cu succes.");
        plataPj.trimiteSMS("");

        pjFaraTelefon.trimiteSMS("Alerta securitate cont.");

        System.out.println("\nIstoric SMS-uri trimise pentru TechSRL:");
        pj1.getSmsTrimise().forEach(System.out::println);


        System.out.println("\n EDGE CASES & EXCEPȚII ");

        try {
            plataPj.autentificare(null, "1234");
        } catch (IllegalArgumentException e) {
            System.out.println("Excepție prinsă (Autentificare nulă): " + e.getMessage());
        }

        try {
            plataInginer.trimiteSMS("Test SMS");
        } catch (UnsupportedOperationException e) {
            System.out.println("Excepție prinsă (Fără capabilitate SMS): " + e.getMessage());
        }
    }
}