package com.smarthomelightingsystem.controller;


import com.smarthomelightingsystem.dao.LDRDataDAO;
import com.smarthomelightingsystem.exceptions.IllegalLDRDataScopeException;
import com.smarthomelightingsystem.model.LDRData;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("page/ldr")
public class LDRDataController {

    @Path("data")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public LDRData getLDRData(@QueryParam("scope") String scope, @QueryParam("mobile") boolean mobile) {

        try {

            switch (scope) {
                case "def": return new LDRDataDAO().getDefault(mobile);
                case "day": return new LDRDataDAO().getDay(mobile);
                case "week": return new LDRDataDAO().getWeek();
                case "month": return new LDRDataDAO().getMonth(mobile);
                default: throw new IllegalLDRDataScopeException();
            }

        } catch (IllegalLDRDataScopeException e) {
            e.printStackTrace();
            return null;
        }

    }
    
    @Path("setvalue")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public LDRData setValue(@QueryParam("value") float value) {
    	try {
    		if(0.0<= value && value <= 1.0) {
    			new LDRDataDAO().setValue(value);
    			
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
		return null;
    	
    }

}