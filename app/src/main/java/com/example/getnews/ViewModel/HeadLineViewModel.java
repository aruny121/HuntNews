package com.example.getnews.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.getnews.Model.HeadlineModel;
import com.example.getnews.ViewModel.ViewModelHelpers.HeadLinesRoomDBRespository;
import com.example.getnews.ViewModel.ViewModelHelpers.HeadLinesWebServiceRepository;

import java.util.List;

/**
 * Desc - View model class for headlines screen
 * author - Arun yadav
 * email - aruny121@gmail.com
 */
public class HeadLineViewModel extends AndroidViewModel {


    private LiveData<List<HeadlineModel>> allHeadline; //live data of headline
    private HeadLinesWebServiceRepository headLinesWebServiceRepository;
    private HeadLinesRoomDBRespository headLinesRoomDBRespository;
    private LiveData<List<HeadlineModel>> retroObservable;


    public HeadLineViewModel(@NonNull Application application) {
        super(application);
        headLinesWebServiceRepository = new HeadLinesWebServiceRepository(application);
        headLinesRoomDBRespository = new HeadLinesRoomDBRespository(application);



    }

    //function return the live data of headlines
    public LiveData<List<HeadlineModel>> getAllHeadline() {
        return allHeadline;
    }


}
