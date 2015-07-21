package com.tradeshift.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.tradeshift.dto.Message;
import com.tradeshift.exception.ServiceException;

public class MessageServiceTest {

    @Test
    public void testCreateMessage() throws ServiceException {

        MessageService messageService = new MessageService();
        Message message = messageService.createMessage("Salman");

        assertNotNull(message);
        assertEquals("Hello Salman", message.getContent());
    }

}
