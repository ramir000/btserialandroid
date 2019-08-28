package com.gg.reactionlight_0.dataBase.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gg.reactionlight_0.dataBase.entity.Mode;

import java.util.List;

@Dao
public interface ModeDao {
    @Insert
    void insert(Mode mode);

    @Update
    void update(Mode mode);

    @Delete
    void delete(Mode mode);

    @Query("DELETE FROM mode_table")
    void deleteAllModes();

    @Query("SELECT * FROM mode_table")
    LiveData<List<Mode>> getAllModes();
}
