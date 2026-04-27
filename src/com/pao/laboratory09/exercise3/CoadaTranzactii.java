package com.pao.laboratory09.exercise3;

import com.pao.laboratory09.exercise1.Tranzactie;
import java.util.LinkedList;
import java.util.Queue;

public class CoadaTranzactii {
    private final Queue<Tranzactie> banda = new LinkedList<>();
    private final int capacitateMaxime = 5;

    public synchronized void adauga(Tranzactie t, String atmName) throws InterruptedException {
        while (banda.size() == capacitateMaxime) {
            System.out.println("[" + atmName + "] astept loc...");
            wait();
        }

        banda.add(t);
        notifyAll();
    }

    public synchronized Tranzactie extrage() throws InterruptedException {
        while (banda.isEmpty()) {
            wait();
        }

        Tranzactie t = banda.poll();
        notifyAll();
        return t;
    }

    public synchronized boolean esteGoala() {
        return banda.isEmpty();
    }
}