package com.tradeshift.elektra.johannes;

import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Recent {
    int messageCount;
    Date lastMessage;
    List<MessageDTO> messages;

    public Recent(List<MessageDTO> messages, Date lastMessage) {
        this.messages = messages;
        this.messageCount = messages.size();
        this.lastMessage = lastMessage;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public String getLastMessage()  {
       ObjectMapper mapper = new ObjectMapper();
       mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

       String result;

        try {
            result = mapper.writeValueAsString(this.lastMessage);
        } catch (JsonProcessingException e) {
            result = new String("Jackson:JsonProcessingException");
        }

        return result;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }  else if (other instanceof Recent) {

            if (!lastMessage.equals( ((Recent)other).lastMessage )) { return false; }
            if (!messages.equals( ((Recent)other).messages)) { return false; }

            // double checking cant hurt
            if (messageCount != ((Recent) other).messageCount) { return false; }

            return true;

        }  else {
            return false;
        }
    }
}
