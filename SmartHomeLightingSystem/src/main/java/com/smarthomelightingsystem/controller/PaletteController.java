package com.smarthomelightingsystem.controller;

import com.smarthomelightingsystem.dao.PaletteDAO;
import com.smarthomelightingsystem.dao.AnimationDAO;
import com.smarthomelightingsystem.dao.PresetDAO;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("palette")
public class PaletteController {
    @POST
    public void newPalette(@QueryParam("color") String color, @QueryParam("brightness") int brightness){
        try {
            PaletteDAO palette = new PaletteDAO();
            palette.setUserBrightness(brightness);
            palette.setUserColor(color);
        }catch (Exception e){

        }
    }
    @POST
    @Path("animation")
    public void setAnimation(@QueryParam("id")  int id){

        try{
            AnimationDAO animationDao = new AnimationDAO();

        }catch (Exception e){

        }
    }
    @POST
    @Path("preset")
    public void setPreset(@QueryParam("id") int id){
        try{
            PresetDAO presetDAO=new PresetDAO();

        }catch (Exception e){

        }
    }


}
