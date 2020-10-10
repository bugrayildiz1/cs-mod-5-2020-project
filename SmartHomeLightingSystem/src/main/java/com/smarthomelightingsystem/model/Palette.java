package com.smarthomelightingsystem.model;
/**
 * The Palette class stores information about palette page of 
 * Smart Home Lighting System web app, works with LED strip 
 * brightness and color.
 * 
 * @author Jasper van Amerongen
 * @author Albina Shynkar
 * @author Lola Solovyeva
 * @author Ilya Averchenko
 * @author Bugra Yildiz
 * @author Alexandru Matcov
 * 
 * @version 1
 */
public class Palette {
    /**
     * color of LED strip
     */
    private int color;
    /**
     * brightness of LED strip
     */
    private int brightness;

    /**
     * Create a palette object
     */
    public Palette(){ }

    /**
     * @return color of LED strip
     */
    public int getColor() {
        return color;
    }

    /**
     * @param color the color of LED strip to set
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * @return brightness of LED strip
     */
    public int getBrightness() {
        return brightness;
    }

    /**
     * @param brightness the brightness of LED strip to set
     */
    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

}
