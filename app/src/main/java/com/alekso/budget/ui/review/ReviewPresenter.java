package com.alekso.budget.ui.review;

import android.database.Cursor;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.source.Repository;

/**
 * Created by alekso on 27/06/2017.
 */

public class ReviewPresenter implements ReviewContract.Presenter {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(ReviewPresenter.class.getSimpleName());

    private final Repository mRepository;
    private final ReviewContract.View mView;

    public ReviewPresenter(Repository repository, ReviewContract.View view) {
        if (DEBUG) Log.d(TAG, "constructor(repository: " + repository + "; view: " + view + ")");

        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (DEBUG) Log.d(TAG, "start()");

        //mView.createAccountsLoader();
    }

    @Override
    public void onGetAccounts(Cursor accountsCursor) {

    }
}
