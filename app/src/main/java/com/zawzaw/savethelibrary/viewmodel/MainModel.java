package com.zawzaw.savethelibrary.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

import com.zawzaw.savethelibrary.event.eventclass.Events;
import com.zawzaw.savethelibrary.event.main.OttoBus;
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.model.gson.GsonBooks;
import com.zawzaw.savethelibrary.model.gson.GsonNews;
import com.zawzaw.savethelibrary.model.gson.GsonPdf;
import com.zawzaw.savethelibrary.model.gson.GsonPdfs;
import com.zawzaw.savethelibrary.network.BaseApi;
import com.zawzaw.savethelibrary.network.services.MainService;
import com.zawzaw.savethelibrary.utils.Const;

/**
 * Created by zawzaw on 01/01/18.
 */

public class MainModel extends ViewModel {

    public MutableLiveData<GsonNews> latestNews;
    public MutableLiveData<List<GsonBook>> latestReviews;
    public MutableLiveData<List<GsonPdf>> latestPdfs;

    public LiveData<List<GsonPdf>> getLatestPdfs(int page) {
        if (latestPdfs == null) {
            latestPdfs = new MutableLiveData<>();
            loadLatestPdfs(page);
        }
        return latestPdfs;
    }

    public LiveData<List<GsonBook>> getLatestBookReviews(int page) {
        if(latestReviews == null) {
            latestReviews = new MutableLiveData<>();
            loadLatestBookReviews(page);
        }
        return latestReviews;
    }

    public LiveData<GsonNews> getLatestNews(int page) {
        if (latestNews == null) {
            latestNews = new MutableLiveData<>();
            loadLatestNews(page);
        }
        return latestNews;
    }

    private void loadLatestPdfs(int page) {
        Call<GsonPdfs> call = BaseApi.createService(MainService.class).getPdfs(Const.INJECTED_STRING, page);
        call.enqueue(new Callback<GsonPdfs>() {
            @Override
            public void onResponse(Call<GsonPdfs> call, Response<GsonPdfs> response) {
                latestPdfs.setValue(response.body().getPdfList());
            }

            @Override
            public void onFailure(Call<GsonPdfs> call, Throwable t) {
                Events.NoInternetConection noInternetConection = new Events.NoInternetConection("no");
                OttoBus.getBus().post(noInternetConection);
            }
        });
    }

    private void loadLatestBookReviews(int page) {
        Call<GsonBooks> call = BaseApi.createService(MainService.class).getLatestBookReviews(Const.INJECTED_STRING, page);
        call.enqueue(new Callback<GsonBooks>() {
            @Override
            public void onResponse(Call<GsonBooks> call, Response<GsonBooks> response) {
                latestReviews.setValue(response.body().getBooks());
            }

            @Override
            public void onFailure(Call<GsonBooks> call, Throwable t) {
                Events.NoInternetConection noInternetConection = new Events.NoInternetConection("no");
                OttoBus.getBus().post(noInternetConection);

            }
        });
    }

    private void loadLatestNews(int page) {
        Call<GsonNews> call = BaseApi.createService(MainService.class).getNewsForMainSlider(Const.INJECTED_STRING, page);
        call.enqueue(new Callback<GsonNews>() {
            @Override
            public void onResponse(Call<GsonNews> call, Response<GsonNews> response) {
                latestNews.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GsonNews> call, Throwable t) {
                // Make some error for server response error
            }
        });
    }

}
