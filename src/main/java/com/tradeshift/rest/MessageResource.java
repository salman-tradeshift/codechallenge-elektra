package com.tradeshift.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public interface MessageResource {

    @GET
    @Path("/names/{name}")
    public Response createName(@PathParam("name") String name);
}
