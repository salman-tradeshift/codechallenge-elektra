package rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * Created by ksp on 08/06/15.
 */
@Component
public class MessageService {
    private MessageDAO messageDAO;

    @Autowired
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public ResponseMessage saveMessage(String content){
        Message message = new Message(content, null);

        messageDAO.saveMessage(message);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(message);

        return responseMessage;
    }

    public ResponseRecent getLatestMessages(){
        List<Message> messages = messageDAO.getLatestMessages();
        ResponseRecent response = new ResponseRecent();
        response.setMessages(messages);
        response.setMessageCount(messages.size());

        if(!messages.isEmpty()){
            response.setLastMessage(messages.get(0).getTimestamp());
        }

        return response;
    }

}



