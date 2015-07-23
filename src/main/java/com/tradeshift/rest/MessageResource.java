package com.tradeshift.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeshift.exception.ServiceException;
import com.tradeshift.rest.response.CreateMessageResponse;
import com.tradeshift.rest.response.GetRecentMessagesResponse;
import com.tradeshift.service.MessageService;

@Path("/messages")
@Component
public final class MessageResource {
    private static final Logger logger = Logger.getLogger(MessageResource.class);
    private final MessageService messageService;

    @Autowired
    public MessageResource(MessageService messageService) {
        this.messageService = messageService;
    }

    @POST
    @Path("/names/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMessage(@PathParam("name") String name) {
        try {
            CreateMessageResponse response = messageService.createMessage(name);
            return Response.ok(response).build();
        } catch (ServiceException se) {
            // There should be a meaningful message to the user
            // Will be implemented in iteration 3
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/recent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentMessages() {
        try {
            GetRecentMessagesResponse response = messageService.getRecentMessages();
            return Response.ok(response).build();
        } catch (Exception se) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
