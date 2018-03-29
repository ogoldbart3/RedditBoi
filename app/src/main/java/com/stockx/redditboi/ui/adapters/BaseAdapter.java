package com.stockx.redditboi.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.stockx.redditboi.listeners.GenericListener;
import com.stockx.redditboi.ui.adapters.viewholders.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oliver on 3/11/18
 */

public abstract class BaseAdapter<T, U extends BaseViewHolder<T>> extends RecyclerView.Adapter<U> {

    private List<T> mItems;

    protected GenericListener<T> mItemClickListener;

    protected RecyclerView mRecyclerView;

    public BaseAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull final U holder, int position) {
        holder.bind(getItem(position));
    }

    private T getItem(int position) {
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

    public void smoothScrollToTop() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }

    public void setItemClickListener(GenericListener<T> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void appendItem(T item) {
        if (mItems == null) {
            mItems = new ArrayList<>();
        }

        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    public void removeItemAtIndex(int adapterPosition) {
        if (mItems != null && adapterPosition < mItems.size()) {
            mItems.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
        }
    }

    public T getItemAtIndex(int adapterPosition) {
        if (mItems != null && adapterPosition < mItems.size()) {
            return mItems.get(adapterPosition);
        }
        return null;
    }
}
