package com.gg.reactionlight_0.dataBase.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.gg.reactionlight_0.dataBase.dao.LogDao;
import com.gg.reactionlight_0.dataBase.AppDB;
import com.gg.reactionlight_0.dataBase.entity.Log;

import java.util.List;


public class LogRepository {
    private LogDao logDao;
    private LiveData<List<Log>> allLogs;

    public LogRepository(Application application) {
        AppDB database = AppDB.getInstance(application);
        logDao = database.logDao();
        allLogs = logDao.getAllLogs();
    }

    public void insert(Log log) {
        new InsertLogAsyncTask(logDao).execute(log);
    }

    public void update(Log log) {
        new UpdateLogAsyncTask(logDao).execute(log);
    }

    public void delete(Log log) {
        new DeleteLogAsyncTask(logDao).execute(log);
    }

    public void deleteAllLogs() {
        new DeleteAllLogsAsyncTask(logDao).execute();
    }

    public LiveData<List<Log>> getAllLogs() {
        return allLogs;
    }

    private static class InsertLogAsyncTask extends AsyncTask<Log, Void, Void> {
        private LogDao logDao;

        private InsertLogAsyncTask(LogDao logDao) {
            this.logDao = logDao;
        }

        @Override
        protected Void doInBackground(Log... logs) {
            logDao.insert(logs[0]);
            return null;
        }
    }

    private static class UpdateLogAsyncTask extends AsyncTask<Log , Void, Void> {
        private LogDao logDao;

        private UpdateLogAsyncTask(LogDao logDao) {
            this.logDao = logDao;
        }

        @Override
        protected Void doInBackground(Log... logs) {
            logDao.update(logs[0]);
            return null;
        }
    }

    private static class DeleteLogAsyncTask extends AsyncTask<Log, Void, Void> {
        private LogDao logDao;

        private DeleteLogAsyncTask(LogDao logDao) {
            this.logDao = logDao;
        }

        @Override
        protected Void doInBackground(Log... logs) {
            logDao.delete(logs[0]);
            return null;
        }
    }

    private static class DeleteAllLogsAsyncTask extends AsyncTask<Void, Void, Void> {
        private LogDao logDao;

        private DeleteAllLogsAsyncTask(LogDao logDao) {
            this.logDao = logDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            logDao.deleteAllLogs();
            return null;
        }
    }
}