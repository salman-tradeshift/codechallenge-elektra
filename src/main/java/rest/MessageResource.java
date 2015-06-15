package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by ksp on 08/06/15.
 */

@Component
@Path("names/{name}")
public class MessageResource {

    @Autowired
    private MessageService messageService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public MessageService getIt(@PathParam("name") String name) {
        messageService.setMessage("hello " + name);
        return messageService;
    }


}
