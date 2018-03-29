package com.stockx.redditboi.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.stockx.redditboi.App;
import com.stockx.redditboi.R;
import com.stockx.redditboi.model.RedditWrapper;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by oliver on 3/29/18
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Call<RedditWrapper> fetchSubredditCall;

    protected void openSubredditActivity() {
        Intent intent = new Intent(this, SubredditActivity.class);
        startActivity(intent);
    }

    protected void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    //

    protected void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    //

    protected void initiateGetHomepageCall(Callback<RedditWrapper> callback) {
        fetchSubredditCall = App.getApiClient().getApiService().getHomepage();
        fetchSubredditCall.enqueue(callback);
    }

    protected void initiateGetSubredditCall(String subredditName, Callback<RedditWrapper> callback) {
        fetchSubredditCall = App.getApiClient().getApiService().getSubreddit(subredditName);
        fetchSubredditCall.enqueue(callback);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_open_subreddit:
                openSubredditActivity();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (fetchSubredditCall != null) {
            fetchSubredditCall.cancel();
        }
    }
}
