package com.tradeshift.dto;

public final class ContentDTO {
    private final String content;

    public ContentDTO(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "MessageDTO [content=" + content + "]";
    }
}
