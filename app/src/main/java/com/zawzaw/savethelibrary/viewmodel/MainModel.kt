package com.zawzaw.savethelibrary.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

import com.zawzaw.savethelibrary.event.eventclass.Events
import com.zawzaw.savethelibrary.event.main.OttoBus
import com.zawzaw.savethelibrary.model.gson.GsonBook
import com.zawzaw.savethelibrary.model.gson.GsonBooks
import com.zawzaw.savethelibrary.model.gson.GsonNews
import com.zawzaw.savethelibrary.model.gson.GsonPdf
import com.zawzaw.savethelibrary.model.gson.GsonPdfs
import com.zawzaw.savethelibrary.model.gson.GsonQuote
import com.zawzaw.savethelibrary.network.BaseApi
import com.zawzaw.savethelibrary.network.services.MainService
import com.zawzaw.savethelibrary.utils.Const

/**
 * Created by zawzaw on 01/01/18.
 */

class MainModel : ViewModel() {

    private var latestNews: MutableLiveData<GsonNews>? = null
    private var latestReviews: MutableLiveData<List<GsonBook>>? = null
    private var latestPdfs: MutableLiveData<List<GsonPdf>>? = null
    private var randomQuote: MutableLiveData<HashMap<String, GsonQuote>>? = null

    fun getRandomQuote(): MutableLiveData<HashMap<String, GsonQuote>> {
        if (randomQuote == null) {
            randomQuote = MutableLiveData()

            loadRandomQuote()
        }
        return randomQuote
    }

    fun getLatestPdfs(page: Int): LiveData<List<GsonPdf>> {
        if (latestPdfs == null) {
            latestPdfs = MutableLiveData()
            loadLatestPdfs(page)
        }
        return latestPdfs
    }

    fun getLatestBookReviews(page: Int): LiveData<List<GsonBook>> {
        if (latestReviews == null) {
            latestReviews = MutableLiveData()
            loadLatestBookReviews(page)
        }
        return latestReviews
    }

    fun getLatestNews(page: Int): LiveData<GsonNews> {
        if (latestNews == null) {
            latestNews = MutableLiveData()
            loadLatestNews(page)
        }
        return latestNews
    }

    private fun loadRandomQuote() {
        val call = BaseApi.createService(MainService::class.java).getRandomQuote(Const.INJECTED_STRING)
        call.enqueue(object : Callback<HashMap<String, GsonQuote>> {
            override fun onResponse(call: Call<HashMap<String, GsonQuote>>, response: Response<HashMap<String, GsonQuote>>) {
                randomQuote!!.value = response.body()
            }

            override fun onFailure(call: Call<HashMap<String, GsonQuote>>, t: Throwable) {
                // Make some error for sever response error
            }
        })

    }

    private fun loadLatestPdfs(page: Int) {
        val call = BaseApi.createService(MainService::class.java).getPdfs(Const.INJECTED_STRING, page)
        call.enqueue(object : Callback<GsonPdfs> {
            override fun onResponse(call: Call<GsonPdfs>, response: Response<GsonPdfs>) {
                latestPdfs!!.value = response.body()!!.pdfList
            }

            override fun onFailure(call: Call<GsonPdfs>, t: Throwable) {
                // Make some error for sever response error
            }
        })
    }

    private fun loadLatestBookReviews(page: Int) {
        val call = BaseApi.createService(MainService::class.java).getLatestBookReviews(Const.INJECTED_STRING, page)
        call.enqueue(object : Callback<GsonBooks> {
            override fun onResponse(call: Call<GsonBooks>, response: Response<GsonBooks>) {
                latestReviews!!.value = response.body()!!.books
            }

            override fun onFailure(call: Call<GsonBooks>, t: Throwable) {
                // Make some error for sever response error
            }
        })
    }

    private fun loadLatestNews(page: Int) {
        val call = BaseApi.createService(MainService::class.java).getNewsForMainSlider(Const.INJECTED_STRING, page)
        call.enqueue(object : Callback<GsonNews> {
            override fun onResponse(call: Call<GsonNews>, response: Response<GsonNews>) {
                latestNews!!.value = response.body()
            }

            override fun onFailure(call: Call<GsonNews>, t: Throwable) {
                // Make some error for sever response error
                val noInternetConection = Events.NoInternetConection("no")
                OttoBus.getBus().post(noInternetConection)
            }
        })
    }

}
