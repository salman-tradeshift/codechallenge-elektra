package com.tradeshift.dto;

import java.sql.Timestamp;
import java.util.List;

import com.tradeshift.rest.response.CreateMessageResponse;

public final class MessageDTO {

    private final List<CreateMessageResponse> messages;
    private final Timestamp lastMessage;

    public MessageDTO(List<CreateMessageResponse> messages, Timestamp createdAt) {
        this.messages = messages;
        this.lastMessage = createdAt;
    }

    public List<CreateMessageResponse> getMessages() {
        return messages;
    }

    public Timestamp getLastMessage() {
        return lastMessage;
    }

    @Override
    public String toString() {
        return "MessageDTO [messages=" + messages + ", lastMessage=" + lastMessage + "]";
    }
}
