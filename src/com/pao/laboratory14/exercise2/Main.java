package com.pao.laboratory14.exercise2;

import com.pao.laboratory14.exercise1.TipBilet;
import com.pao.laboratory14.exercise2.model.Eveniment;
import com.pao.laboratory14.exercise2.repository.EvenimentRepository;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        EvenimentRepository repository = new EvenimentRepository();
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        while (scanner.hasNext()) {
            String comanda = scanner.next();

            switch (comanda) {
                case "ADD":
                    String nume = scanner.next();
                    String data = scanner.next();
                    int capacitate = scanner.nextInt();
                    TipBilet tip = TipBilet.valueOf(scanner.next().toUpperCase());

                    Eveniment ev = new Eveniment(nume, data, capacitate, tip);
                    repository.save(ev);
                    System.out.printf("Adaugat: [%d] %s%n", ev.getId(), ev.getNume());
                    break;

                case "LIST":
                    repository.findAll().forEach(System.out::println);
                    break;

                case "DELETE":
                    int idDeSters = scanner.nextInt();
                    int rowsAffected = repository.deleteImpl(idDeSters);
                    if (rowsAffected > 0) {
                        System.out.printf("Sters: %d%n", idDeSters);
                    } else {
                        System.out.printf("Nu exista: %d%n", idDeSters);
                    }
                    break;

                case "COUNT":
                    System.out.printf("Total: %d%n", repository.count());
                    break;

                default:
                    break;
            }
        }
        scanner.close();
    }
}