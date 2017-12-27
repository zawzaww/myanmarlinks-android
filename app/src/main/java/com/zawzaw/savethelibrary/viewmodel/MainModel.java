package com.zawzaw.savethelibrary.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.model.gson.GsonBooks;
import com.zawzaw.savethelibrary.model.gson.GsonNews;
import com.zawzaw.savethelibrary.network.BaseApi;
import com.zawzaw.savethelibrary.network.services.MainService;
import com.zawzaw.savethelibrary.utils.Const;

/**
 * Created by zawzaw on 25/12/17.
 */

public class MainModel extends ViewModel
{
    public MutableLiveData<GsonNews> latestNews;

    public MutableLiveData<List<GsonBook>> latestReviews;

    public LiveData<List<GsonBook>> getLatestBookReviews(int page)
    {
        if (latestReviews == null)
        {
            latestReviews = new MutableLiveData<>();
            loadLatestBookReviews(page);
        }
        return latestReviews;
    }

    public LiveData<GsonNews> getLatestNews(int page)
    {
        if (latestNews == null)
        {
           latestNews = new MutableLiveData<>();
           loadLatestNews(page);
        }

        return  latestNews;
    }

    private void loadLatestBookReviews(int page)
    {
        Call<GsonBooks> call = BaseApi.createService(MainService.class).getLatestBookReviews(Const.INJECTED_STRING, page);
        call.enqueue(new Callback<GsonBooks>()
        {
            @Override
            public void onResponse(Call<GsonBooks> call, Response<GsonBooks> response)
            {
                latestReviews.setValue(response.body().getBooks());
            }

            @Override
            public void onFailure(Call<GsonBooks> call, Throwable t)
            {
                // Maker somer error for server error
            }
        });
    }

    private void loadLatestNews(int page)
    {
        Call<GsonNews> call = BaseApi.createService(MainService.class).getNewsForMainSlider(Const.INJECTED_STRING, page);
        call.enqueue(new Callback<GsonNews>() {
            @Override
            public void onResponse(Call<GsonNews> call, Response<GsonNews> response)
            {
                latestNews.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GsonNews> call, Throwable t)
            {
                // Make somer error for server error
            }
        });

    }

}
