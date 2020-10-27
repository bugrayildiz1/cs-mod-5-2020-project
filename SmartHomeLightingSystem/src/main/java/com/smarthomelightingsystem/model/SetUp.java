package com.smarthomelightingsystem.model;

public class SetUp {
    /**
     * Length and width
     */
    int p;
    int q;
    /**
     * RGB values
     */
    int r;
    int g;
    int b;
    /**
     * Brightness
     */
    float a;

    /**
     * animation id
     */
    int a_id;
    int p_id;
    boolean power;

    /**
     * Sets configuration
     * @param p length
     * @param q width
     */
    public void setConfiguration(int p, int q){
        this.p=p;
        this.q=q;
    }

    /**
     * sets an RGB value
     * @param r
     * @param g
     * @param b
     */
    public  void setRGB(int r, int g, int b){
        this.r=r;
        this.g=g;
        this.b=b;
    }

    /**
     * Sets the brightness
     * @param a brightness
     */
    public void setBrightness(float a){
        this.a = a;
    }

    public void setAnimation (int a_id){
        this.a_id=a_id;

    }

    public void setMode(boolean power){
        this.power=power;
    }

    public void setPreset(int p_id){
        this.p_id=p_id;
    }

    public int getAnimation(){
        return this.a_id;
    }

    public int getPreset(){
        return this.p_id;
    }

    public int getP() {
        return p;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public float getA() {
        return a;
    }

    public boolean isPower() {
        return power;
    }
}

