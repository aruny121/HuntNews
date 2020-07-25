package com.example.getnews.ViewModel;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.getnews.Model.HeadlineModel;
import com.example.getnews.RoomDB.DaoRepositiory;
import com.example.getnews.RoomDB.HeadlinesDB;

import java.util.List;

public class HeadLineViewModelRepository {

    private LiveData<List<HeadlineModel>> allHeadlines;
    private DaoRepositiory HeadlinesAllDao;


    public HeadLineViewModelRepository(Application application) {
        HeadlinesDB db = HeadlinesDB.getInstance(application);
        HeadlinesAllDao = db.repositoryAllDao();
        allHeadlines = HeadlinesAllDao.getHeadlinesAll();
    }


    public LiveData<List<HeadlineModel>> getAllHeadlines() {
        return allHeadlines;
    }
}
