package com.alekso.budget.ui.review;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekso.budget.App;
import com.alekso.budget.R;
import com.alekso.budget.databinding.FragmentReviewBinding;
import com.alekso.budget.model.decorators.ReviewAccount;
import com.alekso.budget.source.Repository;
import com.alekso.budget.source.local.DbContract;
import com.alekso.budget.source.local.LocalDataSourceImpl;
import com.alekso.budget.source.remote.RemoteDataSourceImpl;

import java.util.List;

/**
 * Created by alekso on 22/06/2017.
 */

public class ReviewFragment extends Fragment implements ReviewContract.View,
        LoaderManager.LoaderCallbacks<Cursor> {

    public static final String TAG = App.fullTag(ReviewFragment.class.getSimpleName());
    private static final boolean DEBUG = true;
    private static final int LOADER_GET_ACCOUNTS = 1;

    private FragmentReviewBinding mViewBinding;
    private ReviewContract.Presenter mPresenter;
    private ReviewAccountsAdapter mAdapter;

    /**
     * Default constructor
     */
    public ReviewFragment() {
        if (DEBUG) Log.d(TAG, "constructor()");
    }

    /**
     * @return
     */
    public static ReviewFragment newInstance() {
        ReviewFragment fragment = new ReviewFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ReviewAccountsAdapter(new ReviewAccountsAdapter.ItemClickHandler() {
            @Override
            public void onClick(long id) {
                if (DEBUG) Log.d(TAG, "account #" + id + " has clicked");
            }
        });

        mPresenter = new ReviewPresenter(
                Repository.getInstance(
                        LocalDataSourceImpl.getInstance(getActivity().getContentResolver()),
                        RemoteDataSourceImpl.getInstance(getContext())
                ), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false);
        return mViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewBinding.rvAccounts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mViewBinding.rvAccounts.setAdapter(mAdapter);

        mPresenter.start();
    }

    @Override
    public void setPresenter(ReviewContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar(boolean show) {

    }

    @Override
    public void showAccounts(List<ReviewAccount> items) {
        mAdapter.setData(items);
    }

    @Override
    public void createLoaders() {
        if (getLoaderManager().getLoader(LOADER_GET_ACCOUNTS) == null) {
            getLoaderManager().initLoader(LOADER_GET_ACCOUNTS, null, this);
        } else {
            getLoaderManager().restartLoader(LOADER_GET_ACCOUNTS, null, this);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (DEBUG) Log.d(TAG, "onCreateLoader(id: " + id + "; args: " + args + ")");
        switch (id) {
            case LOADER_GET_ACCOUNTS:
                Log.d(TAG, "uri: " + DbContract.AccountEntry.CONTENT_URI);
                return new CursorLoader(getContext(),
                        DbContract.REVIEW_ACCOUNTS_CONTENT_URI,
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
            case LOADER_GET_ACCOUNTS:
                mPresenter.onGetAccounts(data);
                break;

            default:
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
