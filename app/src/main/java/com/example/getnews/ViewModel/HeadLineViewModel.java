package com.example.getnews.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.getnews.Model.HeadlineModel;

import java.util.List;

/**
 * Desc - View model class for headlines screen
 * author - Arun yadav
 * email - aruny121@gmail.com
 */
public class HeadLineViewModel extends AndroidViewModel {


    private LiveData<List<HeadlineModel>> allHeadline; //live data of headline
    private HeadLineViewModelRepository headLineViewModelRepository;


    public HeadLineViewModel(@NonNull Application application) {
        super(application);
        allHeadline = headLineViewModelRepository.getAllHeadlines();

    }

    //function return the live data of headlines
    public LiveData<List<HeadlineModel>> getAllHeadline() {
        return allHeadline;
    }
}
