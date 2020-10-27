package com.smarthomelightingsystem.controller;

import com.smarthomelightingsystem.dao.SetupDAO;
import com.smarthomelightingsystem.model.SetUp;



import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("setup")
public class SetUpController {

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public SetUp getSetUp(){
        return new SetupDAO().getSetUp();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void setConfiguration(SetUp setUp){
        new SetupDAO().setConfiguration(setUp);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void setColor(SetUp setUp){
       new SetupDAO().setColor(setUp);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public  void setAnimationPreset(SetUp setUp){
        new SetupDAO().setAnimationPreset(setUp);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void setMode(SetUp setUp){
        new SetupDAO().setMode(setUp);
    }
}
