package com.alekso.budget.model.decorators;

import com.alekso.budget.model.Transaction;

/**
 * Created by alekso on 14/05/2017.
 */

public class TimelineItem {
    private Transaction mTransaction;
    private String mAccountName;
    private String mCategoryName;

    public TimelineItem(Transaction transaction) {
        mTransaction = transaction;
    }

    public Transaction getTransaction() {
        return mTransaction;
    }
}
