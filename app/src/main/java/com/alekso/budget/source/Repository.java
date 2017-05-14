package com.alekso.budget.source;

import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.source.local.LocalDataSource;
import com.alekso.budget.source.remote.RemoteDataSource;

/**
 * Created by alekso on 09/05/2017.
 */

public class Repository {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(Repository.class.getSimpleName());

    /**
     * Repository instance field
     */
    private static Repository sInstance;

    /**
     * Local data source - database
     */
    private final LocalDataSource mLocalDataSource;

    /**
     * Remote data source - server
     */
    private final RemoteDataSource mRemoteDataSource;

    /**
     * Constructor.
     *
     * @param localDataSource
     * @param remoteDataSource
     */
    private Repository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        if (DEBUG)
            Log.d(TAG, "constructor(localDataSource: " + localDataSource + "; remoteDataSource: " + remoteDataSource + ")");

        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    /**
     * Returns instance of the repository.
     *
     * @param remoteDataSource
     * @return
     */
    public static Repository getInstance(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new Repository(localDataSource, remoteDataSource);
        }

        return sInstance;
    }
}
