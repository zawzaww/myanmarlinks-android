package com.zawzaw.savethelibrary.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.zawzaw.savethelibrary.model.gson.GsonNews;
import com.zawzaw.savethelibrary.network.BaseApi;
import com.zawzaw.savethelibrary.network.services.MainService;
import com.zawzaw.savethelibrary.utils.Const;

/**
 * Created by zawzaw on 25/12/17.
 */

public class NewsModel extends ViewModel
{
    public MutableLiveData<GsonNews> latestNews;

    public LiveData<GsonNews> getLatestNews(int page)
    {
        if (latestNews == null)
        {
           latestNews = new MutableLiveData<>();
           loadLatestNews(page);
        }

        return  latestNews;
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
                latestNews.setValue(null);
            }
        });

    }

}
