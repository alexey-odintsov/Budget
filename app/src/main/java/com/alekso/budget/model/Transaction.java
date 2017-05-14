package com.alekso.budget.model;

/**
 * Created by alekso on 09/05/2017.
 */

public class Transaction {
    public static final int STATUS_PLANNED = 0;
    public static final int STATUS_SUBMITTED = 1;
    public static final int TYPE_EXPENSE_INCOME = 0;
    public static final int TYPE_TRANSFER = 1;

    private long mId;
    /**
     * Unix timestamp
     */
    private long mDateTime;

    /**
     * Expense/Income | Transfer
     */
    private int mType;

    private long mAccountId;
    private long mCategoryId;
    private double mAmount;
    private double mBalance;
    /**
     * Planned | Submitted
     */
    private int mStatus;
    private String mComment;
    /**
     * Determines necessity for the transaction. Will be used for savings planning.
     */
    private boolean mIsVital;

    public Transaction() {
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getDateTime() {
        return mDateTime;
    }

    public void setDateTime(long timeStamp) {
        mDateTime = timeStamp;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }

    public long getAccountId() {
        return mAccountId;
    }

    public void setAccountId(long accountId) {
        mAccountId = accountId;
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }

    public void setBalance(double balance) {
        mBalance = balance;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public void setIsVital(boolean isVital) {
        mIsVital = isVital;
    }

    public boolean isVital() {
        return mIsVital;
    }

    public String toString() {
        return String.format("{ id: %d; dt: %d; type: %d; accountId: %d; categoryId: %d; amount:" +
                        " %.2f; balance: %.2f; status: %d; isVital: %b; comment: %s }", mId, mDateTime,
                mType, mAccountId, mCategoryId, mAmount, mBalance, mStatus, mIsVital, mComment);
    }
}
