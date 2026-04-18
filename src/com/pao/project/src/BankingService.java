package com.pao.project.src;

import java.util.*;
import java.util.stream.Collectors;

public class BankingService {
    private Map<String, Client> dictionarClienti = new HashMap<>();

    private TreeSet<Account> toateConturile = new TreeSet<>();

    public void inregistreazaClient(Client client) {
        dictionarClienti.put(client.getCnp(), client);
        System.out.println("Clientul " + client.getNume() + " a fost inregistrat.");
    }

    public Client gasesteClientDupaCnp(String cnp) {
        return dictionarClienti.get(cnp);
    }

    public void deschideCont(String cnp, Account cont) {
        Client client = dictionarClienti.get(cnp);
        if (client != null) {
            client.adaugaCont(cont);
            toateConturile.add(cont);
            System.out.println("Cont nou deschis pentru: " + client.getNume());
        } else {
            System.out.println("Eroare: Clientul cu CNP " + cnp + " nu exista!");
        }
    }

    public void depunereInCont(String iban, double suma) {
        Account cont = gasesteContDupaIban(iban);
        if (cont != null) {
            toateConturile.remove(cont);
            cont.depunere(suma);
            toateConturile.add(cont);
        }
    }

    public void transfer(String ibanSursa, String ibanDestinatar, double suma) {
        Account sursa = gasesteContDupaIban(ibanSursa);
        Account destinatar = gasesteContDupaIban(ibanDestinatar);

        if (sursa != null && destinatar != null && sursa.getSold() >= suma) {
            toateConturile.remove(sursa);
            toateConturile.remove(destinatar);

            sursa.retragere(suma);
            destinatar.depunere(suma);

            toateConturile.add(sursa);
            toateConturile.add(destinatar);
            System.out.println("Transfer realizat cu succes!");
        } else {
            System.out.println("Transfer esuat: Fonduri insuficiente sau conturi inexistente.");
        }
    }

    public void afiseazaRaportConturiSortate() {
        System.out.println("\n--- RAPORT BANCAR (Conturi sortate dupa sold) ---");
        for (Account acc : toateConturile) {
            System.out.println(acc.toString());
        }
    }

    private Account gasesteContDupaIban(String iban) {
        for (Account acc : toateConturile) {
            if (acc.getIban().equals(iban)) return acc;
        }
        return null;
    }

    public double calculeazaAvereTotalaClient(String cnp) {
        Client c = dictionarClienti.get(cnp);
        if (c == null) return 0;
        return c.getConturi().stream()
                .mapToDouble(Account::getSold)
                .sum();
    }

    public void efectueazaRetragere(String ibanR, double sumaR) {
        Account contGasit = null;
        for (Account acc : toateConturile) {
            if (acc.getIban().equals(ibanR)) {
                contGasit = acc;
                break;
            }
        }
        if (contGasit != null) {
            toateConturile.remove(contGasit);
            boolean succes = contGasit.retragere(sumaR);
            toateConturile.add(contGasit);
            if (succes) {
                System.out.println("Operațiune finalizată cu succes pentru IBAN: " + ibanR);
            }
        } else {
            System.out.println("Eroare: Contul cu IBAN-ul " + ibanR + " nu a fost găsit în sistem.");
        }
    }
}