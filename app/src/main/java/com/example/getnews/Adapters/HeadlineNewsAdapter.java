package com.example.getnews.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.getnews.Model.ArticleModel;
import com.example.getnews.R;

import java.util.List;


public class HeadlineNewsAdapter extends RecyclerView.Adapter<HeadlineNewsAdapter.ViewHolder> {

    private final List<ArticleModel> mValues;
    private Context mContext;

    public HeadlineNewsAdapter(Context context, List<ArticleModel> items) {
        mContext = context;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.headline_adapter_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.language.setText(mValues.get(position).getTitle());

        Glide.with(mContext).load(mValues.get(position).getUrlToImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.avatar);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView  language;
        public final TextView mContentView;
        public final ImageView avatar;

        public ArticleModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            avatar = (ImageView) view.findViewById(R.id.avatar);
            language = (TextView) view.findViewById(R.id.language);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}

