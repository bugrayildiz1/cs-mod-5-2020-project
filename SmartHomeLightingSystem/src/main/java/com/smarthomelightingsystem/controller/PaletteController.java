package com.smarthomelightingsystem.controller;

import com.smarthomelightingsystem.dao.PaletteDAO;
import com.smarthomelightingsystem.dao.AnimationDAO;
import com.smarthomelightingsystem.dao.PresetDAO;
import com.smarthomelightingsystem.model.pallate.PallatePage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("page/palette")
public class PaletteController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PallatePage getFunctions() {

        return new

    }

}
