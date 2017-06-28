package com.alekso.budget.ui.review;

import android.database.Cursor;

import com.alekso.budget.model.Account;
import com.alekso.budget.ui.BasePresenter;
import com.alekso.budget.ui.BaseView;

import java.util.List;

/**
 * Created by alekso on 22/06/2017.
 * <p>
 * Main Fragment where brief information about all accounts, plannings and other functionality is displayed.
 */

public interface ReviewContract {
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
        void createLoaders();
    }
}
