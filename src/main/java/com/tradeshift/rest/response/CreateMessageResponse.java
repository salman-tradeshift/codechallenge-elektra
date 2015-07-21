package com.tradeshift.rest.response;

import java.io.Serializable;

import com.tradeshift.dto.Message;

public final class CreateMessageResponse implements Serializable {
    private static final long serialVersionUID = 6822503619799771201L;
    private Message message;

    public CreateMessageResponse(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RestResponse [message=" + message + "]";
    }

}
