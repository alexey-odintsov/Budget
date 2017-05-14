package com.alekso.budget.ui.accounts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alekso.budget.R;
import com.alekso.budget.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekso on 13/05/2017.
 */

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

    private final ItemClickHandler mItemClickHandler;
    private List<Account> mAccountsList = new ArrayList<>();

    /* package */ AccountsAdapter(ItemClickHandler clickHandler) {
        mItemClickHandler = clickHandler;
    }

    @Override
    public AccountsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountsAdapter.ViewHolder holder, int position) {
        Account account = mAccountsList.get(position);
        holder.mTextViewName.setText(account.getName());
    }

    @Override
    public int getItemCount() {
        return mAccountsList == null ? 0 : mAccountsList.size();
    }

    public void setData(List<Account> accounts) {
        mAccountsList = accounts;
        notifyDataSetChanged();
    }

    /**
     *
     */
    public interface ItemClickHandler {
        /**
         * Callback when an item is clicked
         *
         * @param id
         */
        void onClick(long id);
    }

    /**
     *
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTextViewName;

        ViewHolder(View view) {
            super(view);
            mTextViewName = (TextView) view.findViewById(R.id.tv_name);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            Account account = mAccountsList.get(adapterPosition);
            mItemClickHandler.onClick(account.getId());
        }
    }
}
