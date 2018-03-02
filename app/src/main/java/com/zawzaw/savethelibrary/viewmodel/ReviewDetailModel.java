package com.zawzaw.savethelibrary.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.zawzaw.savethelibrary.event.eventclass.Events;
import com.zawzaw.savethelibrary.event.main.OttoBus;
import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.network.BaseApi;
import com.zawzaw.savethelibrary.network.services.MainService;
import com.zawzaw.savethelibrary.utils.Const;

/**
 * Created by zawzaw on 11/01/18.
 */

public class ReviewDetailModel extends ViewModel {

    private MutableLiveData<GsonBook> gsonBook;
    private int review_id;

    public LiveData<GsonBook> getBooks() {
        if (gsonBook == null) {
            gsonBook = new MutableLiveData<>();
            loadReview(getReview_id());
        }
        return gsonBook;
    }

    private void loadReview(int review_id) {
        Call<GsonBook> gsonBookCall = BaseApi.createService(MainService.class).getBookDetail(Const.INJECTED_STRING, review_id);
        gsonBookCall.enqueue(new Callback<GsonBook>() {
            @Override
            public void onResponse(Call<GsonBook> call, Response<GsonBook> response) {
                Log.i("API", "API CALLED");
                gsonBook.setValue(response.body());

            }

            @Override
            public void onFailure(Call<GsonBook> call, Throwable t) {
                Events.NoInternetConection noInternetConection = new Events.NoInternetConection("no");
                OttoBus.getBus().post(noInternetConection);
            }
        });
    }

    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }

    public int getReview_id() {
        return review_id;
    }

}
