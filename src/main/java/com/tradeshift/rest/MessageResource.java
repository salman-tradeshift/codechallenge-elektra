package com.tradeshift.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeshift.dto.Message;
import com.tradeshift.exception.ServiceException;
import com.tradeshift.rest.response.CreateMessageResponse;
import com.tradeshift.service.MessageService;

@Path("/messages")
@Component
public class MessageResource {
    private static final Logger logger = Logger.getLogger(MessageResource.class);
    private MessageService messageService;

    @Autowired
    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    @Path("/names/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createName(String name) {
        try {
            Message message = messageService.createMessage(name);
            CreateMessageResponse response = new CreateMessageResponse(message);
            return Response.ok(response).build();

        } catch (ServiceException se) {
            logger.error("Unable to process content", se);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/recent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentMessages() {
        return null;
    }
}
