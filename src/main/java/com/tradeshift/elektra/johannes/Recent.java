package com.tradeshift.elektra.johannes;

import java.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

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
       mapper.setDateFormat(new ISO8601DateFormat());

       String result;

        try {
            result = mapper.writeValueAsString(this.lastMessage);
        } catch (JsonProcessingException e) {
            result = new String("Jackson:JsonProcessingException");
        }

        result = result.replaceAll("\\\"","");

        return result;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object other) {

        Recent recentOther = (Recent) other;

        if (this == recentOther) {
            return true;
        }  else if (recentOther instanceof Recent) {

            if (!lastMessage.equals(recentOther.lastMessage)) { return false; }
            if (!messages.equals(recentOther.messages)) { return false; }
            
            if (messageCount != recentOther.messageCount) { return false; }

            return true;

        }  else {
            return false;
        }
    }
}
