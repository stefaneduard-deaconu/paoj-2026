package com.pao.laboratory07.exercise1;

public enum StareComanda {
    PLASATA, PROCESATA, EXPEDIATA, LIVRATA, ANULATA;

    public static boolean esteFinala(StareComanda stare) {
        return stare == LIVRATA || stare == ANULATA;
    }

    public static StareComanda tranzitieNext(StareComanda curenta) {
        switch (curenta) {
            case PLASATA: return PROCESATA;
            case PROCESATA: return EXPEDIATA;
            case EXPEDIATA: return LIVRATA;
            default: return curenta;
        }
    }
}