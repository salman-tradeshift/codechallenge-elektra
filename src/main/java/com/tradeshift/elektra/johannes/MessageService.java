package com.tradeshift.elektra.johannes;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public MessageDTO createMessage(String name) {
        MessageDTO message = new MessageDTO("Hello: " + name);
        return message;
    }
}