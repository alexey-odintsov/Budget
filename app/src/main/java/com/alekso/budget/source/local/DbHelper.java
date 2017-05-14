package com.alekso.budget.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.alekso.budget.source.local.DbContract.*;

/**
 * Created by alekso on 09/05/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    /**
     * Database file name
     */
    private static final String DB_NAME = "budget.db";
    /**
     * Database version
     */
    private static final int DB_VERSION = 1;

    /**
     * SQL query to create Accounts table
     */
    private static final String SQL_CREATE_ACCOUNTS = "CREATE TABLE " + AccountEntry.TABLE + " ("
            + AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AccountEntry.C_TYPE + " INTEGER NOT NULL,"
            + AccountEntry.C_NAME + " TEXT NOT NULL,"
            + AccountEntry.C_CURRENCY_ID + " INTEGER NOT NULL"
            + ")";

    /**
     * SQL query to create Categories table
     */
    private static final String SQL_CREATE_CATEGORIES = "CREATE TABLE " + CategoryEntry.TABLE + " ("
            + CategoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CategoryEntry.C_PARENT_ID + " INTEGER,"
            + CategoryEntry.C_NAME + " TEXT NOT NULL"
            + ")";

    /**
     * SQL query to create Currencies table
     */
    private static final String SQL_CREATE_CURRENCIES = "CREATE TABLE " + CurrencyEntry.TABLE + " ("
            + CurrencyEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CurrencyEntry.C_NAME + " TEXT NOT NULL,"
            + CurrencyEntry.C_SYMBOL + " TEXT NOT NULL"
            + ")";

    /**
     * SQL query to create Transactions table
     */
    private static final String SQL_CREATE_TRANSACTIONS = "CREATE TABLE " + TransactionEntry.TABLE + " ("
            + TransactionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TransactionEntry.C_ACCOUNT_ID + " INTEGER NOT NULL,"
            + TransactionEntry.C_CATEGORY_ID + " INTEGER NOT NULL,"
            + TransactionEntry.C_DATETIME + " INTEGER NOT NULL,"
            + TransactionEntry.C_AMOUNT + " REAL NOT NULL,"
            + TransactionEntry.C_BALANCE + " REAL NOT NULL,"
            + TransactionEntry.C_COMMENT + " TEXT,"
            + TransactionEntry.C_IS_VITAL + " INTEGER DEFAULT 1," // 1 - true - is vital
            + TransactionEntry.C_STATUS + " INTEGER DEFAULT 0," // 0 - planned
            + TransactionEntry.C_TYPE + " INTEGER DEFAULT 0" // 0 - expense/income
            + ")";

    /**
     * Constructor
     *
     * @param context Context to use and open database
     */
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        createTables(db);
    }

    /**
     * Deletes tables
     *
     * @param db
     */
    private void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TransactionEntry.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CurrencyEntry.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CategoryEntry.TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + AccountEntry.TABLE);
    }

    /**
     * Creates initial db structure
     *
     * @param db
     */
    private void createTables(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ACCOUNTS);
        db.execSQL(SQL_CREATE_CATEGORIES);
        db.execSQL(SQL_CREATE_CURRENCIES);
        db.execSQL(SQL_CREATE_TRANSACTIONS);
    }
}