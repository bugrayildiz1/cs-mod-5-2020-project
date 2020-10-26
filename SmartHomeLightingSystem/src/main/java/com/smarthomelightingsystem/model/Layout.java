package com.smarthomelightingsystem.model;

import java.util.ArrayList;

public class Layout extends ArrayList<Color>{

    public Layout(){super();}

    public void put(Color color){
        super.add(color);
    }

    public void remove(Color color){
        super.remove(color);
    }

    @Override
    public Color get(int index) {
        return super.get(index);
    }
}
