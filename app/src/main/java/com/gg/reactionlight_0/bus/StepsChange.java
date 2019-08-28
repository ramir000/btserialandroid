package com.gg.reactionlight_0.bus;

import java.util.HashMap;
import java.util.List;

public class StepsChange {
    private List<HashMap> data;

    public StepsChange(List<HashMap> data) {
        this.data = data;
    }

    public List<HashMap> getData() {
        return data;
    }
}
