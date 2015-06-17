package com.tradeshift.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
@Path("/names/{name: [a-zA-Z]+ }")
public class MessageResource {
    private final MessageService msgService;

    @Autowired
    public MessageResource(MessageService msgService) {
        this.msgService = msgService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage getResponseMessage(@PathParam("name") String name) {
        return msgService.getResponseMessage(name);
    }
}
