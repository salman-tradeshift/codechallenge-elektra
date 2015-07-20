package com.tradeshift.dto;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 1351394703566415854L;
    private String content;

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.content = "Hello " + name;
    }

    @Override
    public String toString() {
        return "MessageDTO [content=" + content + "]";
    }
}
