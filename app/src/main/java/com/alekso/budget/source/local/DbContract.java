package com.alekso.budget.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.alekso.budget.BuildConfig;

/**
 * Created by alekso on 09/05/2017.
 */

public class DbContract {
    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_ACCOUNTS = "accounts";
    public static final String PATH_CURRENCIES = "currencies";
    public static final String PATH_CATEGORIES = "categories";
    public static final String PATH_TRANSACTIONS = "transactions";

    /**
     *
     */
    public static abstract class CurrencyEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CURRENCIES).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CURRENCIES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CURRENCIES;

        public static final String TABLE = "currencies";
        public static final String C_NAME = "name";
        public static final String C_SYMBOL = "symbol";

        public static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     *
     */
    public static abstract class CategoryEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGORIES).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CATEGORIES;

        public static final String TABLE = "categories";
        public static final String C_NAME = "name";
        public static final String C_PARENT_ID = "parent_id";

        public static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     *
     */
    public static abstract class AccountEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ACCOUNTS).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ACCOUNTS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ACCOUNTS;

        public static final String TABLE = "accounts";
        public static final String C_NAME = "name";
        public static final String C_CURRENCY_ID = "currency_id";
        public static final String C_TYPE = "type";

        public static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    /**
     *
     */
    public static abstract class TransactionEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TRANSACTIONS).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRANSACTIONS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRANSACTIONS;

        public static final String TABLE = "transactions";
        public static final String C_DATETIME = "datetime";
        public static final String C_TYPE = "type";
        public static final String C_ACCOUNT_ID = "account_id";
        public static final String C_CATEGORY_ID = "category_id";
        public static final String C_AMOUNT = "amount";
        public static final String C_BALANCE = "balance";
        public static final String C_STATUS = "status";
        public static final String C_COMMENT = "comment";
        public static final String C_IS_VITAL = "is_vital";

        public static Uri buildUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


}
