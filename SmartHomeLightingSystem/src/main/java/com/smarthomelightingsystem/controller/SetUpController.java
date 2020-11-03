package com.smarthomelightingsystem.controller;

import com.smarthomelightingsystem.dao.SetupDAO;
import com.smarthomelightingsystem.dao.StripDAO;
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
    public void setSetUp(SetUp setUp) { new SetupDAO().setSetUp(setUp); }

    @Path("pq")
    @POST
    public void setPQ(@QueryParam("p") int p,
                      @QueryParam("q") int q) { new SetupDAO().setPQ(p, q); }

    @Path("rgba")
    @POST
    public void setRGBA(@QueryParam("r") int r,
                        @QueryParam("g") int g,
                        @QueryParam("b") int b,
                        @QueryParam("a") float a) { 
    	
    	new SetupDAO().setRGBA(r, g, b, a); 
    	new StripDAO().setRGBA(r, g, b, a); 
    	
    }

    @Path("anim")
    @POST
    public void setAnim(@QueryParam("id") int id) {
        new SetupDAO().setAnimation(id);
        new SetupDAO().setPreset(0);
        new StripDAO().setAnimation(id);
        new StripDAO().setPreset(0);
    }

    @Path("preset")
    @POST
    public void setPreset(@QueryParam("id") int id) {
        new SetupDAO().setPreset(id);
        new SetupDAO().setAnimation(0);
        new StripDAO().setPreset(id);
        new StripDAO().setAnimation(0);
    }

    @Path("power")
    @POST
    public void setPower(@QueryParam("p") boolean p) { new SetupDAO().setPower(p); }

}
