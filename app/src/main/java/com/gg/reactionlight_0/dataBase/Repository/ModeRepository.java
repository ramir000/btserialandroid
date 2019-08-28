package com.gg.reactionlight_0.dataBase.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.gg.reactionlight_0.dataBase.dao.ModeDao;
import com.gg.reactionlight_0.dataBase.AppDB;
import com.gg.reactionlight_0.dataBase.entity.Mode;

import java.util.List;


public class ModeRepository {
    private ModeDao modeDao;
    private LiveData<List<Mode>> allModes;

    public ModeRepository(Application application) {
        AppDB database = AppDB.getInstance(application);
        modeDao = database.modeDao();
        allModes = modeDao.getAllModes();
    }

    public void insert(Mode mode) {
        new InsertModeAsyncTask(modeDao).execute(mode);
    }

    public void update(Mode mode) {
        new UpdateModeAsyncTask(modeDao).execute(mode);
    }

    public void delete(Mode mode) {
        new DeleteModeAsyncTask(modeDao).execute(mode);
    }

    public void deleteAllModes() {
        new DeleteAllModesAsyncTask(modeDao).execute();
    }

    public LiveData<List<Mode>> getAllModes() {
        return allModes;
    }

    private static class InsertModeAsyncTask extends AsyncTask<Mode, Void, Void> {
        private ModeDao modeDao;

        private InsertModeAsyncTask(ModeDao modeDao) {
            this.modeDao = modeDao;
        }

        @Override
        protected Void doInBackground(Mode... modes) {
            modeDao.insert(modes[0]);
            return null;
        }
    }

    private static class UpdateModeAsyncTask extends AsyncTask<Mode , Void, Void> {
        private ModeDao modeDao;

        private UpdateModeAsyncTask(ModeDao modeDao) {
            this.modeDao = modeDao;
        }

        @Override
        protected Void doInBackground(Mode... modes) {
            modeDao.update(modes[0]);
            return null;
        }
    }

    private static class DeleteModeAsyncTask extends AsyncTask<Mode, Void, Void> {
        private ModeDao modeDao;

        private DeleteModeAsyncTask(ModeDao modeDao) {
            this.modeDao = modeDao;
        }

        @Override
        protected Void doInBackground(Mode... modes) {
            modeDao.delete(modes[0]);
            return null;
        }
    }

    private static class DeleteAllModesAsyncTask extends AsyncTask<Void, Void, Void> {
        private ModeDao modeDao;

        private DeleteAllModesAsyncTask(ModeDao modeDao) {
            this.modeDao = modeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            modeDao.deleteAllModes();
            return null;
        }
    }
}