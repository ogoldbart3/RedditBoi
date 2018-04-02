package com.stockx.droiddit.ui.adapters.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.stockx.droiddit.R;
import com.stockx.droiddit.listeners.SimpleFinishListener;
import com.stockx.droiddit.model.RedditPost;

/**
 * Created by harold on 3/19/18
 */

public class PostViewHolder extends BaseViewHolder<RedditPost> {

    private ImageView mPreviewImageView;
    private TextView mSubredditTextView;
    private TextView mTitleTextView;

    private RedditPost mRedditPost;

    public PostViewHolder(View itemView, SimpleFinishListener<RedditPost> itemClickListener) {
        super(itemView, itemClickListener);

        mPreviewImageView = itemView.findViewById(R.id.post_preview);
        mSubredditTextView = itemView.findViewById(R.id.post_subreddit);
        mTitleTextView = itemView.findViewById(R.id.post_title);
    }

    @Override
    public void bind(RedditPost redditPost) {
        mRedditPost = redditPost;

        if (mRedditPost != null
                && mRedditPost.getData() != null
                && mRedditPost.getData().getPreview() != null
                && mRedditPost.getData().getPreview().getRedditImages() != null
                && mRedditPost.getData().getPreview().getRedditImages().size() > 0
                && mRedditPost.getData().getPreview().getRedditImages().get(0) != null
                && mRedditPost.getData().getPreview().getRedditImages().get(0).getSource() != null
                && mRedditPost.getData().getPreview().getRedditImages().get(0).getSource().getUrl() != null
                && !mRedditPost.getData().getPreview().getRedditImages().get(0).getSource().getUrl().isEmpty()) {
            mPreviewImageView.setVisibility(View.VISIBLE);
            Picasso.with(mContext)
                    .load(mRedditPost.getData().getPreview().getRedditImages().get(0).getSource().getUrl())
                    .placeholder(R.drawable.ic_error_black_24dp)
                    .fit()
                    .centerCrop()
                    .into(mPreviewImageView);
        } else {
            mPreviewImageView.setVisibility(View.GONE);
        }

        mSubredditTextView.setText(mContext.getString(R.string.subreddit_url_prefix_with_value, redditPost.getData().getSubreddit()));

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

    public void bind(RedditPost redditPost, boolean showSubreddit) {
        bind(redditPost);
        mSubredditTextView.setVisibility(showSubreddit ? View.VISIBLE : View.GONE);
    }
}
