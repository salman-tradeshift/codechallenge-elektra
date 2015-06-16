package com.tradeshift.elektra.johannes;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class MessageDAO  {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MessageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(MessageDTO message) {
        String query = "INSERT INTO messages (message) values ('" + message + "')";
        return jdbcTemplate.update(query);
    }

    public Recent retriveRecentN(int n) {
        String sql = "SELECT message, created FROM messages ORDER BY created DESC LIMIT " + n;

        List<MessageDTO> messages = new ArrayList<MessageDTO>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Timestamp lastMessage = null;

        for (Map<String, Object> row : rows) {
            MessageDTO message = new MessageDTO( (String)(row.get("message")) );
            messages.add(message);

            Timestamp current = (Timestamp) (row.get("created"));
            if (lastMessage == null || lastMessage.before(current)) {
                lastMessage = current;
            }
        }

        return new Recent(messages, lastMessage);
    }


}


