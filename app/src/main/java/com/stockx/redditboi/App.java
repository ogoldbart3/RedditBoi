package com.stockx.redditboi;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.util.Log;

/**
 * Created by oliver on 12/25/17
 */

public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

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

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public String getVersion() {
        String versionString = "";

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String versionText = packageInfo.versionName + "(" + String.valueOf(packageInfo.versionCode).substring(4, 7) + "." + String.valueOf(packageInfo.versionCode).substring(7) + ")";
//            versionString = getString(R.string.about_stockx_version, versionText);
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }

        return versionString;
    }

    public static RestApiClient getApiClient() {
        return mApiClient;
    }

//    public static void resetRestApiClient() {
//        mApiClient = new RestApiClient(App.getInstance());
//    }

    public String getUrlBaseApi() {
        return getString(R.string.base_url);
    }
}
