package com.example.getnews.UI.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.example.getnews.HelperClasses.MyReceiver;
import com.example.getnews.R;
import com.example.getnews.UI.Adapter.HeadlineNewsAdapter;
import com.example.getnews.Model.ArticleModel;
import com.example.getnews.ViewModel.HeadLineViewModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Desc - Main activity class for Headlines list
 * author - Arun yadav
 * email - aruny121@gmail.com
 */
public class Headlines extends AppCompatActivity {
    private HeadLineViewModel viewModel;
    private RecyclerView recyclerView;
    private BroadcastReceiver MyReceiver = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        MyReceiver = new MyReceiver();

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
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(MyReceiver);

    }
}