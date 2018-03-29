package com.stockx.redditboi;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {
    private static final String TAG = RestApiClient.class.getSimpleName();

    private ApiService mApiService;

    public RestApiClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(App.getInstance().getUrlBaseApi())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApiService = mRetrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return mApiService;
    }
}
