package com.smarthomelightingsystem.model;
import java.util.HashMap;

//This class is the prototype of the data(color and brightness) being sent to pi.  

/**
 * The class works with animations of LED strip 
 * shown on the Palette page of web interface
 * 
 * @author Jasper van Amerongen
 * @author Albina Shynkar
 * @author Lola Solovyeva
 * @author Ilya Averchenko
 * @author Bugra Yildiz
 * @author Alexandru Matcov

 * 
 * @version 1
 *
 */

public class Color extends HashMap<RGB, Integer>{
	
	public Color() { super(); };
	

	public Integer put(RGB rgb, Integer integer) {
		return super.put(rgb, integer);
	}
	public int get(RGB rgb){
		return super.get(rgb);
	}

}
