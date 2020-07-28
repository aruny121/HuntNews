package com.example.getnews.ViewModel.ViewModelHelpers;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.getnews.Model.ArticleModel;
import com.example.getnews.RoomDB.DaoRepositiory;
import com.example.getnews.RoomDB.HeadlinesDB;

import java.util.ArrayList;
import java.util.List;

public class HeadLinesRoomDBRespository {
    private DaoRepositiory repositoryDao;
    private LiveData<List<ArticleModel>> allArticle;
    int repo_parent_id = 0;

    public HeadLinesRoomDBRespository(Application application) {
        HeadlinesDB db = HeadlinesDB.getInstance(application);
        repositoryDao = db.repositoryAllDao();
        allArticle = repositoryDao.getHeadlinesAll();
    }

    public void insertRepository(List<ArticleModel> articleModels) {
        new InsertArticleAsyncTask(repositoryDao).execute(articleModels);
    }


    public LiveData<List<ArticleModel>> getAllArticle() {
        return allArticle;
    }



    private static class InsertArticleAsyncTask extends AsyncTask<List<ArticleModel>, Void, Void> {
        private DaoRepositiory repositoryAllDao;

        private InsertArticleAsyncTask(DaoRepositiory repositoryAllDao) {
            this.repositoryAllDao = repositoryAllDao;
        }

        @Override
        protected Void doInBackground(List<ArticleModel>... repository) {
            //clear prevous old data
            repositoryAllDao.deleteAllRepository();
            repositoryAllDao.insertArticle(repository[0]);
            return null;
        }
    }

}
