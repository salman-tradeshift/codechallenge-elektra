package com.tradeshift.elektra.johannes;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;
import java.sql.Timestamp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.anyString;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageDAOTest {
    private static final int numMessages = 10;
    private static final MessageDTO saveMessage = new MessageDTO("heurka");

    @Test
    public void MessageDAO_communicates_correctly_with_JbcTemplate() {
        List<Map<String, Object>> queryResult;
        Recent recent;

        MessageDTO message1 = new MessageDTO("abc123");
        MessageDTO message2 = new MessageDTO("def456");

        Timestamp time1 = new Timestamp(0);
        Timestamp time2 = new Timestamp(1);

        queryResult = new ArrayList< Map<String, Object>>();

        Map<String, Object> content1 = new HashMap<String,Object>();
        content1.put("message", message1.toString());
        content1.put("created", time1);
        queryResult.add(content1);

        Map<String, Object> content2 = new HashMap<String,Object>();
        content2.put("message", message2.toString());
        content2.put("created", time2);
        queryResult.add(content2);

        ArrayList<MessageDTO> messages = new ArrayList<MessageDTO>(Arrays.asList(message1, message2));
        recent = new Recent(messages, time2);

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        MessageDAO messageDAO = new MessageDAO(mockJdbcTemplate);

        when(mockJdbcTemplate.queryForList( anyString() )).thenReturn(queryResult);
        assertEquals(messageDAO.retriveRecentN(numMessages), recent);

        when(mockJdbcTemplate.update( anyString() )).thenReturn(1);
        messageDAO.save(saveMessage);
        verify(mockJdbcTemplate).update( anyString() );
    }
}
