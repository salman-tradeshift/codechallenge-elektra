package com.tradeshift.dto;

import java.io.Serializable;

import com.google.common.base.Strings;

public final class Message implements Serializable {

    private static final long serialVersionUID = 1351394703566415854L;
    private String content = "Hello ";
    private String name;

    public Message(String name) {
        this.name = name;
    }

    public void createMessageContent() {
        if (!Strings.isNullOrEmpty(name))
            content = content.concat(name);
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "MessageDTO [content=" + content + "]";
    }
}
