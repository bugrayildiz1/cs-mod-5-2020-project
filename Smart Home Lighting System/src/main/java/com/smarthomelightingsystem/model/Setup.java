package com.smarthomelightingsystem.model;

public class Setup {

    /**
     * Length and width
     */
    int p;
    int q;

    /**
     * RGBA values
     */
    int r;
    int g;
    int b;
    float a;

    /**
     * Animation and Preset id's
     */
    int animId;
    int presetId;

    /**
     * Misc. settings
     */
    boolean doLDR;

    /**
     * Sets configuration
     * @param p length
     * @param q width
     */
    public void setPQ(int p, int q) {

        this.p = p;
        this.q = q;

    }

    /**
     * sets an RGBA value
     * @param r
     * @param g
     * @param b
     * @param a
     */
    public  void setRGBA(int r, int g, int b, float a) {

        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;

    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public int getAnimId() {
        return animId;
    }

    public void setAnimId(int animId) {
        this.animId = animId;
    }

    public int getPresetId() {
        return presetId;
    }

    public void setPresetId(int presetId) {
        this.presetId = presetId;
    }

    public boolean getDoLDR() { return doLDR; }

    public void setDoLDR(boolean doLDR) { this.doLDR = doLDR; }

}

