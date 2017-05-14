package com.alekso.budget.ui.accounts;

import android.database.Cursor;

import com.alekso.budget.model.Account;
import com.alekso.budget.ui.BasePresenter;
import com.alekso.budget.ui.BaseView;

import java.util.List;

/**
 * Created by alekso on 13/05/2017.
 */

public interface AccountsContract {
    interface Presenter extends BasePresenter {
        /**
         * Callback when accounts retrieved by CursorLoader
         *
         * @param accountsCursor
         */
        void onGetAccounts(Cursor accountsCursor);
    }

    interface View extends BaseView<Presenter> {
        /**
         * Shows or hides progress bar while loading data
         *
         * @param show
         */
        void showProgressBar(boolean show);

        /**
         * Shows list of accounts
         *
         * @param items
         */
        void showAccounts(List<Account> items);

        /**
         * Initializes loader to get accounts list
         */
        void createAccountsLoader();
    }
}
