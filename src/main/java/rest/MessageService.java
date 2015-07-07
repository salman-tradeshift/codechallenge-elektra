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
        return messageDAO.saveMessage(content);
    }

    public ResponseRecent getLatestMessages(){
        return messageDAO.getLatestMessages();
    }

}



