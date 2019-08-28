package com.gg.reactionlight_0.dataBase.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.gg.reactionlight_0.dataBase.entity.WorkWithSteps;

import java.util.List;

@Dao
public interface WorkWithStepsDao {

    @Query("SELECT * FROM work_table ORDER BY id DESC")
    LiveData<List<WorkWithSteps>> getAllWorkWithSteps();
}
