package com.tradeshift.messages.resource;

import com.tradeshift.messages.forjson.ResponseMessage;
import com.tradeshift.messages.forjson.ResponseRecentMessages;
import com.tradeshift.messages.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by ajo on 08/06/15.
 * <p/>
 *
 * @{MessageResource} is a REST Resource class.
 * It talks to @{MessageService} class.
 */

@Component
@Scope("prototype")
@Path("/")
public class MessageResource {
    private final MessageService msgService;

    @Autowired
    public MessageResource(MessageService msgService) {
        this.msgService = msgService;
    }

    @POST
    @Path("/names/{name: [a-zA-Z]+ }")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage getResponseMessage(@PathParam("name") String name) {
        return msgService.getResponseMessage(name);
    }

    @GET
    @Path("/recent")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRecentMessages getRecentMessages() {
        return msgService.getRecentMessages();
    }
}
