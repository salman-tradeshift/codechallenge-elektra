package com.tradeshift.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.tradeshift.dao.MessageDAO;
import com.tradeshift.dto.ContentDTO;
import com.tradeshift.exception.ServiceException;
import com.tradeshift.rest.response.CreateMessageResponse;

public class MessageServiceTest {

    private static final String TEST_NAME = "Hello SALMAN";
    MessageDAO messageDAOMock = Mockito.mock(MessageDAO.class);

    @Before
    public void setup() throws ServiceException {
        ContentDTO mockedDTO =
                new ContentDTO(TEST_NAME);
        Mockito.when(messageDAOMock.createMessage(TEST_NAME)).thenReturn(mockedDTO);
    }

    @Test
    public void testCreateMessage() throws ServiceException {

        MessageService messageService = new MessageService(messageDAOMock);
        CreateMessageResponse messageResponse = messageService.createMessage("SALMAN");

        assertNotNull(messageResponse);
        assertEquals(TEST_NAME, messageResponse.getMessage().getContent());
    }

}
