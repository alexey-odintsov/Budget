package com.alekso.budget.ui.review;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alekso.budget.R;
import com.alekso.budget.model.decorators.ReviewAccount;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekso on 13/05/2017.
 */

public class ReviewAccountsAdapter extends RecyclerView.Adapter<ReviewAccountsAdapter.ViewHolder> {

    private final ItemClickHandler mItemClickHandler;
    private List<ReviewAccount> mAccountsList = new ArrayList<>();

    /* package */ ReviewAccountsAdapter(ItemClickHandler clickHandler) {
        mItemClickHandler = clickHandler;
    }

    @Override
    public ReviewAccountsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_review_account, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewAccountsAdapter.ViewHolder holder, int position) {
        ReviewAccount account = mAccountsList.get(position);
        holder.mTextViewName.setText(account.getAccount().getName());
        holder.mTextViewBalance.setText(String.valueOf(account.getBalance()));
    }

    @Override
    public int getItemCount() {
        return mAccountsList == null ? 0 : mAccountsList.size();
    }

    public void setData(List<ReviewAccount> accounts) {
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
        TextView mTextViewBalance;

        ViewHolder(View view) {
            super(view);
            mTextViewName = (TextView) view.findViewById(R.id.tv_name);
            mTextViewBalance = (TextView) view.findViewById(R.id.tv_balance);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            ReviewAccount account = mAccountsList.get(adapterPosition);
            mItemClickHandler.onClick(account.getAccount().getId());
        }
    }
}
