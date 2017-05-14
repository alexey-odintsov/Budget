package com.alekso.budget.source.local;

import com.alekso.budget.model.Account;
import com.alekso.budget.source.DataSource;

/**
 * Created by alekso on 09/05/2017.
 */

public interface LocalDataSource {
    /**
     * Retrieve list of all accounts
     *
     * @param callback
     */
    void getAccounts(DataSource.LoadItemsListCallback<Account> callback);

    /**
     * Add new account
     *
     * @param account
     */
    void addAccount(Account account);

    /**
     * Remove account
     *
     * @param accountId
     */
    void removeAccount(long accountId);
}
