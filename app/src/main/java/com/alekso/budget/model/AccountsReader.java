package com.alekso.budget.model;

import android.database.Cursor;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.source.local.DbContract;

import java.util.ArrayList;
import java.util.List;

import static com.alekso.budget.source.local.DbContract.*;

/**
 * Created by alekso on 13/05/2017.
 */

public class AccountsReader {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(AccountsReader.class.getSimpleName());


    public static List<Account> fromCursor(Cursor c) {
        if (DEBUG) Log.d(TAG, "fromCursor(cursor: " + c + ")");

        List<Account> accounts = new ArrayList<>();

        while (c.moveToNext()) {
            Account account = new Account(
                    c.getLong(c.getColumnIndex(AccountEntry._ID)),
                    c.getLong(c.getColumnIndex(AccountEntry.C_CURRENCY_ID)),
                    c.getString(c.getColumnIndex(AccountEntry.C_NAME)),
                    c.getInt(c.getColumnIndex(AccountEntry.C_TYPE))
            );
            accounts.add(account);
            Log.d(TAG, "account parsed: " + account);
        }
        c.close();

        return accounts;
    }
}
