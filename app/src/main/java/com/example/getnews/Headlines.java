package com.example.getnews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.getnews.Adapters.HeadlineNewsAdapter;
import com.example.getnews.Model.ArticleModel;
import com.example.getnews.ViewModel.HeadLineViewModel;

import java.util.List;

/**
 * Desc - Main activity class for Headlines list
 * author - Arun yadav
 * email - aruny121@gmail.com
 */
public class Headlines extends AppCompatActivity {
    private HeadLineViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        getHeadlinesData();
    }

    /**
    function to get the api data i.e headline data
    @Param - NOT REQUIRED ANY
     **/
    public void  getHeadlinesData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel = ViewModelProviders.of(this).get(HeadLineViewModel.class);
        viewModel.getAllHeadline().observe(this, new Observer<List<ArticleModel>>() {
            @Override
            public void onChanged(@Nullable List<ArticleModel> articleModel) {
                if(articleModel != null)
                {
                    recyclerView.setAdapter(new HeadlineNewsAdapter(getApplication(), articleModel));

                    //connection for adapter
                }
            }
        });
    }
}