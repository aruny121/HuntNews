package com.example.getnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.ParcelUuid;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.getnews.HelperClasses.Constants;
import com.example.getnews.HelperClasses.Util;
import com.example.getnews.Model.ArticleModel;

public class HeadlinesDetail extends  Util {
    private ArticleModel articleModel;
    private ImageView imageView;
    private TextView headline, author, date, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines_detail);
        setUI();
        articleModel = (ArticleModel) getIntent().getSerializableExtra(Constants.INTENT_NEWS_DETAILS_TAG);
            if (articleModel == null)
            {
                Toast.makeText(getApplicationContext(),Constants.ERROR,Toast.LENGTH_LONG).show();
                finish();
                return;
            }
        Log.d("Repository", "Response::::" + articleModel.getDescription());

            setValueUI(articleModel);
    }



    public void setUI(){
        imageView = findViewById(R.id.avatar);
        headline = findViewById(R.id.heading);
        author = findViewById(R.id.author);
        date = findViewById(R.id.date);
        description = findViewById(R.id.description);

    }

    public  void setValueUI(ArticleModel articleModel){
        headline.setText(NullcheckforResponse(articleModel.getTitle()));
        author.setText(NullcheckforResponse(articleModel.getAuthor()));
        date.setText(parsedate(articleModel.getPublishedAt()));
        if(articleModel.getUrlToImage()!=null) {
            Glide.with(this).load(articleModel.getUrlToImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }

        description.setText(NullcheckforResponse(articleModel.getDescription()));
    }
}