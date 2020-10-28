package com.smarthomelightingsystem.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class TestController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String test() {
        return "<h1>Test succesful!</h1>";
    }

    // Returns "Text Successful!" when accessing /rest/test

}
