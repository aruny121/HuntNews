package com.example.getnews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.getnews.Model.HeadlineModel;
import com.example.getnews.ViewModel.HeadLineViewModel;

import java.util.List;

/**
 * Desc - Main activity class for Headlines list
 * author - Arun yadav
 * email - aruny121@gmail.com
 */
public class Headlines extends AppCompatActivity {
    private HeadLineViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHeadlinesData();
    }


    /*
    function to get the api data i.e headline data
    @Param - NOT REQUIRED ANY
     */
    public void  getHeadlinesData(){
        viewModel = ViewModelProviders.of(this).get(HeadLineViewModel.class);
        viewModel.getAllHeadline().observe(this, new Observer<List<HeadlineModel>>() {
            @Override
            public void onChanged(@Nullable List<HeadlineModel> developerModels) {
                System.out.print("****" + developerModels.toString());
            }
        });
    }
}