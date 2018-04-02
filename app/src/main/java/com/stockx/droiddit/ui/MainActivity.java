package com.stockx.droiddit.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.stockx.droiddit.R;
import com.stockx.droiddit.listeners.SimpleFinishListener;
import com.stockx.droiddit.model.RedditPost;
import com.stockx.droiddit.model.RedditWrapper;
import com.stockx.droiddit.ui.adapters.PostAdapter;

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

        RecyclerView postRecycler = findViewById(R.id.recycler);

        //

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

        initiateGetHomepageCall(new Callback<RedditWrapper>() {
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
}
