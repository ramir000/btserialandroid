package com.gg.reactionlight_0.dataBase.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.gg.reactionlight_0.dataBase.dao.WorkWithStepsDao;
import com.gg.reactionlight_0.dataBase.AppDB;
import com.gg.reactionlight_0.dataBase.entity.WorkWithSteps;

import java.util.List;


public class WorkWithStepsRepository {
    private WorkWithStepsDao mWorkWithStepsDao;
    private LiveData<List<WorkWithSteps>> allWorkWithSteps;

    public WorkWithStepsRepository(Application application) {
        AppDB database = AppDB.getInstance(application);
        this.mWorkWithStepsDao = database.workWithStepsDao();
        this.allWorkWithSteps = mWorkWithStepsDao.getAllWorkWithSteps();
    }

    public LiveData<List<WorkWithSteps>> getAllWorkWithSteps() {
        return this.allWorkWithSteps;
    }

}