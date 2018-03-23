package com.zawzaw.savethelibrary.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.zawzaw.savethelibrary.utils.Const

/**
 * Created by zawzaw on 31/12/17.
 */

class BaseApi {
    companion object {

        private val builder = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient())

        fun <S> createService(serviceClass: Class<S>): S {
            val retrofit = builder.build()
            return retrofit.create(serviceClass)
        }
    }

}
