package com.alekso.budget.ui.review;

import android.database.Cursor;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.model.Account;
import com.alekso.budget.model.AccountsReader;
import com.alekso.budget.model.decorators.ReviewAccount;
import com.alekso.budget.model.decorators.ReviewAccountsReader;
import com.alekso.budget.source.Repository;

import java.util.List;

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

        mView.createLoaders();
    }

    @Override
    public void onGetAccounts(Cursor accountsCursor) {
        if (DEBUG) Log.d(TAG, "onGetAccounts(data: " + accountsCursor + ")");

        List<ReviewAccount> accounts = ReviewAccountsReader.fromCursor(accountsCursor);
        mView.showAccounts(accounts);
    }
}
