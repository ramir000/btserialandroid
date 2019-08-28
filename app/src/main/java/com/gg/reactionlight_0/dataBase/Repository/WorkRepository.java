package com.gg.reactionlight_0.dataBase.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.gg.reactionlight_0.dataBase.AppDB;
import com.gg.reactionlight_0.dataBase.dao.StepDao;
import com.gg.reactionlight_0.dataBase.dao.WorkDao;
import com.gg.reactionlight_0.dataBase.entity.Step;
import com.gg.reactionlight_0.dataBase.entity.Work;
import com.gg.reactionlight_0.dataBase.entity.WorkWithSteps;

import java.util.HashMap;
import java.util.List;


public class WorkRepository {
    private WorkDao workDao;
    private StepDao stepDao;
    private LiveData<List<Work>> allWorks;
    private LiveData<List<Work>>  mFirst;

    public WorkRepository(Application application) {
        AppDB database = AppDB.getInstance(application);
        workDao = database.workDao();
        stepDao = database.stepDao();
        allWorks = workDao.getAllWorks();
        mFirst = workDao.getLast();

    }

    public void insert(Work work,List<Step> Steps) {
         HashMap WWS = new HashMap();
         WWS.put("work",work);
         WWS.put("steps",Steps);
        new InsertWorkAsyncTask(workDao,stepDao).execute(WWS);
    }

    public void update(Work work) {
        new UpdateWorkAsyncTask(workDao).execute(work);
    }

    public void delete(Work work) {
        new DeleteWorkAsyncTask(workDao).execute(work);
    }

    public void deleteAllWorks() {
        new DeleteAllWorksAsyncTask(workDao).execute();
    }

    public LiveData<List<Work>> getAllWorks() {
        return allWorks;
    }

    public LiveData<List<Work>> getLast(){
        return mFirst;
    }

    private static class InsertWorkAsyncTask extends AsyncTask<HashMap, Void, Void> {
        private static final String TAG = "InsertWorkAsyncTask";
        private WorkDao workDao;
        private StepDao stepDao;

        private InsertWorkAsyncTask(WorkDao workDao,StepDao stepDao) {
            this.workDao = workDao;
            this.stepDao = stepDao;
        }

        @Override
        protected Void doInBackground
                (HashMap... hashMap) {
            Work work = (Work) hashMap[0].get("work") ;
            List<Step> steps = (List<Step>) hashMap[0].get("steps");
            long id = workDao.insert(work);
            if(steps != null && steps.size() > 0 )
            for (Step step : steps) {
                step.setOwner((int) id);
                stepDao.insert(step);
            }
            return null;
        }
    }

    private static class UpdateWorkAsyncTask extends AsyncTask<Work , Void, Void> {
        private WorkDao workDao;

        private UpdateWorkAsyncTask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDao.update(works[0]);
            return null;
        }
    }

    private static class DeleteWorkAsyncTask extends AsyncTask<Work, Void, Void> {
        private WorkDao workDao;

        private DeleteWorkAsyncTask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDao.delete(works[0]);
            return null;
        }
    }

    private static class DeleteAllWorksAsyncTask extends AsyncTask<Void, Void, Void> {
        private WorkDao workDao;

        private DeleteAllWorksAsyncTask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            workDao.deleteAllWorks();
            return null;
        }
    }
}