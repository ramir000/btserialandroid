package com.gg.reactionlight_0.dataBase.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gg.reactionlight_0.dataBase.entity.Work;

import java.util.List;
//TODO : @Entity(indices = {@Index("name"),
//        @Index(value = {"last_name", "address"})})
@Dao
public interface  WorkDao {
    @Insert
    long insert(Work step);

    @Update
    void update(Work step);

    @Delete
    void delete(Work step);

    @Query("DELETE FROM work_table")
    void deleteAllWorks();

    @Query("SELECT * FROM work_table ORDER BY id DESC")
    LiveData<List<Work>> getAllWorks();


    @Query("SELECT * FROM work_table ORDER BY id DESC LIMIT 1" )
    LiveData<List<Work>> getLast();
}
