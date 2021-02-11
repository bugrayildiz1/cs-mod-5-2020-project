package com.smarthomelightingsystem.controller;

import com.smarthomelightingsystem.dao.SetupDAO;
import com.smarthomelightingsystem.dao.StripDAO;
import com.smarthomelightingsystem.exceptions.IllegalSetupException;
import com.smarthomelightingsystem.model.Setup;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("setup")
public class SetUpController {

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Setup getSetUp(){

        return new SetupDAO().getSetUp();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void setSetUp(Setup s) throws IllegalSetupException {

        new SetupDAO().setSetup(s);

    }

    @Path("pq")
    @POST
    public void setPQ(@QueryParam("p") int p,
                      @QueryParam("q") int q) throws IllegalSetupException {

        new SetupDAO().setPQ(p, q);

    }

    @Path("rgba")
    @POST
    public void setRGBA(@QueryParam("r") int r,
                        @QueryParam("g") int g,
                        @QueryParam("b") int b,
                        @QueryParam("a") float a) throws IllegalSetupException {

        SetupDAO dao = new SetupDAO();

        dao.setRGBA(r, g, b, a);
        dao.setPreset(0);
        dao.setDoLDR(false);

    	new StripDAO().setRGBA(1,r, g, b, a);

    }

    @Path("anim")
    @POST
    public void setAnim(@QueryParam("id") int id) throws IllegalSetupException {

        SetupDAO dao = new SetupDAO();

        dao.setAnimation(id);
        dao.setPreset(0);
        dao.setDoLDR(false);

        Setup s = dao.getSetUp();
        new StripDAO().setAnimation(id, s.getR(),  s.getG(), s.getB(), s.getA());

    }

    @Path("preset")
    @POST
    public void setPreset(@QueryParam("id") int id) throws IllegalSetupException {

        SetupDAO dao = new SetupDAO();

        dao.setPreset(id);
        dao.setAnimation(0);
        dao.setRGBA(0, 0, 0, 0);
        dao.setDoLDR(false);

        Setup s = dao.getSetUp();
        new StripDAO().setPreset(id,0,0,0,s.getA());

    }

    @Path("ldr")
    @POST
    public void setDoLDR(@QueryParam("b") boolean b) throws IllegalSetupException {

        SetupDAO dao = new SetupDAO();
        dao.setDoLDR(b);

        if (b) {
            dao.setAnimation(0);
            dao.setPreset(0);
            dao.setRGBA(0, 0, 0, 0);
        }

    }

}
