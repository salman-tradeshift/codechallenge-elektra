package com.tradeshift.messages;

/**
 * Created by ajo on 08/06/15.
 * <p/>
 *
 * @{Message} class represents message content wrapped in the @{ResponseMessage} object.
 */

public class Message {
    private final String content;

    public Message(String name) {

        content = "Hello " + name;
    }

    public String getContent() {

        return content;
    }

}
