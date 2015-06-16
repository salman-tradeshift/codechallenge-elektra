package com.tradeshift.elektra.johannes;

public class MessageDTO {

    private Content message;

    public MessageDTO(String message) {
        this.message = new Content(message);
    }

    public Content getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }  else if (other instanceof MessageDTO) {
            return this.message.equals( ((MessageDTO)other).message);
        }  else {
            return false;
        }
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

    @Override
    public String toString() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }  else if (other instanceof Content) {
            return this.content.equals( ((Content)other).content);
        }  else {
            return false;
        }
    }
}