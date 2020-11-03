package com.smarthomelightingsystem.dao;

import com.smarthomelightingsystem.exceptions.IllegalSetupException;

/**
 * The class works with LED strip, calls animations or
 * presets depending on their IDs from python code on physical layer
 * 
 * @author Jasper van Amerongen
 * @author Albina Shynkar
 * @author Lola Solovyeva
 * @author Ilya Averchenko
 * @author Bugra Yildiz
 * @author Alexandru Matcov

 * 
 * @version 1
 **/
public class StripDAO {
	
	private static final String DIRECTORY = "/home/pi//python_led/home/illya/rpi_ws281x/python/examples";
	/**
     * Sends r,g,b,a to physical layer
     * 
     * @param r red value
     * @param g green value
     * @param b blue value
     * @param a brightness value
     */
    public void setRGBA(int id, int r, int g, int b, float a) {
        try {
    	String cmd =  "python " +
    		      DIRECTORY +
    		      Integer.toString(id) +
    		      Integer.toString(r) +
    		      Integer.toString(g) +
    		      Integer.toString(b) +
    		      Float.toString(a);
    	
    	Runtime.getRuntime().exec(cmd);

        } catch (Exception e) { e.printStackTrace(); }
    }
    /**
     * Sends to physical layer id of animation to be executed 
     * 
     * @param id id of preset
     * @throws IllegalSetupException 
     */
    public void setAnimation(int id, int r, int g, int b, float a) throws IllegalSetupException {
    	if (id < 0) throw new IllegalSetupException();
		try {
			String cmd = "python " +
	    		      DIRECTORY +
	    		      Integer.toString(id) +
	    		      Integer.toString(r) +
	    		      Integer.toString(g) +
	    		      Integer.toString(b) +
	    		      Float.toString(a);
			
	    	Runtime.getRuntime().exec(cmd);

		} catch (Exception e) { e.printStackTrace(); }

	}
    
    /**
     * Sends to physical layer id of preset to be executed 
     * 
     * @param id id of preset
     * @throws IllegalSetupException 
     */
    public void setPreset(int id, int r, int g, int b, float a) throws IllegalSetupException {
    	if (id < 0) throw new IllegalSetupException();
		try {
			String cmd = "python " +
	    		      DIRECTORY + 
	    		      Integer.toString(id) +
	    		      Integer.toString(r) +
	    		      Integer.toString(g) +
	    		      Integer.toString(b) +
	    		      Float.toString(a);
			
	    	Runtime.getRuntime().exec(cmd);

		} catch (Exception e) { e.printStackTrace(); }


	}

}
