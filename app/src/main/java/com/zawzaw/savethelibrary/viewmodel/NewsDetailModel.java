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
import com.zawzaw.savethelibrary.model.gson.GsonNew;
import com.zawzaw.savethelibrary.network.BaseApi;
import com.zawzaw.savethelibrary.network.services.MainService;
import com.zawzaw.savethelibrary.utils.Const;

/**
 * Created by zawzaw on 12/01/18.
 */

public class NewsDetailModel extends ViewModel {

    private int post_id;

    private MutableLiveData<GsonNew> gsonNew;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public LiveData<GsonNew> getNews() {
        if (gsonNew == null) {
            gsonNew = new MutableLiveData<>();
            loadNews(getPost_id());
        }
        return gsonNew;
    }

    private void loadNews(int post_id) {
        Call<GsonNew> call = BaseApi.createService(MainService.class).getNewDetail(Const.INJECTED_STRING, post_id);
        call.enqueue(new Callback<GsonNew>() {
            @Override
            public void onResponse(Call<GsonNew> call, Response<GsonNew> response) {
                Log.i("API", "API CALLED");
                gsonNew.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GsonNew> call, Throwable t) {
                Events.NoInternetConection noInternetConection = new Events.NoInternetConection("no");
                OttoBus.getBus().post(noInternetConection);
            }
        });
    }

}
