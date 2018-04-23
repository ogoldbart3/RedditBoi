package com.stockx.droiddit;

import android.app.Application;

/**
 * Created by harold on 3/19/18
 */

public class App extends Application {

    private static App mInstance;
    private static ApiClient mApiApiClient;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mApiApiClient = new ApiClient();
    }

    public static ApiClient getApiClient() {
        return mApiApiClient;
    }

    public String getUrlBaseApi() {
        return getString(R.string.base_url);
    }
}
