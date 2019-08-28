package com.gg.reactionlight_0.dataBase.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "mode_table",
        foreignKeys = {
                @ForeignKey(entity = Mode.class,
                        parentColumns = "id",
                        childColumns = "belongto")
        })
public class Mode {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int belongto;
    private String name;
    private String desc;

    public Mode(int belongto, String name, String desc) {
        this.belongto = belongto;
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBelongto() {
        return belongto;
    }

    public void setBelongto(int belongto) {
        this.belongto = belongto;
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
}
