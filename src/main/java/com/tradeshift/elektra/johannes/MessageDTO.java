package com.tradeshift.elektra.johannes;

public class MessageDTO {

    private Content message;

    public MessageDTO(String message) {
        this.message = new Content(message);
    }

    public Content getMessage() {
        return message;
    }
}

class Content {

    private String content;

    public Content(String content) {
        this.content = content;
}

    public String getContent() {
        return content;
    }
}