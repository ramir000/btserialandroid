package com.gg.reactionlight_0.dataBase.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "step_table")
public class Step {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int owner;
    private Double delay;
    private Double timelimit;
    private int order;
    private String to;
    private String data;

    public Step(int owner, Double delay, Double timelimit, int order, String to, String data) {
        this.owner = owner;
        this.delay = delay;
        this.timelimit = timelimit;
        this.order = order;
        this.to = to;
        this.data = data;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Double getDelay() {
        return delay;
    }

    public void setDelay(Double delay) {
        this.delay = delay;
    }

    public Double getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(Double timelimit) {
        this.timelimit = timelimit;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
