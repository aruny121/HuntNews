package com.example.getnews.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.getnews.Model.ArticleModel;
import com.example.getnews.ViewModel.ViewModelHelpers.HeadLinesRoomDBRespository;
import com.example.getnews.ViewModel.ViewModelHelpers.HeadLinesWebServiceRepository;

import java.util.List;

/**
 * Desc - View model class for headlines screen
 * author - Arun yadav
 * email - aruny121@gmail.com
 */
public class HeadLineViewModel extends AndroidViewModel {


    private LiveData<List<ArticleModel>> allHeadline; //live data of headline
    private HeadLinesWebServiceRepository headLinesWebServiceRepository;
    private HeadLinesRoomDBRespository headLinesRoomDBRespository;
    private LiveData<List<ArticleModel>> retroObservable;


    public HeadLineViewModel(@NonNull Application application) {
        super(application);
        headLinesWebServiceRepository = new HeadLinesWebServiceRepository(application);
        headLinesRoomDBRespository = new HeadLinesRoomDBRespository(application);
        retroObservable = headLinesWebServiceRepository.providesWebService();
        allHeadline = headLinesRoomDBRespository.getAllArticle();

    }

    // function return the live data of headlines
    public LiveData<List<ArticleModel>> getAllHeadline() {
        return allHeadline;
    }
}
