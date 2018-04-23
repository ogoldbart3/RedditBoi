package com.stockx.droiddit;

import com.stockx.droiddit.model.RedditWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by harold on 3/29/18
 */

public interface ApiService {

    @GET("/.json")
    Call<RedditWrapper> getHomepage();

    @GET("/r/{subreddit_name}/.json")
    Call<RedditWrapper> getSubreddit(@Path("subreddit_name") String subredditName);
}
