package com.example.getnews.RoomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.getnews.HelperClasses.Constants;
import com.example.getnews.Model.HeadlineModel;

@Database(entities = {HeadlineModel.class}, version = 1, exportSchema = false)
public abstract class HeadlinesDB extends RoomDatabase {
    private static final String DB_NAME = Constants.DATABASE_NAME;
    private static HeadlinesDB instance;
    public static synchronized HeadlinesDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), HeadlinesDB.class, DB_NAME)
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
            new PopulateDBAsyncTask(instance).execute();
        }


    };

    public abstract DaoRepositiory repositoryAllDao();


    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private DaoRepositiory repositoryAllDao;


        public PopulateDBAsyncTask(HeadlinesDB instance) {
            this.repositoryAllDao = instance.repositoryAllDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
