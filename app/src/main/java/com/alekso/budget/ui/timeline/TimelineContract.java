package com.alekso.budget.ui.timeline;

import android.database.Cursor;

import com.alekso.budget.model.decorators.ReviewAccount;
import com.alekso.budget.model.decorators.TimelineItem;
import com.alekso.budget.ui.BasePresenter;
import com.alekso.budget.ui.BaseView;
import com.alekso.budget.ui.review.ReviewContract;

import java.util.List;

/**
 * Created by alekso on 14/05/2017.
 */

public class TimelineContract {
    interface Presenter extends BasePresenter {
        /**
         * Callback when timeline items retrieved by CursorLoader
         *
         * @param cursor
         */
        void onGetTimelineItems(Cursor cursor);
    }

    interface View extends BaseView<TimelineContract.Presenter> {
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
        void showItems(List<TimelineItem> items);

        /**
         * Initializes loader to get accounts list
         */
        void createLoaders();
    }
}
