package com.pao.project.platforma_elearning;

import com.pao.project.platforma_elearning.service.MeniuService;

public class Main {
    public static void main(String[] args) {
        MeniuService meniu = MeniuService.getInstance();
        meniu.porneste();
    }
}