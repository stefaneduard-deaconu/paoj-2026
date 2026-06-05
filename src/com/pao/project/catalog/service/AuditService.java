package com.pao.project.catalog.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static AuditService instance;
    private final String FILE_PATH = "audit.csv";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private AuditService() {}

    public static AuditService getInstance() {
        if (instance == null) {
            instance = new AuditService();
        }
        return instance;
    }

    public synchronized void logAction(String actionName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().format(formatter);
            writer.println(actionName + "," + timestamp);
        } catch (IOException e) {
            System.err.println("Eroare la scrierea in fisierul de audit: " + e.getMessage());
        }
    }
}