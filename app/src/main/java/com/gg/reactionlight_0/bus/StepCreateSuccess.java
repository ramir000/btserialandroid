package com.gg.reactionlight_0.bus;

import java.util.HashMap;

public class StepCreateSuccess {
    private HashMap data;

    public StepCreateSuccess(HashMap data) {
        this.data = data;
    }

    public HashMap getData() {
        return data;
    }
}