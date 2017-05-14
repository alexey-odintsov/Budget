package com.alekso.budget.model;

import android.database.Cursor;

import com.alekso.budget.App;

import java.util.List;

/**
 * Created by alekso on 13/05/2017.
 */

public class AccountsReader {
    private static final boolean DEBUG = false;
    private static final String TAG = App.fullTag(AccountsReader.class.getSimpleName());


    public static List<Account> fromCursor(Cursor data) {
        return null;
    }
}
