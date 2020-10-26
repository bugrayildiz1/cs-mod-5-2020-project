package com.smarthomelightingsystem.dao;

import com.smarthomelightingsystem.model.Color;
import com.smarthomelightingsystem.model.Palette;
import com.smarthomelightingsystem.model.RGB;

/**
 * The class works with palette page
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
public class PaletteDAO {

	Palette palette = new Palette();

	public void setUserBrightness(int brightness) {
		palette.setBrightness(brightness);
	}
	
	public void setUserColor(String color) {
		int r = Integer.valueOf( color.substring( 1, 3 ), 16 );
		int g = Integer.valueOf( color.substring( 3, 5 ), 16 );
		int b = Integer.valueOf( color.substring( 5, 7 ), 16 );
		Color c = new Color();
		c.put(RGB.R,r);
		c.put(RGB.G,g);
		c.put(RGB.B,b);
		palette.setColor(c);
	}

}
