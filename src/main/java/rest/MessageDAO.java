package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by ksp on 16/06/15.
 */

@Repository
public class MessageDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(BasicDataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public ResponseMessage saveMessage(String  content){
        String sql = "insert into message(content) values(?)";
        jdbcTemplate.update(sql, content);

        Message message = new Message(content, null);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(message);

        return responseMessage;
    }

    public ResponseRecent getLatestMessages(){
        String sql = "select * from message order by timestamp desc limit 10";

        List<Message> messages = this.jdbcTemplate.query(sql, new RowMapper<Message>() {
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                String content = rs.getString("content");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
                calendar.setTime(rs.getTimestamp("timestamp"));

                Message message = new Message(content, calendar);
                return message;
            }
        });

        ResponseRecent response = new ResponseRecent();
        response.setMessages(messages);

        if (!messages.isEmpty()){
            response.setLastMessage(messages.get(0).getTimestamp());
        }

        return response;
    }
}

