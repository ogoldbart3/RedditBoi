package com.stockx.redditboi.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stockx.redditboi.R;
import com.stockx.redditboi.model.RedditPost;
import com.stockx.redditboi.ui.adapters.viewholders.PostViewHolder;

/**
 * Created by oliver on 3/19/18
 */

public class PostAdapter extends BaseAdapter<RedditPost, PostViewHolder> {

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PostViewHolder(inflater.inflate(viewType, parent, false), mItemClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_post_row;
    }

    public void scrollToBottom() {
        if (mRecyclerView != null) {
            System.out.println("yo yo " + getItemCount());
            mRecyclerView.scrollToPosition(getItemCount() - 1);
        }
    }
}
