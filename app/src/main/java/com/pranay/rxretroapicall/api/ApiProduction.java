package com.pranay.rxretroapicall.api;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Pranay.
 *
 * API production class to get Retrofit Rest API adapter
 */

public class ApiProduction {
    private static final String BASE_URL = "https://api.stackexchange.com/2.2/";
    private Context context;

    private ApiProduction(Context context) {
        this.context = context;
    }

    public static ApiProduction getInstance(Context context) {
        return new ApiProduction(context);
    }

    private Retrofit provideRestAdapter() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpProduction.getOkHttpClient(context, true))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <S> S provideService(Class<S> serviceClass) {
        return provideRestAdapter().create(serviceClass);
    }
}
