package com.grizzlywithjaxrs.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("hello")
public class HelloApi
{
    @GET
    public Response hi()
    {
        return Response.ok("hello").build();
    }
}
