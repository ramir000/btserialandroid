package com.gg.reactionlight_0.dataBase.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gg.reactionlight_0.dataBase.Repository.PlayerRepository;
import com.gg.reactionlight_0.dataBase.entity.Player;

import java.util.List;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerRepository repository;
    private LiveData<List<Player>> allActidades;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        repository = new PlayerRepository(application);
        allActidades = repository.getAllPlayers();
    }

    public void insert(Player player) {
        repository.insert(player);
    }

    public void update(Player player) {
        repository.update(player);
    }

    public void delete(Player player) {
        repository.delete(player);
    }

    public void deleteAllNotes() {
        repository.deleteAllPlayers();
    }

    public LiveData<List<Player>> getAllPlayers() {
        return allActidades;
    }
}
