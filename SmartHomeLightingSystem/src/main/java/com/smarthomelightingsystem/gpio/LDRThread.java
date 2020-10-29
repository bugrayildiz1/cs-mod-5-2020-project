package com.smarthomelightingsystem.gpio;

import com.smarthomelightingsystem.controller.LDRDataController;
import com.smarthomelightingsystem.dao.LDRDataDAO;

public class LDRThread extends Thread {

    private final static int MIN = 0;
    private final static int MAX = 0;
    private LDRDataDAO dao = new LDRDataDAO();

    public LDRThread() { run(); }

    @Override
    public void run() {

        while (true) {

            int value = LDRReader.readLDR();
            float perc = (value - MIN) / (MAX - MIN);
            dao.setValue(Math.round(perc * 100) / 100);

            try { sleep(10 * 1000); }
            catch (InterruptedException e) { e.printStackTrace(); }

        }

    }

}
