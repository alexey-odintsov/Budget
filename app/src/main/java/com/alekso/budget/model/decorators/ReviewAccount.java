package com.alekso.budget.model.decorators;

import com.alekso.budget.model.Account;

/**
 * Account data for Review screen
 * <p>
 * Created by alekso on 28/06/2017.
 */
public class ReviewAccount {
    /**
     * Account data
     */
    private Account mAccount;
    /**
     * Balance for the account at the current (system) time
     */
    private double mBalance;
    /**
     * Image to represent the account
     */
    private int mImageResource;

    public ReviewAccount(Account account, double balance) {
        mAccount = account;
        mBalance = balance;
    }

    public Account getAccount() {
        return mAccount;
    }

    public double getBalance() {
        return mBalance;
    }
}
