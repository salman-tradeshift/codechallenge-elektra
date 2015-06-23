package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Component
@Path("/messages")
public class MessageResource {

    private MessageService messageService;

    @POST
    @Path("/names/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseMessage postMessage(@PathParam("name") String name) {
        return messageService.saveMessage("hello " + name);
    }

    @GET
    @Path("/recent")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseRecent recentMessages(){
        return messageService.getLatestMessages();

    }

    @Autowired
    public MessageResource (MessageService messageService){
        this.messageService = messageService;
    }
}
