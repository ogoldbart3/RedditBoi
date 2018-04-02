package com.stockx.droiddit.ui.adapters.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stockx.droiddit.listeners.SimpleFinishListener;

/**
 * Created by harold on 3/11/18
 */

public abstract class BaseViewHolder<U> extends RecyclerView.ViewHolder {

    protected Context mContext;

    protected SimpleFinishListener<U> mItemClickListener;

    public BaseViewHolder(View itemView, SimpleFinishListener<U> itemClickListener) {
        super(itemView);

        mContext = itemView.getContext();

        mItemClickListener = itemClickListener;
    }

    public abstract void bind(U u);
}
