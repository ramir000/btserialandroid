package com.gg.reactionlight_0.dataBase.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.gg.reactionlight_0.dataBase.dao.StepDao;
import com.gg.reactionlight_0.dataBase.AppDB;
import com.gg.reactionlight_0.dataBase.entity.Step;

import java.util.List;


public class StepRepository {
    private StepDao stepDao;
    private LiveData<List<Step>> allSteps;

    public StepRepository(Application application) {
        AppDB database = AppDB.getInstance(application);
        stepDao = database.stepDao();
        allSteps = stepDao.getAllSteps();
    }

    public LiveData<List<Step>> getAllStepsForOwner(int owner){
        return stepDao.getAllStepsForOwner(owner);
    }

    public void insert(Step step) {
        new InsertStepAsyncTask(stepDao).execute(step);
    }

    public void update(Step step) {
        new UpdateStepAsyncTask(stepDao).execute(step);
    }

    public void delete(Step step) {
        new DeleteStepAsyncTask(stepDao).execute(step);
    }

    public void deleteAllSteps() {
        new DeleteAllStepsAsyncTask(stepDao).execute();
    }

    public Boolean containsWork(int id) {
        if(allSteps != null)
        for (Step s : allSteps.getValue())
            if (s.getOwner() == id)
                return true;
        return false;
    }

    public LiveData<List<Step>> getAllSteps() {
        return allSteps;
    }

    private static class InsertStepAsyncTask extends AsyncTask<Step, Void, Void> {
        private StepDao stepDao;

        private InsertStepAsyncTask(StepDao stepDao) {
            this.stepDao = stepDao;
        }

        @Override
        protected Void doInBackground(Step... steps) {
            stepDao.insert(steps[0]);
            return null;
        }
    }

    private static class UpdateStepAsyncTask extends AsyncTask<Step, Void, Void> {
        private StepDao stepDao;

        private UpdateStepAsyncTask(StepDao stepDao) {
            this.stepDao = stepDao;
        }

        @Override
        protected Void doInBackground(Step... steps) {
            stepDao.update(steps[0]);
            return null;
        }
    }

    private static class DeleteStepAsyncTask extends AsyncTask<Step, Void, Void> {
        private StepDao stepDao;

        private DeleteStepAsyncTask(StepDao stepDao) {
            this.stepDao = stepDao;
        }

        @Override
        protected Void doInBackground(Step... steps) {
            stepDao.delete(steps[0]);
            return null;
        }
    }

    private static class DeleteAllStepsAsyncTask extends AsyncTask<Void, Void, Void> {
        private StepDao stepDao;

        private DeleteAllStepsAsyncTask(StepDao stepDao) {
            this.stepDao = stepDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            stepDao.deleteAllSteps();
            return null;
        }
    }
}