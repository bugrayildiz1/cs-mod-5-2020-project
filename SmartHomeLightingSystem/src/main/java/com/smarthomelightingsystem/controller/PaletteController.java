package com.smarthomelightingsystem.controller;

import com.smarthomelightingsystem.dao.AnimationDAO;
import com.smarthomelightingsystem.dao.PresetDAO;
import com.smarthomelightingsystem.model.palette.PalettePage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("page/palette")
public class PaletteController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PalettePage getFunctions() {

        PalettePage pp = new PalettePage();
        pp.setAnimations(new AnimationDAO().getAll());
        pp.setPresets(new PresetDAO().getAll());
        return pp;

    }

}
