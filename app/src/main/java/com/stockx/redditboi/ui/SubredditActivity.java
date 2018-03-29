package com.stockx.redditboi.ui;

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

import com.stockx.redditboi.R;
import com.stockx.redditboi.listeners.GenericListener;
import com.stockx.redditboi.model.RedditPost;
import com.stockx.redditboi.model.RedditWrapper;
import com.stockx.redditboi.ui.adapters.PostAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oliver on 3/29/18
 */

public class SubredditActivity extends BaseActivity {

    private PostAdapter mPostAdapter;

    private EditText mEditText;

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
                    Snackbar.make(mEditText, "\"" + mEditText.getText().toString() + "\" ain't no subreddit", Snackbar.LENGTH_LONG).show();
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

        mPostAdapter = new PostAdapter(false);
        mPostAdapter.setItemClickListener(new GenericListener<RedditPost>() {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}