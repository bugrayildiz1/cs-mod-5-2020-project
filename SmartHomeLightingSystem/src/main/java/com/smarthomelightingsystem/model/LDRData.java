package com.smarthomelightingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class LDRData {

    List<Integer> data = new ArrayList<>();
    List<String> labels = new ArrayList<>();

    public LDRData() {
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
