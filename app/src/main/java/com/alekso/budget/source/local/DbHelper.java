package com.alekso.budget.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.model.Account;

import static com.alekso.budget.source.local.DbContract.AccountEntry;
import static com.alekso.budget.source.local.DbContract.CategoryEntry;
import static com.alekso.budget.source.local.DbContract.CurrencyEntry;
import static com.alekso.budget.source.local.DbContract.TransactionEntry;

/**
 * Created by alekso on 09/05/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(DbHelper.class.getSimpleName());

    /**
     * Database file name
     */
    private static final String DB_NAME = "budget.db";
    /**
     * Database version
     */
    private static final int DB_VERSION = 5;

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
        if (DEBUG)
            Log.d(TAG, "onUpgrade(db: " + db + "; oldVersion: " + oldVersion + "; newVersion: " + newVersion + ")");

        dropTables(db);
        createTables(db);
        insertPredefinedValues(db);
    }

    /**
     * Deletes tables
     *
     * @param db
     */
    private void dropTables(SQLiteDatabase db) {
        if (DEBUG) Log.d(TAG, "dropTables(db: " + db + ")");

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
        if (DEBUG) Log.d(TAG, "createTables(db: " + db + ")");

        db.execSQL(SQL_CREATE_ACCOUNTS);
        db.execSQL(SQL_CREATE_CATEGORIES);
        db.execSQL(SQL_CREATE_CURRENCIES);
        db.execSQL(SQL_CREATE_TRANSACTIONS);
    }

    /**
     * Inserts predefined values
     *
     * @param db
     */
    private void insertPredefinedValues(SQLiteDatabase db) {
        if (DEBUG) Log.d(TAG, "insertPredefinedValues(db: " + db + ")");

        final String CURRENCY_RUB = "1";
        final String CURRENCY_USD = "2";
        final String CURRENCY_EUR = "3";

        final String ACCOUNT_CASH_RUB = "1";
        final String ACCOUNT_CASH_USD = "2";
        final String ACCOUNT_CASH_EUR = "3";
        final String ACCOUNT_DEBIT_RUB = "4";
        final String ACCOUNT_DEBIT_USD = "5";
        final String ACCOUNT_CREDIT_RUB = "6";

        // populate currency
        String[][] currencyData = {
                {CURRENCY_RUB, "RUB", "р."},
                {CURRENCY_USD, "USD", "$"},
                {CURRENCY_EUR, "EUR", "€"},
        };
        ContentValues[] cv = new ContentValues[currencyData.length];
        for (int i = 0; i < currencyData.length; i++) {
            cv[i] = new ContentValues();
            cv[i].put(CurrencyEntry._ID, currencyData[i][0]);
            cv[i].put(CurrencyEntry.C_NAME, currencyData[i][1]);
            cv[i].put(CurrencyEntry.C_SYMBOL, currencyData[i][2]);
            db.insert(CurrencyEntry.TABLE, null, cv[i]);
        }

        // populate accounts
        String[][] accountData = {
                {ACCOUNT_CASH_RUB, String.valueOf(Account.TYPE.CASH), "Cash RUB", CURRENCY_RUB},
                {ACCOUNT_CASH_USD, String.valueOf(Account.TYPE.CASH), "Cash USD", CURRENCY_USD},
                {ACCOUNT_CASH_EUR, String.valueOf(Account.TYPE.CASH), "Cash EUR", CURRENCY_EUR},
                {ACCOUNT_DEBIT_RUB, String.valueOf(Account.TYPE.DEBIT_CARD), "Visa RUB", CURRENCY_RUB},
                {ACCOUNT_DEBIT_USD, String.valueOf(Account.TYPE.DEBIT_CARD), "Visa USD", CURRENCY_USD},
                {ACCOUNT_CREDIT_RUB, String.valueOf(Account.TYPE.CREDIT_CARD), "Visa Credit RUB", CURRENCY_RUB},
        };
        cv = new ContentValues[accountData.length];
        for (int i = 0; i < accountData.length; i++) {
            cv[i] = new ContentValues();
            cv[i].put(AccountEntry._ID, accountData[i][0]);
            cv[i].put(AccountEntry.C_TYPE, accountData[i][1]);
            cv[i].put(AccountEntry.C_NAME, accountData[i][2]);
            cv[i].put(AccountEntry.C_CURRENCY_ID, accountData[i][3]);
            db.insert(AccountEntry.TABLE, null, cv[i]);
        }
    }
}