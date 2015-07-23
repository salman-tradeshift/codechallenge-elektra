package com.tradeshift.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Lists;
import com.tradeshift.dto.ContentDTO;
import com.tradeshift.dto.MessageDTO;
import com.tradeshift.model.Message;

public class MessageDAOTest {

    private static final String TEST_NAME = "Hello SALMAN";
    private static final Timestamp TEST_TIMESTAMP = Timestamp.valueOf("2015-07-22 19:49:03.195");
    JdbcTemplate mockedJdbcTemplate = Mockito.mock(JdbcTemplate.class);

    @Before
    public void setup() {
        Message mockedMessage = new Message(TEST_NAME, TEST_TIMESTAMP);
        Mockito.when(mockedJdbcTemplate.update(Mockito.anyString(), Mockito.any())).thenReturn(1);
        Mockito.when(mockedJdbcTemplate.query(Mockito.anyString(),
                Mockito.any(MessageDAO.MessageMapper.class)))
                .thenReturn(Lists.newArrayList(mockedMessage));
    }

    @Test
    public void testMessageCreation() {
        MessageDAO messageDAO = new MessageDAO(mockedJdbcTemplate);
        ContentDTO contentDTO = messageDAO.createMessage(TEST_NAME);
        assertEquals(TEST_NAME, contentDTO.getContent());
    }

    @Test
    public void testGetRecentMessages() {
        MessageDAO messageDAO = new MessageDAO(mockedJdbcTemplate);

        MessageDTO messageDTO = messageDAO.getMessages();

        assertNotNull(messageDTO);
        assertEquals(TEST_TIMESTAMP, messageDTO.getLastMessage());
        assertEquals(TEST_NAME, messageDTO.getMessages().get(0).getMessage().getContent());
    }

}
