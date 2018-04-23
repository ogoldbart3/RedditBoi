package com.stockx.droiddit.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stockx.droiddit.listeners.SimpleFinishListener;
import com.stockx.droiddit.ui.adapters.viewholders.BaseViewHolder;

import java.util.List;

/**
 * Created by harold on 3/29/18
 */

public abstract class BaseAdapter<T, U extends BaseViewHolder<T>> extends RecyclerView.Adapter<U> {

    private List<T> mItems;

    protected SimpleFinishListener<T> mItemClickListener;

    protected RecyclerView mRecyclerView;

    public BaseAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull final U holder, int position) {
        holder.bind(getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onComplete(getItem(holder.getAdapterPosition()));
                }
            }
        });
    }

    protected T getItem(int position) {
        if (mItems != null
                && position >= 0 && position < mItems.size()) {
            return mItems.get(position);
        }

        return null;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        mRecyclerView = recyclerView;
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public void setItems(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void setItemClickListener(SimpleFinishListener<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
