package com.gg.reactionlight_0.dataBase.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "player_table")
public class Player {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String lastname;
    private String pos;


    public Player(String name, String lastname, String pos) {
        this.name = name;
        this.lastname = lastname;
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPos() {
        return pos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }
}
