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
import com.zawzaw.savethelibrary.model.gson.GsonBook
import com.zawzaw.savethelibrary.network.BaseApi
import com.zawzaw.savethelibrary.network.services.MainService
import com.zawzaw.savethelibrary.utils.Const

/**
 * Created by zawzaw on 11/01/18.
 */

class ReviewDetailModel : ViewModel() {

    private var gsonBook: MutableLiveData<GsonBook>? = null
    var review_id: Int = 0

    val books: LiveData<GsonBook>
        get() {
            if (gsonBook == null) {
                gsonBook = MutableLiveData()
                loadReview(review_id)
            }
            return gsonBook
        }

    private fun loadReview(review_id: Int) {
        val gsonBookCall = BaseApi.createService(MainService::class.java).getBookDetail(Const.INJECTED_STRING, review_id)
        gsonBookCall.enqueue(object : Callback<GsonBook> {
            override fun onResponse(call: Call<GsonBook>, response: Response<GsonBook>) {
                Log.i("API", "API CALLED")
                gsonBook!!.value = response.body()

            }

            override fun onFailure(call: Call<GsonBook>, t: Throwable) {
                val noInternetConection = Events.NoInternetConection("no")
                OttoBus.getBus().post(noInternetConection)
            }
        })
    }

}
