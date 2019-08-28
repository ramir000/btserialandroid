package com.gg.reactionlight_0.dataBase.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "log_table",
        primaryKeys = {"player", "work", "date"},
        foreignKeys = {
                @ForeignKey(entity = Player.class,
                        parentColumns = "id",
                        childColumns = "player"),
                @ForeignKey(entity = Work.class,
                        parentColumns = "id",
                        childColumns = "work")})
public class Log {

    private int player;
    private int work;
    private Double result;
    @NonNull
    private String date;

    public Log(int player, int work, Double result, String date) {
        this.player = player;
        this.work = work;
        this.result = result;
        this.date = date;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getWork() {
        return work;
    }

    public void setWork(int work) {
        this.work = work;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
