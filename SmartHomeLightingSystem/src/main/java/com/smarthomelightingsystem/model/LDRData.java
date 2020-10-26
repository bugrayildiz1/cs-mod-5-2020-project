package com.smarthomelightingsystem.model;

import java.util.ArrayList;
import java.util.List;

public class LDRData {
    List<Integer> data = new ArrayList<>();
    List<Integer> labels = new ArrayList<>();
     public LDRData(){ }

    public List<Integer> getData() {
        return data;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }
}
