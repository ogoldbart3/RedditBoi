package com.stockx.redditboi.listeners;

/**
 * Created by oliver on 12/27/17
 */

public interface GenericListener<T> {
    void onComplete(T output);
}
