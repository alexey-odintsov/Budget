package com.alekso.budget.model.decorators;

import android.database.Cursor;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.model.Transaction;
import com.alekso.budget.source.local.DbContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekso on 08/07/2017.
 */

public class TimelineItemsReader {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(TimelineItemsReader.class.getSimpleName());

    /**
     * @param c
     * @return
     */
    public static List<TimelineItem> fromCursor(Cursor c) {
        if (DEBUG) Log.d(TAG, "fromCursor(cursor: " + c + ")");

        List<TimelineItem> items = new ArrayList<>();

        while (c.moveToNext()) {
            Transaction transaction = new Transaction.Builder()
                    .id(c.getLong(c.getColumnIndex(DbContract.TransactionEntry._ID)))
                    .accountId(c.getLong(c.getColumnIndex(DbContract.TransactionEntry.C_ACCOUNT_ID)))
                    .categoryId(c.getLong(c.getColumnIndex(DbContract.TransactionEntry.C_CATEGORY_ID)))
                    .amount(c.getDouble(c.getColumnIndex(DbContract.TransactionEntry.C_AMOUNT)))
                    .balance(c.getDouble(c.getColumnIndex(DbContract.TransactionEntry.C_BALANCE)))
                    .comment(c.getString(c.getColumnIndex(DbContract.TransactionEntry.C_COMMENT)))
                    .dateTime(c.getLong(c.getColumnIndex(DbContract.TransactionEntry.C_DATETIME)))
                    .isVital(c.getInt(c.getColumnIndex(DbContract.TransactionEntry.C_IS_VITAL)) > 0)
                    .status(c.getInt(c.getColumnIndex(DbContract.TransactionEntry.C_STATUS)))
                    .type(c.getInt(c.getColumnIndex(DbContract.TransactionEntry.C_TYPE)))
                    .build();

            TimelineItem item = new TimelineItem(transaction);
            item.setAccountName(c.getString(c.getColumnIndex(DbContract.AccountEntry.C_NAME)));

            items.add(item);
            Log.d(TAG, "timeline item parsed: " + item);
        }
        c.close();

        return items;
    }

}