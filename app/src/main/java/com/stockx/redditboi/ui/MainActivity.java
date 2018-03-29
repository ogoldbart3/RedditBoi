package com.stockx.redditboi.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.stockx.redditboi.R;
import com.stockx.redditboi.listeners.GenericListener;
import com.stockx.redditboi.model.RedditPost;
import com.stockx.redditboi.model.RedditWrapper;
import com.stockx.redditboi.ui.adapters.PostAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private PostAdapter mPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();

        RecyclerView postRecycler = findViewById(R.id.recycler);

        //

        mPostAdapter = new PostAdapter();
        mPostAdapter.setItemClickListener(new GenericListener<RedditPost>() {
            @Override
            public void onComplete(RedditPost output) {
                System.out.println(output.getData().getScore());
            }
        });

        LinearLayoutManager searchHistoryLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        postRecycler.setLayoutManager(searchHistoryLinearLayoutManager);
        postRecycler.setAdapter(mPostAdapter);

        initiateGetSubredditCall("sneakers", new Callback<RedditWrapper>() {
            @Override
            public void onResponse(Call<RedditWrapper> call, Response<RedditWrapper> response) {
                if (response.isSuccessful()) {
                    RedditWrapper redditWrapper = response.body();
                    if (redditWrapper != null
                            && redditWrapper.getData() != null) {
                        mPostAdapter.setItems(redditWrapper.getData().getChildren());
                    }
                }
            }

            @Override
            public void onFailure(Call<RedditWrapper> call, Throwable t) {

            }
        });

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
