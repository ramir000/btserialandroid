package com.gg.reactionlight_0.dataBase.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gg.reactionlight_0.dataBase.Repository.StepRepository;
import com.gg.reactionlight_0.dataBase.dao.StepDao;
import com.gg.reactionlight_0.dataBase.entity.Step;

import java.util.LinkedList;
import java.util.List;

public class StepViewModel extends AndroidViewModel {
    private StepRepository repository;
    private LiveData<List<Step>> allSteps;

    public StepViewModel(@NonNull Application application) {
        super(application);
        repository = new StepRepository(application);
        allSteps = repository.getAllSteps();
    }

    public void insert(Step step) {
        repository.insert(step);
    }

    public void update(Step step) {
        repository.update(step);
    }

    public void delete(Step step) {
        repository.delete(step);
    }

    public Boolean containsWork(int id) {
        return repository.containsWork(id);
    }

    public void deleteAllNotes() {
        repository.deleteAllSteps();
    }

    public LiveData<List<Step>> getAllSteps() {
        return allSteps;
    }

    public LiveData<List<Step>>  getAllStepsForOwner(int owner) {
        return repository.getAllStepsForOwner(owner);
    }

    public int getLast(int owner) {
        int max = 0;
        if (allSteps.getValue() != null) {
            List<Step> ls = allSteps.getValue();
            for (Step s : ls) {
                if (s.getOwner() == owner)
                    if (s.getOrder() >= max) {
                        max = s.getOrder();
                    }

            }
        }
        return max;
    }

}
