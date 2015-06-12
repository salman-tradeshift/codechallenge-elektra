package com.tradeshift.messages;

/**
 * Created by ajo on 08/06/15.
 * <p/>
 *
 * @{ResponseMessage} class is a DTO object that will be serialized via JSON by Jackson library.
 */
public class ResponseMessage {
    private Message message;

    public ResponseMessage() {

    }

    public ResponseMessage(String name) {
        message = new Message(name);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
