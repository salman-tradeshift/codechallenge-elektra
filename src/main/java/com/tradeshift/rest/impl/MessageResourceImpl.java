package com.tradeshift.rest.impl;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.annotations.VisibleForTesting;
import com.tradeshift.dto.Message;
import com.tradeshift.dto.RestResponse;
import com.tradeshift.exception.ServiceException;
import com.tradeshift.rest.MessageResource;
import com.tradeshift.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class MessageResourceImpl implements MessageResource {
    private static final Logger logger = Logger.getLogger(MessageResource.class);
    @Autowired private MessageService messageService;

    @Override
    public Response createName(String name) {
        try {
            Message message = messageService.processContent(name);
            RestResponse response = new RestResponse();
            response.setMessage(message);
            return Response.ok(response).build();

        } catch (ServiceException se) {
            logger.error("Unable to process content", se);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    public MessageService getMessageService() {
        return messageService;
    }

    @VisibleForTesting
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
