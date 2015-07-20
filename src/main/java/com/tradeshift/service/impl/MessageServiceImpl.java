package com.tradeshift.service.impl;

import org.apache.log4j.Logger;

import com.tradeshift.dto.Message;
import com.tradeshift.exception.ServiceException;
import com.tradeshift.service.MessageService;

public class MessageServiceImpl implements MessageService {

    private static final Logger logger = Logger.getLogger(MessageService.class);

    @Override
    public Message processContent(String name) throws ServiceException {

        try {
            Message message = new Message();
            message.setName(name);

            return message;
        } catch (Exception ex) {
            logger.error("Exception in service layer", ex);
            ServiceException se = new ServiceException();
            se.setCode("5001");
            se.setMessage("Invalid name field");
            throw se;
        }
    }

}
