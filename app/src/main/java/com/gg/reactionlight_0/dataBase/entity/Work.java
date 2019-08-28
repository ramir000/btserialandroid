package com.gg.reactionlight_0.dataBase.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "work_table")
public class Work {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String desc;
    private boolean automatic;
    private boolean random;
    private boolean memoria;

    public Work(String name, String desc, boolean automatic, boolean random, boolean memoria) {
        this.name = name;
        this.desc = desc;
        this.automatic = automatic;
        this.random = random;
        this.memoria = memoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public boolean isMemoria() {
        return memoria;
    }

    public void setMemoria(boolean memoria) {
        this.memoria = memoria;
    }
}
