package com.smarthomelightingsystem.model.palette;

public class Animation {
	/**
	 * id of animation
	 */
    protected int id;
    /**
     * name of the animation
     */
    protected String name;
    /**
     * icon of the animation
     */
    protected String icon;
    /**
     * Create Animation object
     */
    public Animation() {
    }
	/**
	 * Creates animation object with id, name, icon
	 * 
	 * @param id animation id
	 * @param name animation name
	 * @param icon animation icon
	 */
    public Animation(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
