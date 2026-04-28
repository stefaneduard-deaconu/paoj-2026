package com.pao.project.service;


import com.pao.project.model.Client;

import java.util.HashMap;
import java.util.Map;

public class ServiceClient {

    private static ServiceClient INSTANCE;
    private Map<Integer, Client> clienti = new HashMap<>();
    private ServiceClient() {
    }

    public static ServiceClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceClient();
        }
        return INSTANCE;
    }

    public void adaugaClient(Client c) {
        clienti.put(c.getId(), c);
    }

    public Client cautaClient(int id) {
        for (Client c : clienti.values()) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public void stergeClient(int id) {
        clienti.remove(id);
    }

    public Map<Integer, Client> getClienti() {
        return clienti;
    }
}