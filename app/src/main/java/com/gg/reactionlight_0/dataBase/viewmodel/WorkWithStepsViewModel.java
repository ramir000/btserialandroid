package com.gg.reactionlight_0.dataBase.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gg.reactionlight_0.dataBase.Repository.WorkWithStepsRepository;
import com.gg.reactionlight_0.dataBase.entity.WorkWithSteps;

import java.util.List;

public class WorkWithStepsViewModel extends AndroidViewModel {
    private WorkWithStepsRepository repository;
    private LiveData<List<WorkWithSteps>> allActidades;

    public WorkWithStepsViewModel(@NonNull Application application) {
        super(application);
        repository = new WorkWithStepsRepository(application);
        allActidades = repository.getAllWorkWithSteps();
    }

    public LiveData<List<WorkWithSteps>> getAllWorkWithSteps() {
        return allActidades;
    }

}
