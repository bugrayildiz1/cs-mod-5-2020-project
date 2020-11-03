package com.smarthomelightingsystem.gpio;

import com.smarthomelightingsystem.dao.StripDAO;

public class LDRStripThread extends Thread {

    private volatile boolean exit = true;

    private StripDAO dao = new StripDAO();

    public LDRStripThread() {run();}

    @Override
    public void run() {

        while (exit){

            dao.setRGBA(1,255,255,255,1-LDRReader.readLDR());

            try { sleep(10 * 1000); }
            catch (InterruptedException e) { e.printStackTrace(); }
        }

    }
    public void quit(){
        exit = false;
    }

}
