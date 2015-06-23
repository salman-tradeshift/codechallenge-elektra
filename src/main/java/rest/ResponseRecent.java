package rest;

import java.util.List;

/**
 * Created by ksp on 22/06/15.
 */
public class ResponseRecent {
    private int messageCount;
    private String lastMessage;
    private List<Message> messages;

    public void setMessageCount(int messageCount){
        this.messageCount = messageCount;
    }

    public void setLastMessage(String lastMessage){
        this.lastMessage = lastMessage;
    }

    public void setMessages(List<Message> messages){
        this.messages = messages;
    }

    public int getMessageCount(){
        return messageCount;
    }

    public String getLastMessage(){
        return lastMessage;
    }

    public List<Message> getMessages(){
        return messages;
    }
}
