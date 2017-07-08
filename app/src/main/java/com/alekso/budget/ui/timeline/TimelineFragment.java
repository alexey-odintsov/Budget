package com.alekso.budget.ui.timeline;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekso.budget.App;
import com.alekso.budget.R;
import com.alekso.budget.databinding.FragmentTimelineBinding;
import com.alekso.budget.model.decorators.TimelineItem;
import com.alekso.budget.source.Repository;
import com.alekso.budget.source.local.BudgetProvider;
import com.alekso.budget.source.local.DbContract;
import com.alekso.budget.source.local.LocalDataSourceImpl;
import com.alekso.budget.source.remote.RemoteDataSourceImpl;
import com.alekso.budget.ui.accounts.AccountsAdapter;
import com.alekso.budget.ui.accounts.AccountsPresenter;

import java.util.List;

/**
 * Created by alekso on 01/07/2017.
 */

public class TimelineFragment extends Fragment implements TimelineContract.View,
        LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = App.fullTag(TimelineFragment.class.getSimpleName());
    private static final boolean DEBUG = true;
    private static final int LOADER_GET_ITEMS = 1;

    private FragmentTimelineBinding mViewBinding;
    private TimelineContract.Presenter mPresenter;
    private TimelineAdapter mAdapter;

    /**
     * Default constructor
     */
    public TimelineFragment() {
        if (DEBUG) Log.d(TAG, "constructor()");
    }

    /**
     * @return
     */
    public static TimelineFragment newInstance() {
        TimelineFragment fragment = new TimelineFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (DEBUG) Log.d(TAG, "onCreate(savedInstanceState: " + savedInstanceState + ")");

        super.onCreate(savedInstanceState);
        mAdapter = new TimelineAdapter(new TimelineAdapter.ItemClickHandler() {
            @Override
            public void onClick(long id) {
                if (DEBUG) Log.d(TAG, "item #" + id + " has clicked");
            }
        });

        mPresenter = new TimelinePresenter(
                Repository.getInstance(
                        LocalDataSourceImpl.getInstance(getActivity().getContentResolver()),
                        RemoteDataSourceImpl.getInstance(getContext())
                ), this);

//        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (DEBUG)
            Log.d(TAG, "onCreateView(inflater: " + inflater + "; container: " + container + "; savedInstanceState: " + savedInstanceState + ")");

        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timeline, container, false);
        return mViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (DEBUG)
            Log.d(TAG, "onViewCreated(view: " + view + "; savedInstanceState: " + savedInstanceState + ")");

        super.onViewCreated(view, savedInstanceState);

        mViewBinding.rvItems.setLayoutManager(new LinearLayoutManager(getContext()));
        mViewBinding.rvItems.setAdapter(mAdapter);

        mPresenter.start();
    }

    @Override
    public void setPresenter(TimelineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar(boolean show) {

    }

    @Override
    public void showItems(List<TimelineItem> items) {
        mAdapter.setData(items);
    }

    @Override
    public void createLoaders() {
        if (getLoaderManager().getLoader(LOADER_GET_ITEMS) == null) {
            getLoaderManager().initLoader(LOADER_GET_ITEMS, null, this);
        } else {
            getLoaderManager().restartLoader(LOADER_GET_ITEMS, null, this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (DEBUG) Log.d(TAG, "onCreateLoader(id: " + id + "; args: " + args + ")");
        switch (id) {
            case LOADER_GET_ITEMS:
                Log.d(TAG, "uri: " + DbContract.TransactionEntry.CONTENT_URI);
                return new CursorLoader(getContext(),
                        DbContract.TIMELINE_CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (DEBUG) Log.d(TAG, "onLoadFinished(loader: " + loader + "; cursor: " + data + ")");
        switch (loader.getId()) {
            case LOADER_GET_ITEMS:
                mPresenter.onGetTimelineItems(data);
                break;

            default:
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
