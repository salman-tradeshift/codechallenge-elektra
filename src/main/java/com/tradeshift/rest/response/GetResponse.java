package com.tradeshift.rest.response;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.tradeshift.dto.Message;

public class GetResponse implements Serializable {

    private static final long serialVersionUID = -4227947057262227741L;

    private int messageCount;
    private Timestamp lastMessage;
    private List<Message> messages;

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public Timestamp getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Timestamp lastMessage) {
        this.lastMessage = lastMessage;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "GetResponse [messageCount=" + messageCount + ", lastMessage=" + lastMessage
                + ", messages=" + messages + "]";
    }
}
