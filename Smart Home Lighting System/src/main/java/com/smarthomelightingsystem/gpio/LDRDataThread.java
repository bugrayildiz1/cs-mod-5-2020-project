package com.smarthomelightingsystem.gpio;

import com.smarthomelightingsystem.dao.LDRDataDAO;

public class LDRDataThread extends Thread {

    private LDRDataDAO dao = new LDRDataDAO();

    public LDRDataThread() { run(); }

    @Override
    public void run() {

        while (true) {

            dao.setValue(LDRReader.readLDR());

            try { sleep(10 * 1000); }
            catch (InterruptedException e) { e.printStackTrace(); }

        }

    }

}
