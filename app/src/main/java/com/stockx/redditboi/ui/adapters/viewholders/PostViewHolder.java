package com.stockx.redditboi.ui.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import com.stockx.redditboi.R;
import com.stockx.redditboi.listeners.GenericListener;
import com.stockx.redditboi.model.RedditPost;

/**
 * Created by oliver on 3/19/18
 */

public class PostViewHolder extends BaseViewHolder<RedditPost> {

    private TextView mTitleTextView;

    private RedditPost mRedditPost;

    public PostViewHolder(View itemView, GenericListener<RedditPost> itemClickListener) {
        super(itemView, itemClickListener);

        mTitleTextView = itemView.findViewById(R.id.post_title);
    }

    @Override
    public void bind(RedditPost redditPost) {
        mRedditPost = redditPost;

        mTitleTextView.setText(redditPost.getData().getTitle());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onComplete(mRedditPost);
                }
            }
        });
    }
}
