package com.tradeshift.elektra.johannes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.print.attribute.standard.Media;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

@Component
@Path("/")
public class MessageResource {

	private final MessageService messageService;

	@Autowired
	public MessageResource(MessageService messageService) {
		this.messageService = messageService;
	}

	@POST
	@Path("/names/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public MessageDTO postMessage(@PathParam("name") String name) {
		return messageService.createMessage(name);
	}

	@GET
	@Path("/recent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.TEXT_PLAIN)
	public Recent getRecent() {
		return messageService.createRecent();
	}
}