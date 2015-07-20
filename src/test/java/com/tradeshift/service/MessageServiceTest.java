package com.tradeshift.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.tradeshift.dto.Message;
import com.tradeshift.exception.ServiceException;
import com.tradeshift.service.impl.MessageServiceImpl;

public class MessageServiceTest {

    @Test
    public void testProcessContent() throws ServiceException {

        MessageServiceImpl messageService = new MessageServiceImpl();
        Message message = messageService.processContent("Salman");

        assertNotNull(message);
        assertEquals("Hello Salman", message.getContent());
    }

}
