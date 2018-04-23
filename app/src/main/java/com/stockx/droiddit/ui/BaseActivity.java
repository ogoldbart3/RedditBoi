package com.stockx.droiddit.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import com.stockx.droiddit.App;
import com.stockx.droiddit.R;
import com.stockx.droiddit.listeners.KeyboardVisibilityListener;
import com.stockx.droiddit.model.RedditWrapper;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by harold on 3/29/18
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

    //

    protected void setKeyboardVisibilityListener(final KeyboardVisibilityListener keyboardVisibilityListener) {
        final View parentView = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        parentView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            private boolean alreadyOpen;
            private final int defaultKeyboardHeightDP = 100;
            private final int EstimatedKeyboardDP = defaultKeyboardHeightDP + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ? 48 : 0);
            private final Rect rect = new Rect();

            @Override
            public void onGlobalLayout() {
                int estimatedKeyboardHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, EstimatedKeyboardDP, parentView.getResources().getDisplayMetrics());
                parentView.getWindowVisibleDisplayFrame(rect);
                int heightDiff = parentView.getRootView().getHeight() - (rect.bottom - rect.top);
                boolean isShown = heightDiff >= estimatedKeyboardHeight;

                if (isShown == alreadyOpen) {
                    Log.i("Keyboard state", "Ignoring global layout change...");
                    return;
                }
                alreadyOpen = isShown;
                keyboardVisibilityListener.onVisibilityChanged(isShown);
            }
        });
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
