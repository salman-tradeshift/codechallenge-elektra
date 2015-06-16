package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ksp on 08/06/15.
 */
@Component
public class MessageService {
    @Autowired
    private Message message;

    public void setMessage(String content){
        message.setContent(content);
    }

    public Message getMessage(){
        return this.message;
    }
}

@Component
class Message{
    private String content;

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return content;
    }
}
