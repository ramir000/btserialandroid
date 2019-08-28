package com.gg.reactionlight_0.dataBase.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gg.reactionlight_0.dataBase.entity.Step;

import java.util.List;

@Dao
public interface StepDao {
    @Insert
    void insert(Step step);

    @Update
    void update(Step step);

    @Delete
    void delete(Step step);

    @Query("DELETE FROM step_table")
    void deleteAllSteps();

    @Query("SELECT * FROM step_table")
    LiveData<List<Step>> getAllSteps();

    @Query("SELECT * FROM step_table WHERE id = :owner")
    LiveData<List<Step>> getAllStepsForOwner(int owner);
}
