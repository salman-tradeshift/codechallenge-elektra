package com.tradeshift.elektra.johannes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageDAO messageDAO;

    @Autowired
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public MessageDTO createMessage(String name) {
        MessageDTO message = new MessageDTO("Hello: " + name);
        messageDAO.save(message);
        return message;
    }

    public Recent createRecent() {
        return messageDAO.retriveRecentN(10);
    }

}