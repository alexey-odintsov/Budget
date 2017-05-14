package com.alekso.budget.source.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.model.Account;
import com.alekso.budget.source.DataSource;

/**
 * Created by alekso on 09/05/2017.
 */

public class LocalDataSourceImpl implements LocalDataSource {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(LocalDataSourceImpl.class.getSimpleName());

    private static LocalDataSourceImpl sInstance;
    private ContentResolver mContentResolver;

    /**
     * @param сontentResolver
     */
    private LocalDataSourceImpl(ContentResolver сontentResolver) {
        if (DEBUG) Log.d(TAG, "constructor(сontentResolver: " + сontentResolver + ")");
        mContentResolver = сontentResolver;
    }

    /**
     * @param сontentResolver
     * @return
     */
    public static LocalDataSourceImpl getInstance(ContentResolver сontentResolver) {
        if (sInstance == null) {
            sInstance = new LocalDataSourceImpl(сontentResolver);
        }

        return sInstance;
    }

    @Override
    public void getAccounts(DataSource.LoadItemsListCallback<Account> callback) {
        if (DEBUG) Log.d(TAG, "getAccounts(listener: " + callback + ")");

        mContentResolver.query(DbContract.AccountEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void addAccount(Account account) {
        if (DEBUG) Log.d(TAG, "addAccount(account: " + account + ")");

        ContentValues cv = new ContentValues();
        cv.put(DbContract.AccountEntry.C_TYPE, account.getType());
        cv.put(DbContract.AccountEntry.C_CURRENCY_ID, account.getCurrencyId());
        cv.put(DbContract.AccountEntry.C_NAME, account.getName());
        mContentResolver.insert(DbContract.AccountEntry.CONTENT_URI, cv);
    }

    @Override
    public void removeAccount(long accountId) {
        if (DEBUG) Log.d(TAG, "removeAccount(accountId: " + accountId + ")");


        // TODO: 14/05/2017 Should we delete specified account or just mark it as deleted
        mContentResolver.delete(DbContract.AccountEntry.CONTENT_URI,
                DbContract.AccountEntry._ID + " = ? ",
                new String[]{String.valueOf(accountId)});
    }

}
