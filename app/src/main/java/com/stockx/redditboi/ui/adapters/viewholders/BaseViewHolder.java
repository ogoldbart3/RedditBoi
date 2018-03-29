package com.stockx.redditboi.ui.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stockx.redditboi.listeners.GenericListener;

/**
 * Created by oliver on 3/11/18
 */

public abstract class BaseViewHolder<U> extends RecyclerView.ViewHolder {

    protected Context mContext;

    protected GenericListener<U> mItemClickListener;

    public BaseViewHolder(View itemView, GenericListener<U> itemClickListener) {
        super(itemView);

        mContext = itemView.getContext();

        mItemClickListener = itemClickListener;
    }

    public abstract void bind(U u);
}
