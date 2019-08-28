package com.gg.reactionlight_0.dataBase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.gg.reactionlight_0.dataBase.dao.LogDao;
import com.gg.reactionlight_0.dataBase.dao.ModeDao;
import com.gg.reactionlight_0.dataBase.dao.PlayerDao;
import com.gg.reactionlight_0.dataBase.dao.StepDao;
import com.gg.reactionlight_0.dataBase.dao.WorkDao;
import com.gg.reactionlight_0.dataBase.dao.WorkWithStepsDao;
import com.gg.reactionlight_0.dataBase.entity.Log;
import com.gg.reactionlight_0.dataBase.entity.Mode;
import com.gg.reactionlight_0.dataBase.entity.Player;
import com.gg.reactionlight_0.dataBase.entity.Step;
import com.gg.reactionlight_0.dataBase.entity.Work;

@Database(entities = {Work.class,Log.class,Player.class,Step.class,Mode.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;

    public abstract WorkDao workDao();

    public abstract StepDao stepDao();

    public abstract PlayerDao playerDao();

    public abstract LogDao logDao();

    public abstract ModeDao modeDao();

    public abstract WorkWithStepsDao workWithStepsDao();

    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDB.class, "ReactionLightDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private WorkDao workDao;
        private StepDao stepDao;
        private ModeDao modeDao;

        private PopulateDbAsyncTask(AppDB db) {
            workDao = db.workDao();
            stepDao = db.stepDao();
            modeDao = db.modeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            stepDao.insert(new Step(1,2.0, 2.0,0,"1","asdasd"));
            stepDao.insert(new Step(1,2.0, 2.0,1,"2","asdasd"));
            stepDao.insert(new Step(1,2.0, 2.0,2,"1","asdasd"));
            stepDao.insert(new Step(2,2.0, 2.0,0,"3","asdasd"));
            workDao.insert(new Work("Actividad 1", "Description 1",true,false,false));
            workDao.insert(new Work("Actividad 2", "Description 2",true,false,false));
            workDao.insert(new Work("Actividad 3", "Description 3",false,false,false));
            modeDao.insert(new Mode(0,"Automatico", "Funciona por si solo"));
            modeDao.insert(new Mode(0,"Manual", "No funciona por si solo"));
            modeDao.insert(new Mode(1,"Random", "Funciona por si solo"));
            modeDao.insert(new Mode(1,"Secuencia", "Funciona por si solo"));

            return null;
        }
    }

}
