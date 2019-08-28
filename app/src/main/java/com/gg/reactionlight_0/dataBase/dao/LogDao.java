package com.gg.reactionlight_0.dataBase.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gg.reactionlight_0.dataBase.entity.Log;

import java.util.List;

@Dao
public interface LogDao {
    @Insert
    void insert(Log step);

    @Update
    void update(Log step);

    @Delete
    void delete(Log step);

    @Query("DELETE FROM log_table")
    void deleteAllLogs();

    @Query("SELECT * FROM log_table")
    LiveData<List<Log>> getAllLogs();
}
