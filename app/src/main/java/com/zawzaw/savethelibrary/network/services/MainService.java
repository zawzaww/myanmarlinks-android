package com.zawzaw.savethelibrary.network.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import java.util.HashMap;

import com.zawzaw.savethelibrary.model.gson.GsonBook;
import com.zawzaw.savethelibrary.model.gson.GsonBooks;
import com.zawzaw.savethelibrary.model.gson.GsonNew;
import com.zawzaw.savethelibrary.model.gson.GsonNews;
import com.zawzaw.savethelibrary.model.gson.GsonPdfs;
import com.zawzaw.savethelibrary.model.gson.GsonQuote;

/**
 * Created by zawzaw on 31/12/17.
 */

public interface MainService {

    @GET("get-news")
    Call<GsonNews> getNewsForMainSlider(@Header("Authorization") String authorization, @Query("page") int page);

    @GET("get-books")
    Call<GsonBooks> getLatestBookReviews(@Header("Authorization") String authorization, @Query("page") int page);

    @GET("get-pdfs")
    Call<GsonPdfs> getPdfs(@Header("Authorization") String authorization, @Query("page") int page);

    @GET("get-random-quote")
    Call<HashMap<String, GsonQuote>> getRandomQuote(@Header("Authorization") String authorization);

    @GET("get-book-detail/{id}")
    Call<GsonBook> getBookDetail(@Header("Authorization") String authorization, @Path("id") int id);

    @GET("get-new-detail/{id}")
    Call<GsonNew> getNewDetail(@Header("Authorization") String authorization, @Path("id") int id);
}
