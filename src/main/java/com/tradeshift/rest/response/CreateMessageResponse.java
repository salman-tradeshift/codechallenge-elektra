package com.tradeshift.rest.response;

import java.io.Serializable;

import com.tradeshift.dto.ContentDTO;

public final class CreateMessageResponse implements Serializable {
    private static final long serialVersionUID = 6822503619799771201L;
    private final ContentDTO message;

    public CreateMessageResponse(ContentDTO message) {
        this.message = message;
    }

    public ContentDTO getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RestResponse [message=" + message + "]";
    }

}
