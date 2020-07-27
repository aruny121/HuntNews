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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
        holder.language.setText(NullcheckforResponse(mValues.get(position).getTitle()));
        holder.author.setText(NullcheckforResponse(mValues.get(position).getAuthor()));

        holder.date.setText(parsedate(mValues.get(position).getPublishedAt()));
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


    public  String parsedate(String date){
        if(date == null)
            return "Unknown";

        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy MM dd");
        Date dateformatter = null;
        try {
            dateformatter = inputFormatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return outputFormatter.format(dateformatter);

    }


    public  String  NullcheckforResponse(String response)
    {
        if (response == null)
        {
            return "Unknown";
        }
        else
        {
            return response;
        }
    }
}

