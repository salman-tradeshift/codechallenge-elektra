package com.tradeshift.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tradeshift.dao.MessageDAO;
import com.tradeshift.dto.ContentDTO;
import com.tradeshift.dto.MessageDTO;
import com.tradeshift.exception.ServiceException;
import com.tradeshift.rest.response.CreateMessageResponse;
import com.tradeshift.rest.response.GetRecentMessagesResponse;

@Service
public class MessageService {
    private static final Logger logger = Logger.getLogger(MessageService.class);
    private final MessageDAO messageDAO;

    @Autowired
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    public CreateMessageResponse createMessage(String name) throws ServiceException {
        try {
            name = "Hello " + name;
            ContentDTO message = messageDAO.createMessage(name);
            return new CreateMessageResponse(message);
        } catch (DataAccessException dae) {
            throw new ServiceException("5001", "Unable to Access database");
        }
    }

    public GetRecentMessagesResponse getRecentMessages() throws ServiceException {
        try {
            MessageDTO messages = messageDAO.getMessages();
            return new GetRecentMessagesResponse(messages);
        } catch (DataAccessException dae) {
            throw new ServiceException("5001", "Unable to Access database");
        }
    }
}
