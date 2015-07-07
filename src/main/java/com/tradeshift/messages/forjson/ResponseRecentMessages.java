package com.tradeshift.messages.forjson;

import java.util.List;

/**
 * Created by ajo on 18/06/15.
 *
 * @{ResponseRecentMessages} is a DTO class that will be serialized via JSON as a response to a
 * GET request for getting recent messages.
 */
public class ResponseRecentMessages {
    private final int messagesCount;
    private final String lastMessage;
    private final List<ResponseMessage> messages;

    public ResponseRecentMessages(int messagesCount, String lastMessage, List<ResponseMessage>
            messages) {
        this.messagesCount = messagesCount;
        this.lastMessage = lastMessage;
        this.messages = messages;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public List<ResponseMessage> getMessages() {
        return messages;
    }
}
