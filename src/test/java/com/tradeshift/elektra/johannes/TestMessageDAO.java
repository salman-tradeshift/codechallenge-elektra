package com.tradeshift.elektra.johannes;

import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;
import java.sql.Timestamp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestMessageDAO {

    String sql;
    String saveSql;
    MessageDTO saveMessage;

    List<Map<String, Object>> queryResult;
    Recent recent;
    int n;

    @Before
    public void setup() {
        n = 10;
        sql =  "SELECT message, created FROM messages ORDER BY created DESC LIMIT " + n;

        MessageDTO m1 = new MessageDTO("abc123");
        MessageDTO m2 = new MessageDTO("def456");

        Timestamp t1 = new Timestamp(0);
        Timestamp t2 = new Timestamp(1);

        queryResult = new ArrayList< Map<String, Object>>();

        Map<String, Object> content1 = new HashMap<String,Object>();
        content1.put("message", m1.toString());
        content1.put("created", t1);
        queryResult.add(content1);

        Map<String, Object> content2 = new HashMap<String,Object>();
        content2.put("message", m2.toString());
        content2.put("created", t2);
        queryResult.add(content2);

        ArrayList<MessageDTO> messages = new ArrayList<MessageDTO>(Arrays.asList(m1, m2));
        recent = new Recent(messages, t2);

        saveMessage = new MessageDTO("heurka");
        saveSql = "INSERT INTO messages (message) values ('" + saveMessage + "')";
    }


    @Test
    public void testMessageDAO() {

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        MessageDAO messageDAO = new MessageDAO(mockJdbcTemplate);

        when(mockJdbcTemplate.queryForList(sql)).thenReturn(queryResult);
        assertEquals(messageDAO.retriveRecentN(n), recent);

        when(mockJdbcTemplate.update(saveSql)).thenReturn(1);
        messageDAO.save(saveMessage);
        verify(mockJdbcTemplate).update(saveSql);
    }
}
