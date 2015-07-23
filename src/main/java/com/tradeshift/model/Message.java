package com.tradeshift.model;

import java.sql.Timestamp;

public final class Message {

    private int id;
    private String message;
    private Timestamp createdAt;

    public Message(String message, Timestamp createdAt) {
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Message [id=" + id + ", message=" + message + ", createdAt=" + createdAt + "]";
    }
}
