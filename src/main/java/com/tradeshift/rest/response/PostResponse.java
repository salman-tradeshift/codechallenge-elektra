package com.tradeshift.rest.response;

import java.io.Serializable;

import com.tradeshift.dto.Message;

public class PostResponse implements Serializable {
    private static final long serialVersionUID = 6822503619799771201L;
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RestResponse [message=" + message + "]";
    }

}
