package com.gg.reactionlight_0.dataBase.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gg.reactionlight_0.dataBase.Repository.ModeRepository;
import com.gg.reactionlight_0.dataBase.entity.Mode;

import java.util.List;

public class ModeViewModel extends AndroidViewModel {
    private ModeRepository repository;
    private LiveData<List<Mode>> allActidades;

    public ModeViewModel(@NonNull Application application) {
        super(application);
        repository = new ModeRepository(application);
        allActidades = repository.getAllModes();
    }

    public void insert(Mode mode) {
        repository.insert(mode);
    }

    public void update(Mode mode) {
        repository.update(mode);
    }

    public void delete(Mode mode) {
        repository.delete(mode);
    }

    public void deleteAllNotes() {
        repository.deleteAllModes();
    }

    public LiveData<List<Mode>> getAllModes() {
        return allActidades;
    }
}
