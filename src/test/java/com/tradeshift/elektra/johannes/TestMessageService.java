package com.tradeshift.elektra.johannes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestMessageService {

    Recent recent;
    MessageDTO message;
    String testString;

    @Before
    public void Setup() {
        Date date = new Date(1);
        MessageDTO m1 = new MessageDTO("foo");
        MessageDTO m2 = new MessageDTO("bar");
        ArrayList<MessageDTO> messages = new ArrayList<MessageDTO>(Arrays.asList(m1, m2));
        recent = new Recent(messages, date);

        testString = "abcdefg";
        message = new MessageDTO(("Hello: " + testString));
    }

    @Test
    public void testMessageService() {

        MessageDAO mockMessageDAO = mock(MessageDAO.class);
        MessageService messageService = new MessageService(mockMessageDAO);

        when(mockMessageDAO.save(message)).thenReturn(1);
        assertEquals(messageService.createMessage(testString), message);

        when(mockMessageDAO.retriveRecentN(10)).thenReturn(recent);
        assertEquals(messageService.createRecent(), recent);
    }
}
