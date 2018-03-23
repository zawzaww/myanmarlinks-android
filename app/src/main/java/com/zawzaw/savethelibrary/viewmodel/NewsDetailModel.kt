package com.zawzaw.savethelibrary.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import com.zawzaw.savethelibrary.event.eventclass.Events
import com.zawzaw.savethelibrary.event.main.OttoBus
import com.zawzaw.savethelibrary.model.gson.GsonNew
import com.zawzaw.savethelibrary.network.BaseApi
import com.zawzaw.savethelibrary.network.services.MainService
import com.zawzaw.savethelibrary.utils.Const

/**
 * Created by zawzaw on 12/01/18.
 */

class NewsDetailModel : ViewModel() {

    var post_id: Int = 0

    private var gsonNew: MutableLiveData<GsonNew>? = null

    val news: LiveData<GsonNew>
        get() {
            if (gsonNew == null) {
                gsonNew = MutableLiveData()
                loadNews(post_id)
            }
            return gsonNew
        }

    private fun loadNews(post_id: Int) {
        val call = BaseApi.createService(MainService::class.java).getNewDetail(Const.INJECTED_STRING, post_id)
        call.enqueue(object : Callback<GsonNew> {
            override fun onResponse(call: Call<GsonNew>, response: Response<GsonNew>) {
                Log.i("API", "API CALLED")
                gsonNew!!.value = response.body()
            }

            override fun onFailure(call: Call<GsonNew>, t: Throwable) {
                val noInternetConection = Events.NoInternetConection("no")
                OttoBus.getBus().post(noInternetConection)
            }
        })
    }

}
