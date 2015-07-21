package com.tradeshift.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tradeshift.dto.Message;
import com.tradeshift.exception.ServiceException;

@Component
public class MessageService {

    private static final Logger logger = Logger.getLogger(MessageService.class);

    public Message createMessage(String name) throws ServiceException {

        try {
            Message message = new Message(name);
            message.createMessageContent();
            return message;
        } catch (Exception ex) {
            logger.error("Error setting name", ex);
            throw new ServiceException("5001", "Invalid name field");
        }
    }
}
