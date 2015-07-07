import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import rest.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.*;


/**
 * Created by ksp on 22/06/15.
 */
public class TestMessageService {


    @Test
    public void testResentMessagesEmpty(){
        List<Message> messages = new ArrayList<Message>();
        MessageService messageService = getMessageService(getJdbcTemplateMockupRecent(messages));

        ResponseRecent responseRecent = messageService.getLatestMessages();
        assertEquals(0, responseRecent.getMessageCount());
        assertEquals(0, responseRecent.getMessages().size());
        assertNull(responseRecent.getLastMessage());
    }

    @Test
    public void testResentMessagesOne(){
        List<Message> messages = new ArrayList<Message>();
        Calendar timestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        messages.add(new Message("hello", timestamp));
        MessageService messageService = getMessageService(getJdbcTemplateMockupRecent(messages));
        ResponseRecent responseRecent = messageService.getLatestMessages();
        assertEquals(1, responseRecent.getMessageCount());
        assertEquals(1, responseRecent.getMessages().size());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        sdf.setCalendar(timestamp);

        assertEquals(sdf.format(timestamp.getTime()), responseRecent.getLastMessage());
    }

    @Test
    public void testResentMessagesMultiple(){
        List<Message> messages = new ArrayList<Message>();
        Calendar timestamp = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        messages.add(new Message("hello1", timestamp));
        messages.add(new Message("hello2", timestamp));

        MessageService messageService = getMessageService(getJdbcTemplateMockupRecent(messages));
        ResponseRecent responseRecent = messageService.getLatestMessages();
        assertEquals(2, responseRecent.getMessageCount());
        assertEquals(2, responseRecent.getMessages().size());
        assertEquals("hello1", responseRecent.getMessages().get(0).getContent());
        assertEquals("hello2", responseRecent.getMessages().get(1).getContent());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        sdf.setCalendar(timestamp);

        assertEquals(sdf.format(timestamp.getTime()), responseRecent.getLastMessage());
    }


    @Test
    public void testSaveMessage(){
        MessageService messageService = getMessageService(getJdbcTemplateMockupSave());

        ResponseMessage responseMessage = messageService.saveMessage("hello");
        assertEquals("hello", responseMessage.getMessage().getContent());
        assertNull(responseMessage.getMessage().getTimestamp());
    }

    @Test
    public void testSaveMessageNull(){
        MessageService messageService = getMessageService(getJdbcTemplateMockupSave());

        ResponseMessage responseMessage = messageService.saveMessage(null);
        assertNull(responseMessage.getMessage().getContent());
    }

    public JdbcTemplate getJdbcTemplateMockupRecent(List<Message> messages){
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);

        String sql = anyString();
        RowMapper<Message> rm = anyObject();
        Mockito.when(jdbcTemplate.query(sql, rm)).thenReturn(messages);

        return jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplateMockupSave(){
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        return jdbcTemplate;
    }

    public MessageService getMessageService(JdbcTemplate jdbcTemplate){
        MessageDAO messageDAO = new MessageDAO();
        messageDAO.setJdbcTemplate(jdbcTemplate);
        MessageService messageService = new MessageService(messageDAO);

        return messageService;
    }
}
