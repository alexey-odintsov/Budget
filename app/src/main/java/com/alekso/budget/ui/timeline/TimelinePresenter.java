package com.alekso.budget.ui.timeline;

import android.database.Cursor;
import android.util.Log;

import com.alekso.budget.App;
import com.alekso.budget.model.decorators.TimelineItem;
import com.alekso.budget.model.decorators.TimelineItemsReader;
import com.alekso.budget.source.Repository;

import java.util.List;

/**
 * Created by alekso on 01/07/2017.
 */

public class TimelinePresenter implements TimelineContract.Presenter {
    private static final boolean DEBUG = true;
    private static final String TAG = App.fullTag(TimelinePresenter.class.getSimpleName());

    private final Repository mRepository;
    private final TimelineContract.View mView;

    public TimelinePresenter(Repository repository, TimelineContract.View view) {
        if (DEBUG) Log.d(TAG, "constructor(repository: " + repository + "; view: " + view + ")");

        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (DEBUG) Log.d(TAG, "start()");

        mView.createLoaders();
    }

    @Override
    public void onGetTimelineItems(Cursor cursor) {
        if (DEBUG) Log.d(TAG, "onGetTimelineItems(data: " + cursor + ")");

        List<TimelineItem> items = TimelineItemsReader.fromCursor(cursor);
        mView.showItems(items);
    }
}
