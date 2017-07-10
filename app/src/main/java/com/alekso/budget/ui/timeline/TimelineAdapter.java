package com.alekso.budget.ui.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alekso.budget.R;
import com.alekso.budget.model.decorators.TimelineItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alekso on 01/07/2017.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy H:mm:ss");

    private final TimelineAdapter.ItemClickHandler mItemClickHandler;
    private List<TimelineItem> mItemsList = new ArrayList<>();

    /* package */ TimelineAdapter(TimelineAdapter.ItemClickHandler clickHandler) {
        mItemClickHandler = clickHandler;
    }

    @Override
    public TimelineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_timeline, parent, false);
        return new TimelineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimelineAdapter.ViewHolder holder, int position) {
        TimelineItem item = mItemsList.get(position);
        holder.mTextViewId.setText("#" + String.valueOf(item.getTransaction().getId()));
        holder.mTextViewAccount.setText(String.valueOf(item.getAccountName()));
        holder.mTextViewCategory.setText(String.valueOf(item.getTransaction().getCategoryId()));
        holder.mTextViewAmount.setText(String.valueOf(item.getTransaction().getAmount()));
        holder.mTextViewBalance.setText(String.valueOf(item.getTransaction().getBalance()));
        holder.mTextViewDateTime.setText(dateFormat.format(new Date(item.getTransaction().getDateTime())));
    }

    @Override
    public int getItemCount() {
        return mItemsList == null ? 0 : mItemsList.size();
    }

    public void setData(List<TimelineItem> accounts) {
        mItemsList = accounts;
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
        TextView mTextViewId;
        TextView mTextViewAccount;
        TextView mTextViewCategory;
        TextView mTextViewAmount;
        TextView mTextViewBalance;
        TextView mTextViewDateTime;

        ViewHolder(View view) {
            super(view);
            mTextViewId = (TextView) view.findViewById(R.id.tv_id);
            mTextViewAccount = (TextView) view.findViewById(R.id.tv_account);
            mTextViewCategory = (TextView) view.findViewById(R.id.tv_category);
            mTextViewAmount = (TextView) view.findViewById(R.id.tv_amount);
            mTextViewBalance = (TextView) view.findViewById(R.id.tv_balance);
            mTextViewDateTime = (TextView) view.findViewById(R.id.tv_datetime);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            TimelineItem item = mItemsList.get(adapterPosition);
            mItemClickHandler.onClick(item.getTransaction().getId());
        }
    }
}