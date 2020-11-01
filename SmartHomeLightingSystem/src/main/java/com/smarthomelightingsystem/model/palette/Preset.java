package com.smarthomelightingsystem.model.palette;

public class Preset {

    private int id;
    private String primary;
    private String secondary;
    /**
     * Create Preset object
     */
    public Preset() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }
}
