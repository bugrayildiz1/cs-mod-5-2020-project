package com.smarthomelightingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class LDRData {
	/**
	 * List of data values retrieved from LDR
	 */
    List<Float> data = new ArrayList<>();
    /**
     * List of timestamps
     */
    List<String> labels = new ArrayList<>();
    /**
     * Create LDRData object
     */
    public LDRData() {
    }

    public List<Float> getData() {
        return data;
    }

    public void setData(List<Float> data) {
        this.data = data;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
