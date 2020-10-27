package com.smarthomelightingsystem.controller;

import com.smarthomelightingsystem.dao.AnimationDAO;
import com.smarthomelightingsystem.dao.PresetDAO;
import com.smarthomelightingsystem.model.pallate.PallatePage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("page/palette")
public class PaletteController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PallatePage getFunctions() {

        PallatePage pp = new PallatePage();
        pp.setAnimations(new AnimationDAO().getAll());
        pp.setPresets(new PresetDAO().getAll());
        return pp;
        // test

    }

}
