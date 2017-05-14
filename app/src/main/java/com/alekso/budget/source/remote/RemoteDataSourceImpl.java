package com.alekso.budget.source.remote;

import android.content.Context;

import com.alekso.budget.App;

/**
 * Created by alekso on 09/05/2017.
 */

public class RemoteDataSourceImpl implements RemoteDataSource {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(RemoteDataSourceImpl.class.getSimpleName());

    private static RemoteDataSourceImpl sInstance;
    private Context mContext;


    /**
     * @param context
     */
    private RemoteDataSourceImpl(Context context) {
        mContext = context;
    }

    /**
     * @param context
     * @return
     */
    public static RemoteDataSourceImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RemoteDataSourceImpl(context);
        }

        return sInstance;
    }
}
