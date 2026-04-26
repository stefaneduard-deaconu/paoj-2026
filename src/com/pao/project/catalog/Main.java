package com.pao.project.catalog;

import com.pao.project.catalog.model.*;
import com.pao.project.catalog.service.*;
import com.pao.project.catalog.exception.*;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = StudentService.getInstance();
        CatalogService catalogService = CatalogService.getInstance();


        studentService.adaugaStudent(new Student("Popescu Ion", "ion@mail.com", new IdentificatorScolar("ST01")));
        studentService.adaugaStudent(new Student("Ionescu Ana", "ana@mail.com", new IdentificatorScolar("ST02")));


        Profesor prof = new Profesor("Matei Valer", "matei@scoala.ro", 5000, "Matematica");


        Materie mate = new Materie("Matematica", 5);


        try {
            catalogService.adaugaNota("ST01", new Nota(10, "20-04-2024"));
            catalogService.adaugaNota("ST01", new Nota(8, "22-04-2024"));
        } catch (NotaInvalidaException e) {
            System.out.println(e.getMessage());
        }


        try {
            Student gasit = studentService.cautaDupaId("ST01");
            System.out.println("Student gasit: " + gasit.getNume());
        } catch (StudentNegasitException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Studenti sortati: " + studentService.getStudentiSortati());


        System.out.println("Note ST01: " + catalogService.getNoteStudent("ST01").size() + " note inregistrate.");


        System.out.println("Media ST01: " + catalogService.calculeazaMedia("ST01"));


        prof.setEmail("matei.nou@scoala.ro");
        System.out.println("Email nou profesor: " + prof.getEmail());


        studentService.stergeStudent("ST02");
        System.out.println("Lista dupa stergere: " + studentService.getStudentiSortati());
    }
}