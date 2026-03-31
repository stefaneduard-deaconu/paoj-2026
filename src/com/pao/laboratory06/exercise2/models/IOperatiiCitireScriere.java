package com.pao.laboratory06.exercise2.models;

import java.util.Scanner;

public interface IOperatiiCitireScriere {
    void citeste(Scanner in);
    void afiseaza();
    String tipContract();
    default boolean areBonus() { return false; }
}