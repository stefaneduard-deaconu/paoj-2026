//package com.pao.project.service;
//
//import com.pao.project.model.Firma;
//import com.pao.project.model.Platforma;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//
//public class ServicePlatforma {
//
//
//    private static ServicePlatforma INSTANCE;
//    private List<Firma> firme = new ArrayList<>();
//
//    private ServicePlatforma(){
//    }
//
//    public static ServicePlatforma getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ServicePlatforma();
//        }
//        return INSTANCE;
//    }
//
//
//    public void adaugaFirma() {
//
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("CUI: ");
//        int cui = scanner.nextInt();
//
//        System.out.print("Numar Registru Comert: ");
//        int nrRegistru = scanner.nextInt();
//        scanner.nextLine();
//
//        System.out.print("Nume firma: ");
//        String numeFirma = scanner.nextLine();
//
//        System.out.print("Adresa sediu social: ");
//        String adresa = scanner.nextLine();
//
//        System.out.print("Numar telefon: ");
//        String telefon = scanner.nextLine();
//
//        System.out.print("Email: ");
//        String email = scanner.nextLine();
//
//
////
//        if (numeFirma.isEmpty() || adresa.isEmpty()) {
//            throw new IllegalArgumentException("Datele firmei nu sunt valide!");
//        }
//
//        Firma firma = new Firma(cui, nrRegistru, numeFirma, adresa, telefon, email);
//
//
//
//        firma.add(firma);
//
//        System.out.println("Firma adaugata cu succes: " + firma.getNume());
//    }
//
//}
