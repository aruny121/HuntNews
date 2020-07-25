package com.example.getnews.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.getnews.HelperClasses.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Desc - class is for headlines  model getter and setter
 * author - Arun yadav
 * email - aruny121@gmail.com
 */

@Entity(tableName = Constants.DATABASE_HEADLINES_TABLE_NAME)
public class HeadlineModel  {
    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    @PrimaryKey(autoGenerate = true)
    private int parentid;

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<ArticleModel> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModel> articles) {
        this.articles = articles;
    }
}