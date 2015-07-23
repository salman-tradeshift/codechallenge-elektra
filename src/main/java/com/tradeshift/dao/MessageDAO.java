package com.tradeshift.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.tradeshift.dto.ContentDTO;
import com.tradeshift.dto.MessageDTO;
import com.tradeshift.model.Message;
import com.tradeshift.rest.response.CreateMessageResponse;

@Repository
public class MessageDAO {
    private static final Logger logger = Logger.getLogger(MessageDAO.class);
    private final JdbcTemplate jdbcTemplate;
    private final Function<Message, CreateMessageResponse> convertMessagesToCreateMessageResponsePredicate =
            new Function<Message, CreateMessageResponse>() {
                public CreateMessageResponse apply(Message message) {
                    return new CreateMessageResponse(new ContentDTO(message.getMessage()));
                }
            };

    @Autowired
    public MessageDAO(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ContentDTO createMessage(final String messageContent) throws DataAccessException {

        Message message = new Message(messageContent, new Timestamp(new Date().getTime()));
        final String sql = "INSERT INTO message (message,created_at) VALUES (?,?)";
        try {
            this.jdbcTemplate.update(sql,
                    new Object[] {message.getMessage(), message.getCreatedAt()});
            return new ContentDTO(messageContent);
        } catch (DataAccessException dae) {
            logger.error("Unable to create a Message in DB", dae);
            throw dae;
        }
    }

    public MessageDTO getMessages() throws DataAccessException {
        final String sql =
                "SELECT message, created_at FROM message ORDER BY created_at DESC LIMIT 10";
        try {
            List<Message> messageList = this.jdbcTemplate.query(sql, new MessageMapper());
            List<CreateMessageResponse> messages =
                    convertMessagesToCreateMessageResponse(messageList);
            Timestamp lastMessage = new Timestamp(0);
            Message message = Iterables.getFirst(messageList, null);
            if (message != null)
                lastMessage = message.getCreatedAt();
            return new MessageDTO(messages, lastMessage);
        } catch (DataAccessException dae) {
            logger.error("Unable to create a Message in DB", dae);
            throw dae;
        }
    }

    private List<CreateMessageResponse> convertMessagesToCreateMessageResponse(
            List<Message> messageList) {
        return Lists.transform(messageList, convertMessagesToCreateMessageResponsePredicate);
    }

    protected static final class MessageMapper implements RowMapper<Message> {
        public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
            Message message = new Message(rs.getString("message"),
                    Timestamp.valueOf(rs.getString("created_at")));
            return message;
        }
    }
}
