package com.zawzaw.savethelibrary.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.zawzaw.savethelibrary.utils.Const;

/**
 * Created by zawzaw on 31/12/17.
 */

public class BaseApi {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient());

    public BaseApi() {

    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

}
