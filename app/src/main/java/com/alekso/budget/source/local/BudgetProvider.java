package com.alekso.budget.source.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alekso.budget.App;

/**
 * Created by alekso on 09/05/2017.
 */

public class BudgetProvider extends ContentProvider {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(BudgetProvider.class.getSimpleName());

    private static final int ACCOUNTS = 100;
    private static final int ACCOUNTS_ITEM = 101;
    private static final int CATEGORIES = 200;
    private static final int CATEGORIES_ITEM = 201;
    private static final int CURRENCIES = 300;
    private static final int CURRENCIES_ITEM = 301;
    private static final int TRANSACTIONS = 400;
    private static final int TRANSACTIONS_ITEM = 401;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    /**
     * Database helper
     */
    private DbHelper mDbHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DbContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DbContract.PATH_ACCOUNTS, ACCOUNTS);
        matcher.addURI(authority, DbContract.PATH_ACCOUNTS + "/*", ACCOUNTS_ITEM);
        matcher.addURI(authority, DbContract.PATH_CATEGORIES, CATEGORIES);
        matcher.addURI(authority, DbContract.PATH_CATEGORIES + "/*", CATEGORIES_ITEM);
        matcher.addURI(authority, DbContract.PATH_CURRENCIES, CURRENCIES);
        matcher.addURI(authority, DbContract.PATH_CURRENCIES + "/*", CURRENCIES_ITEM);
        matcher.addURI(authority, DbContract.PATH_TRANSACTIONS, TRANSACTIONS);
        matcher.addURI(authority, DbContract.PATH_TRANSACTIONS + "/*", TRANSACTIONS_ITEM);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        if (DEBUG) Log.d(TAG, "onCreate()");

        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query(uri: " + uri + "; selection: " + selection + "; selectionArgs: " + selectionArgs + ")");

        Cursor c;

        switch (sUriMatcher.match(uri)) {
            case ACCOUNTS:
                c = mDbHelper.getReadableDatabase().query(
                        DbContract.AccountEntry.TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;

            case ACCOUNTS_ITEM:
                String[] where = {uri.getLastPathSegment()};
                c = mDbHelper.getReadableDatabase().query(
                        DbContract.AccountEntry.TABLE,
                        projection,
                        DbContract.AccountEntry._ID + " = ? ",
                        where,
                        null,
                        null,
                        sortOrder
                );
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case ACCOUNTS:
                return DbContract.AccountEntry.CONTENT_TYPE;
            case ACCOUNTS_ITEM:
                return DbContract.AccountEntry.CONTENT_ITEM_TYPE;
            case CATEGORIES:
                return DbContract.CategoryEntry.CONTENT_TYPE;
            case CATEGORIES_ITEM:
                return DbContract.CategoryEntry.CONTENT_ITEM_TYPE;
            case CURRENCIES:
                return DbContract.CurrencyEntry.CONTENT_TYPE;
            case CURRENCIES_ITEM:
                return DbContract.CurrencyEntry.CONTENT_ITEM_TYPE;
            case TRANSACTIONS:
                return DbContract.TransactionEntry.CONTENT_TYPE;
            case TRANSACTIONS_ITEM:
                return DbContract.TransactionEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert(uri: " + uri + "; values: " + values + ")");

        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case CATEGORIES:
                long id = db.insert(
                        DbContract.CategoryEntry.TABLE,
                        null,
                        values
                );
                if (id > 0) {
                    returnUri = DbContract.CategoryEntry.buildUri(id);
                } else {
                    throw new SQLiteException("Failed to insert row for " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;

        switch (match) {
            case CATEGORIES:
                rowsDeleted = db.delete(DbContract.CategoryEntry.TABLE, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (selection == null || rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case CATEGORIES:
                rowsUpdated = db.update(DbContract.CategoryEntry.TABLE, values, selection, selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }
}
