package com.zawzaw.savethelibrary.network.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import com.zawzaw.savethelibrary.model.gson.GsonBooks;
import com.zawzaw.savethelibrary.model.gson.GsonNews;

/**
 * Created by zawzaw on 25/12/17.
 */

public interface MainService
{
    @GET("get-news")
    Call<GsonNews> getNewsForMainSlider(@Header("Authorization") String authorization, @Query("page") int page);

    @GET("get-books")
    Call<GsonBooks> getLatestBookReviews(@Header("Authorization") String authorization, @Query("page") int page);

}
