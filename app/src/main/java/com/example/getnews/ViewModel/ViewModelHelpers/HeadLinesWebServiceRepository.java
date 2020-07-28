package com.example.getnews.ViewModel.ViewModelHelpers;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.getnews.HelperClasses.Constants;
import com.example.getnews.Model.ArticleModel;
import com.example.getnews.Model.ResponseModel;
import com.example.getnews.Networking.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.getnews.HelperClasses.Constants.API_SUCESS_RESPONSE;

public class HeadLinesWebServiceRepository {
    Application application;

    public HeadLinesWebServiceRepository(Application application) {
        this.application = application;
    }

    List<ArticleModel> webserviceArticleResponseList = new ArrayList<>();

    public LiveData<List<ArticleModel>> providesWebService() {
        final MutableLiveData<List<ArticleModel>> data = new MutableLiveData<>();
        try {
            ApiInterface service = getApi();
            Call<ResponseModel> call = service.getHeadlinesApi(Constants.API_COUNTRY,Constants.API_CATERGORY,Constants.API_NEWS_API_KEY);
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    if(response != null && response.body().getArticles()!=null) {
                        if(response.body().getStatus().equalsIgnoreCase(API_SUCESS_RESPONSE))
                        {
                            webserviceArticleResponseList = response.body().getArticles();
                            System.out.print("response is" + webserviceArticleResponseList);
                            Log.d("Repository", "Response::::" + response.body());
                            HeadLinesRoomDBRespository postRoomDBRepository = new HeadLinesRoomDBRespository(application);
                            postRoomDBRepository.insertRepository(webserviceArticleResponseList);
                            data.setValue(webserviceArticleResponseList);
                        }

                    } else {

                        Log.d("Repository", "Response::::null");
                    }
                }
                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Log.d("Repository", t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

/*
RETROFIT INSTANCE
 */
    private ApiInterface getApi(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(ApiInterface.API_BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(ApiInterface.class);
    }
}
