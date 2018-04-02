package com.stockx.droiddit.ui.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.stockx.droiddit.R;
import com.stockx.droiddit.model.RedditPost;
import com.stockx.droiddit.ui.adapters.viewholders.PostViewHolder;

/**
 * Created by harold on 3/19/18
 */

public class PostAdapter extends BaseAdapter<RedditPost, PostViewHolder> {

    private boolean mShowSubredditName = true;

    public PostAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(getItem(position), mShowSubredditName);
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PostViewHolder(inflater.inflate(viewType, parent, false), mItemClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_post_row;
    }

    public void setShowSubredditNames(boolean showSubredditName) {
        this.mShowSubredditName = showSubredditName;
        notifyDataSetChanged();
    }
}
