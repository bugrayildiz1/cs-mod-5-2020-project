package com.smarthomelightingsystem.model;
/**
 * The Configuration class stores information about the width and
 * length of the screen the user is using
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
public class Configuration {

    /**
     * length of the display
     */
    private int length;
    /**
     * width of the display
     */
    private int width;

    /**
     * Create a configuration object
     */
    public Configuration(){ }

    /**
     * @return the length of the screen
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length of display to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the width of the screen
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width of display to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Calculate number of pixels needed on LED strip
     * @return the number of pixels on LED strip
     */
    public int getNumOfPix(){
        return 2*(length+width);
    }

}
