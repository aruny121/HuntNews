package com.example.getnews.Networking;

import com.example.getnews.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Desc - interface which contains fucntion in order to interact with param with Retrofit Api .
 * author - Arun yadav
 * email - aruny121@gmail.com
 */


public interface ApiInterface {
    public static  final String API_BASE_URL = "https://newsapi.org/";

    @GET("v2/top-headlines")
    Call<ResponseModel> getHeadlinesApi(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey);
}

