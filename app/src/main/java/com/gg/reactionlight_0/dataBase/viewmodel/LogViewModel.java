package com.gg.reactionlight_0.dataBase.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gg.reactionlight_0.dataBase.Repository.LogRepository;
import com.gg.reactionlight_0.dataBase.entity.Log;

import java.util.List;

public class LogViewModel extends AndroidViewModel {
    private LogRepository repository;
    private LiveData<List<Log>> allActidades;

    public LogViewModel(@NonNull Application application) {
        super(application);
        repository = new LogRepository(application);
        allActidades = repository.getAllLogs();
    }

    public void insert(Log log) {
        repository.insert(log);
    }

    public void update(Log log) {
        repository.update(log);
    }

    public void delete(Log log) {
        repository.delete(log);
    }

    public void deleteAllNotes() {
        repository.deleteAllLogs();
    }

    public LiveData<List<Log>> getAllLogs() {
        return allActidades;
    }
}
