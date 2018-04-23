package com.stockx.droiddit.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
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

/**
 * Created by harold on 3/29/18
 */

public class MainActivity extends BaseActivity {

    private PostAdapter mPostAdapter;

    private RecyclerView mPostRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPostRecycler = findViewById(R.id.recycler);

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
        mPostRecycler.setLayoutManager(searchHistoryLinearLayoutManager);
        mPostRecycler.setAdapter(mPostAdapter);

        initiateGetHomepageCall(new Callback<RedditWrapper>() {
            @Override
            public void onResponse(Call<RedditWrapper> call, Response<RedditWrapper> response) {
                if (response.isSuccessful()) {
                    RedditWrapper redditWrapper = response.body();
                    if (redditWrapper != null
                            && redditWrapper.getData() != null) {
                        mPostAdapter.setItems(redditWrapper.getData().getChildren());
                    } else {
                        Snackbar.make(mPostRecycler, getString(R.string.network_error_snackbar), Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(mPostRecycler, getString(R.string.network_error_snackbar), Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RedditWrapper> call, Throwable t) {
                Snackbar.make(mPostRecycler, getString(R.string.network_error_snackbar), Snackbar.LENGTH_LONG).show();
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
