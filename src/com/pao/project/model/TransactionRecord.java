package com.pao.project.model;

import java.time.LocalDateTime;

public final class TransactionRecord {

    private final String idComanda;
    private final int clientId;
    private final int restaurantId;
    private final double total;
    private final LocalDateTime timestamp;

    public TransactionRecord(String idComanda, int clientId, int restaurantId, double total, LocalDateTime timestamp) {
        this.idComanda = idComanda;
        this.clientId = clientId;
        this.restaurantId = restaurantId;
        this.total = total;
        this.timestamp = timestamp;
    }

    public String getIdComanda() {
        return this.idComanda;
    }

    public int getClientId() {
        return this.clientId;
    }

    public int getRestaurantId() {
        return this.restaurantId;
    }

    public double getTotal() {
        return this.total;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "idComanda='" + idComanda + '\'' +
                ", clientId=" + clientId +
                ", restaurantId=" + restaurantId +
                ", total=" + total +
                ", timestamp=" + timestamp +
                '}';
    }
}