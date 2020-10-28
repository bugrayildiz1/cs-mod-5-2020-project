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
                        @QueryParam("a") float a) { new SetupDAO().setRGBA(r, g, b, a); }

    @Path("anim")
    @POST
    public void setAnim(@QueryParam("id") int id) { new SetupDAO().setAnimation(id); }

    @Path("preset")
    @POST
    public void setPreset(@QueryParam("id") int id) { new SetupDAO().setPreset(id); }

    @Path("power")
    @POST
    public void setPower(@QueryParam("p") boolean p) { new SetupDAO().setPower(p); }

}
