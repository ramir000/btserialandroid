package com.gg.reactionlight_0.dataBase.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gg.reactionlight_0.dataBase.Repository.WorkRepository;
import com.gg.reactionlight_0.dataBase.entity.Step;
import com.gg.reactionlight_0.dataBase.entity.Work;

import java.util.List;

public class WorkViewModel extends AndroidViewModel {
    private WorkRepository repository;
    private LiveData<List<Work>> allActidades;
    private LiveData<List<Work>> last;

    public WorkViewModel(@NonNull Application application) {
        super(application);
        repository = new WorkRepository(application);
        allActidades = repository.getAllWorks();
        last = repository.getLast();

    }

    public void insert(Work work, List<Step> Steps) {
         repository.insert(work,Steps);
    }

    public void update(Work work) {
        repository.update(work);
    }

    public void delete(Work work) {
        repository.delete(work);
    }

    public void deleteAllNotes() {
        repository.deleteAllWorks();
    }

    public LiveData<List<Work>> getAllWorks() {
        return allActidades;
    }

    public LiveData<List<Work>> getLast() {return last;}
}
