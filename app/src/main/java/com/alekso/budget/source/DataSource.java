package com.alekso.budget.source;

import java.util.List;

/**
 * Created by alekso on 09/05/2017.
 */

public interface DataSource {
    /**
     * Callback for retrieving list of items
     *
     * @param <T>
     */
    interface LoadItemsListCallback<T> {
        void onSuccess(List<T> items);

        void onError(String message);
    }

    /**
     * Callback for retrieving an item
     *
     * @param <T>
     */
    interface LoadItemCallback<T> {
        void onSuccess(T result);

        void onError(String message);
    }
}
