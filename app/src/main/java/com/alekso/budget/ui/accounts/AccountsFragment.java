package com.alekso.budget.ui.accounts;

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
import com.alekso.budget.databinding.FragmentAccountsBinding;
import com.alekso.budget.model.Account;
import com.alekso.budget.source.Repository;
import com.alekso.budget.source.local.DbContract;
import com.alekso.budget.source.local.LocalDataSourceImpl;
import com.alekso.budget.source.remote.RemoteDataSourceImpl;

import java.util.List;

/**
 * Created by alekso on 13/05/2017.
 */

public class AccountsFragment extends Fragment implements AccountsContract.View,
        LoaderManager.LoaderCallbacks<Cursor> {
    public static final String TAG = App.fullTag(AccountsFragment.class.getSimpleName());
    private static final boolean DEBUG = true;
    private static final int LOADER_GET_ACCOUNTS = 1;

    private FragmentAccountsBinding mViewBinding;
    private AccountsContract.Presenter mPresenter;
    private AccountsAdapter mAdapter;

    /**
     * Default constructor
     */
    public AccountsFragment() {
        if (DEBUG) Log.d(TAG, "constructor()");
    }

    /**
     * @return
     */
    public static AccountsFragment newInstance() {
        AccountsFragment fragment = new AccountsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (DEBUG) Log.d(TAG, "onCreate(savedInstanceState: " + savedInstanceState + ")");

        super.onCreate(savedInstanceState);
        mAdapter = new AccountsAdapter(new AccountsAdapter.ItemClickHandler() {
            @Override
            public void onClick(long id) {
                if (DEBUG) Log.d(TAG, "account #" + id + " has clicked");
            }
        });

        mPresenter = new AccountsPresenter(
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

        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_accounts, container, false);
        return mViewBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (DEBUG)
            Log.d(TAG, "onViewCreated(view: " + view + "; savedInstanceState: " + savedInstanceState + ")");

        super.onViewCreated(view, savedInstanceState);

        mViewBinding.rvAccounts.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mViewBinding.rvAccounts.setAdapter(mAdapter);

        mPresenter.start();
    }

    @Override
    public void setPresenter(AccountsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressBar(boolean show) {

    }

    @Override
    public void showAccounts(List<Account> items) {
        mAdapter.setData(items);
    }

    @Override
    public void createAccountsLoader() {
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
                        DbContract.AccountEntry.CONTENT_URI,
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
        if (DEBUG) Log.d(TAG, "onLoaderReset(loader:" + loader + ")");
    }
}
