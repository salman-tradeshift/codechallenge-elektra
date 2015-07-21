package com.tradeshift.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface MessageResource {

    @POST
    @Path("/names/{name}")
    public Response createName(@PathParam("name") String name);

    @GET
    @Path("/recent")
    public Response getRecentMessages();
}
