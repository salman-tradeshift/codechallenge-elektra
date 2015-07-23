package com.tradeshift.rest.response;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Preconditions;
import com.tradeshift.dto.MessageDTO;
import com.tradeshift.util.TSDateUtil;

public final class GetRecentMessagesResponse implements Serializable {

    private static final long serialVersionUID = -4227947057262227741L;
    private final int messageCount;
    private final String lastMessage;
    private final List<CreateMessageResponse> messages;

    public GetRecentMessagesResponse(MessageDTO messageDTO) {
        Preconditions.checkNotNull(messageDTO);
        this.messageCount = messageDTO.getMessages().size();
        this.messages = messageDTO.getMessages();
        this.lastMessage = TSDateUtil.convertTimestampToISO8601(messageDTO.getLastMessage());
    }

    public int getMessageCount() {
        return messageCount;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public List<CreateMessageResponse> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "GetResponse [messageCount=" + messageCount + ", lastMessage=" + lastMessage
                + ", messages=" + messages + "]";
    }
}
