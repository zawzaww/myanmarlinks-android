package com.zawzaw.savethelibrary.network.services

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.HashMap
import com.zawzaw.savethelibrary.model.gson.GsonAuthors
import com.zawzaw.savethelibrary.model.gson.GsonBook
import com.zawzaw.savethelibrary.model.gson.GsonBooks
import com.zawzaw.savethelibrary.model.gson.GsonNew
import com.zawzaw.savethelibrary.model.gson.GsonNews
import com.zawzaw.savethelibrary.model.gson.GsonPdfs
import com.zawzaw.savethelibrary.model.gson.GsonQuote

/**
 * Created by zawzaw on 31/12/17.
 */

interface MainService {

    @GET("get-news")
    fun getNewsForMainSlider(@Header("Authorization") authorization: String, @Query("page") page: Int): Call<GsonNews>

    @GET("get-books")
    fun getLatestBookReviews(@Header("Authorization") authorization: String, @Query("page") page: Int): Call<GsonBooks>

    @GET("get-pdfs")
    fun getPdfs(@Header("Authorization") authorization: String, @Query("page") page: Int): Call<GsonPdfs>

    @GET("get-random-quote")
    fun getRandomQuote(@Header("Authorization") authorization: String): Call<HashMap<String, GsonQuote>>

    @GET("get-book-detail/{id}")
    fun getBookDetail(@Header("Authorization") authorization: String, @Path("id") id: Int): Call<GsonBook>

    @GET("get-new-detail/{id}")
    fun getNewDetail(@Header("Authorization") authorization: String, @Path("id") id: Int): Call<GsonNew>

    @GET("get-book-authors")
    fun getAuthorList(@Header("Authorization") authorization: String, @Query("page") page: Int): Call<GsonAuthors>

}
