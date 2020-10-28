package com.smarthomelightingsystem.dao;

import com.smarthomelightingsystem.model.LDRData;

public class LDRDataDAO {

    public LDRData getDefault(boolean mobile) { // last 8h | mobile false: every h; mobile true: every 2h
        return null;
    }

    public LDRData getDay(boolean mobile) { // mobile false: every 2h; mobile true: every 4h
        return null;
    }

    public LDRData getWeek() { // every day regardless of mobile
        return null;
    }

    public LDRData getMonth(boolean mobile) { // mobile false: every 2d; mobile true: every 4d

        return null;

    }

    public void setValue(float value) {

    }

}
