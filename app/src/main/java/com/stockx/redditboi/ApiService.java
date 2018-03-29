package com.stockx.redditboi;

import com.stockx.redditboi.model.RedditWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by oliver on 8/23/2016
 */
public interface ApiService {

    @GET("r/{subreddit_name}/.json")
    Call<RedditWrapper> getSearch(@Path("subreddit_name") String subredditName);
}
