package com.smarthomelightingsystem.model;

import java.util.ArrayList;

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
public class Strip extends ArrayList<LED>{
    public Strip(){
        super();
    }
    public int size(){
        return super.size();
    }
    @Override
    public LED remove(int index) {
        return super.remove(index);
    }

    @Override
    public boolean add(LED led) {
        return super.add(led);
    }

    @Override
    public LED get(int index) {
        return super.get(index);
    }
}
