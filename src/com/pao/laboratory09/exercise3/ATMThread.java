package com.pao.laboratory09.exercise3;

import com.pao.laboratory09.exercise1.Tranzactie;
import com.pao.laboratory09.exercise1.TipTranzactie;

public class ATMThread extends Thread {
    private final int idAtm;
    private final CoadaTranzactii coada;

    public ATMThread(int idAtm, CoadaTranzactii coada) {
        this.idAtm = idAtm;
        this.coada = coada;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 4; i++) {
                int idTranzactie = idAtm * 100 + i;
                Tranzactie t = new Tranzactie(idTranzactie, 100.0 * i, "2024-05-20", "RO_ATM_" + idAtm, "RO_DEST", TipTranzactie.DEBIT);

                System.out.println("[ATM-" + idAtm + "] trimite: Tranzactie #" + idTranzactie + " " + (100.0 * i) + " RON");
                coada.adauga(t, "ATM-" + idAtm);

                Thread.sleep(50);
            }
        }

        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}