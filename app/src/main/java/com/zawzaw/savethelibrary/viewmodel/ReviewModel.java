package com.zawzaw.savethelibrary.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.zawzaw.savethelibrary.event.eventclass.Events;
import com.zawzaw.savethelibrary.event.main.OttoBus;
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.model.gson.GsonBooks;
import com.zawzaw.savethelibrary.network.BaseApi;
import com.zawzaw.savethelibrary.network.services.MainService;
import com.zawzaw.savethelibrary.utils.Const;

/**
 * Created by zawzaw on 09/01/18.
 */

public class ReviewModel extends ViewModel {

    private MutableLiveData<List<GsonBook>> latestReviews;

    public LiveData<List<GsonBook>> getLatestBookReviews (int page) {
        if (latestReviews == null) {
            latestReviews = new MutableLiveData<>();
        }
        loadLatestBookReviews(page);
        return latestReviews;
    }


    private void loadLatestBookReviews (int page) {
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

}
