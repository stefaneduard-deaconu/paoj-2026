package com.pao.laboratory09.exercise3;

import com.pao.laboratory09.exercise1.Tranzactie;

public class ProcessorThread implements Runnable {
    private final CoadaTranzactii coada;
    public volatile boolean activ = true;
    private int totalProcesate = 0;

    public ProcessorThread(CoadaTranzactii coada) {
        this.coada = coada;
    }

    @Override
    public void run() {
        try {
            while (activ || !coada.esteGoala()) {
                if (coada.esteGoala() && !activ) break;

                Tranzactie t = coada.extrage();
                if (t != null) {
                    Thread.sleep(80);
                    System.out.println("[Processor] Factura #" + t.getId() + " - " + t.getData());
                    totalProcesate++;
                }
            }
        }

        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getTotalProcesate() {
        return totalProcesate;
    }
}