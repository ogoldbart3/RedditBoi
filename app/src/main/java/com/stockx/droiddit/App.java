package com.stockx.droiddit;

import android.app.Application;

/**
 * Created by harold on 12/25/17
 */

public class App extends Application {

    private static App mInstance;
    private static RestApiClient mApiClient;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mApiClient = new RestApiClient();
    }

    public static RestApiClient getApiClient() {
        return mApiClient;
    }

    public String getUrlBaseApi() {
        return getString(R.string.base_url);
    }
}
