package com.stockx.droiddit.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.stockx.droiddit.R;
import com.stockx.droiddit.listeners.OnKeyboardVisibilityListener;
import com.stockx.droiddit.listeners.SimpleFinishListener;
import com.stockx.droiddit.model.RedditPost;
import com.stockx.droiddit.model.RedditWrapper;
import com.stockx.droiddit.ui.adapters.PostAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harold on 3/29/18
 */

public class SubredditActivity extends BaseActivity {

    private PostAdapter mPostAdapter;

    private EditText mEditText;

    private MenuItem mGoToSubredditMenuItem;
    private MenuItem mOpenSubredditMenuItem;
    private MenuItem mToggleSubredditNamesOnMenuItem;
    private MenuItem mToggleSubredditNamesOffMenuItem;

    private Callback<RedditWrapper> mCallback = new Callback<RedditWrapper>() {
        @Override
        public void onResponse(Call<RedditWrapper> call, Response<RedditWrapper> response) {
            if (response.isSuccessful()) {
                RedditWrapper redditWrapper = response.body();
                if (redditWrapper != null
                        && redditWrapper.getData() != null
                        && redditWrapper.getData().getChildren() != null
                        && redditWrapper.getData().getChildren().size() > 0) {
                    mPostAdapter.setItems(redditWrapper.getData().getChildren());
                    hideKeyboard();
                } else {
                    Snackbar.make(mEditText, getString(R.string.subreddit_not_found_snackbar, mEditText.getText().toString()), Snackbar.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onFailure(Call<RedditWrapper> call, Throwable t) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subreddit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mEditText = findViewById(R.id.subreddit_name_edittext);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    initiateGetSubredditCall(v.getText().toString(), mCallback);
                    return true;
                }
                return false;
            }
        });

        RecyclerView postRecycler = findViewById(R.id.recycler);

        mPostAdapter = new PostAdapter();
        mPostAdapter.setItemClickListener(new SimpleFinishListener<RedditPost>() {
            @Override
            public void onComplete(RedditPost output) {
                String url = getString(R.string.base_url) + output.getData().getPermalink();
                System.out.println(url);
                openWebPage(url);
            }
        });

        LinearLayoutManager searchHistoryLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        postRecycler.setLayoutManager(searchHistoryLinearLayoutManager);
        postRecycler.setAdapter(mPostAdapter);

        setKeyboardVisibilityListener(new OnKeyboardVisibilityListener() {
            @Override
            public void onVisibilityChanged(boolean visible) {
                mGoToSubredditMenuItem.setVisible(visible);
                mOpenSubredditMenuItem.setVisible(!visible);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_subreddit, menu);

        mGoToSubredditMenuItem = menu.findItem(R.id.action_go_to_subreddit);
        mOpenSubredditMenuItem = menu.findItem(R.id.action_open_subreddit);
        mToggleSubredditNamesOnMenuItem = menu.findItem(R.id.action_toggle_subreddit_names_on);
        mToggleSubredditNamesOffMenuItem = menu.findItem(R.id.action_toggle_subreddit_names_off);

        mOpenSubredditMenuItem.setVisible(false);
        mToggleSubredditNamesOnMenuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_go_to_subreddit:
                initiateGetSubredditCall(mEditText.getText().toString(), mCallback);
                return true;
            case R.id.action_toggle_subreddit_names_on:
                mPostAdapter.setShowSubredditNames(true);
                mToggleSubredditNamesOnMenuItem.setVisible(false);
                mToggleSubredditNamesOffMenuItem.setVisible(true);
                return true;
            case R.id.action_toggle_subreddit_names_off:
                mPostAdapter.setShowSubredditNames(false);
                mToggleSubredditNamesOnMenuItem.setVisible(true);
                mToggleSubredditNamesOffMenuItem.setVisible(false);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}