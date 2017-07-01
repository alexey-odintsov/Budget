package com.alekso.budget.model.decorators;

import android.database.Cursor;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.model.Account;
import com.alekso.budget.model.AccountsReader;
import com.alekso.budget.source.local.DbContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekso on 30/06/2017.
 */

public class ReviewAccountsReader {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(ReviewAccountsReader.class.getSimpleName());


    public static List<ReviewAccount> fromCursor(Cursor c) {
        if (DEBUG) Log.d(TAG, "fromCursor(cursor: " + c + ")");

        List<ReviewAccount> accounts = new ArrayList<>();

        while (c.moveToNext()) {
            Account account = new Account(
                    c.getLong(c.getColumnIndex(DbContract.AccountEntry._ID)),
                    c.getLong(c.getColumnIndex(DbContract.AccountEntry.C_CURRENCY_ID)),
                    c.getString(c.getColumnIndex(DbContract.AccountEntry.C_NAME)),
                    c.getInt(c.getColumnIndex(DbContract.AccountEntry.C_TYPE))
            );

            ReviewAccount reviewAccount = new ReviewAccount(account,
                    c.getDouble(c.getColumnIndex(DbContract.TransactionEntry.C_BALANCE)));

            accounts.add(reviewAccount);
            Log.d(TAG, "account parsed: " + account);
        }
        c.close();

        return accounts;
    }
}
