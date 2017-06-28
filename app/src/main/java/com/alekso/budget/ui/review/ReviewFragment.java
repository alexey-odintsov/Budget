package com.alekso.budget.ui.review;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekso.budget.App;
import com.alekso.budget.R;
import com.alekso.budget.databinding.FragmentReviewBinding;
import com.alekso.budget.model.Account;
import com.alekso.budget.source.Repository;
import com.alekso.budget.source.local.LocalDataSourceImpl;
import com.alekso.budget.source.remote.RemoteDataSourceImpl;
import com.alekso.budget.ui.accounts.AccountsPresenter;

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
    public void setPresenter(ReviewContract.Presenter presenter) {

    }

    @Override
    public void showProgressBar(boolean show) {

    }

    @Override
    public void showAccounts(List<Account> items) {

    }

    @Override
    public void createLoaders() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
