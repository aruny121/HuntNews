package com.example.getnews.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.getnews.UI.Activity.HeadlinesDetail;
import com.example.getnews.HelperClasses.Constants;
import com.example.getnews.HelperClasses.Util;
import com.example.getnews.Model.ArticleModel;
import com.example.getnews.R;

import java.util.List;


public class HeadlineNewsAdapter extends RecyclerView.Adapter<HeadlineNewsAdapter.ViewHolder> {

    private final List<ArticleModel> mValues;
    private Context mContext;
    Util util;

    public HeadlineNewsAdapter(Context context, List<ArticleModel> items) {
        mContext = context;
        mValues = items;
        util = new Util();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.headline_adapter_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.language.setText(util.NullcheckforResponse(mValues.get(position).getTitle()));
        holder.author.setText(util.NullcheckforResponse(mValues.get(position).getAuthor()));
        holder.date.setText(util.parsedate(mValues.get(position).getPublishedAt()));
        if(mValues.get(position).getUrlToImage()!=null) {
            Glide.with(mContext).load(mValues.get(position).getUrlToImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.avatar);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIntent(mValues.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView  language, author, date;
        public final TextView mContentView;
        public final ImageView avatar;

        public ArticleModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            language = (TextView) view.findViewById(R.id.language);
            author = (TextView) view.findViewById(R.id.author);
            date = (TextView) view.findViewById(R.id.date);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


    public void startIntent(ArticleModel articleModel)
    {
        Intent i = new Intent(mContext, HeadlinesDetail.class);
        i.putExtra(Constants.INTENT_NEWS_DETAILS_TAG, articleModel);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //add this line
        mContext.startActivity(i);
    }
}

