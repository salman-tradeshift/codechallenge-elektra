package com.tradeshift.rest.response;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.google.common.base.Preconditions;
import com.tradeshift.dto.Message;

public class GetMessagesResponse implements Serializable {

    private static final long serialVersionUID = -4227947057262227741L;

    private int messageCount;
    private Timestamp lastMessage;
    private List<Message> messages;

    public GetMessagesResponse(List<Message> messages, Timestamp lastMessage) {

        Preconditions.checkNotNull(messages);
        this.messages = messages;
        this.lastMessage = lastMessage;
        this.messageCount = messages.size();

    }

    public int getMessageCount() {
        return messageCount;
    }

    public Timestamp getLastMessage() {
        return lastMessage;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "GetResponse [messageCount=" + messageCount + ", lastMessage=" + lastMessage
                + ", messages=" + messages + "]";
    }
}
