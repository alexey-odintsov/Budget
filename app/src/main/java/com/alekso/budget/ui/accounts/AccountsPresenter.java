package com.alekso.budget.ui.accounts;

import android.database.Cursor;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.model.Account;
import com.alekso.budget.model.AccountsReader;
import com.alekso.budget.source.Repository;

import java.util.List;

/**
 * Created by alekso on 13/05/2017.
 */

public class AccountsPresenter implements AccountsContract.Presenter {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(AccountsPresenter.class.getSimpleName());

    private final Repository mRepository;
    private final AccountsContract.View mView;

    public AccountsPresenter(Repository repository, AccountsContract.View view) {
        if (DEBUG) Log.d(TAG, "constructor(repository: " + repository + "; view: " + view + ")");

        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (DEBUG) Log.d(TAG, "start()");

        mView.createAccountsLoader();
    }

    @Override
    public void onGetAccounts(Cursor accountsCursor) {
        if (DEBUG) Log.d(TAG, "onGetAccounts(data: " + accountsCursor + ")");

        List<Account> accounts = AccountsReader.fromCursor(accountsCursor);
        mView.showAccounts(accounts);
    }
}
