package com.alekso.budget.ui.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alekso.budget.R;
import com.alekso.budget.model.decorators.TimelineItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekso on 01/07/2017.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private final TimelineAdapter.ItemClickHandler mItemClickHandler;
    private List<TimelineItem> mItemsList = new ArrayList<>();

    /* package */ TimelineAdapter(TimelineAdapter.ItemClickHandler clickHandler) {
        mItemClickHandler = clickHandler;
    }

    @Override
    public TimelineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_account, parent, false);
        return new TimelineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimelineAdapter.ViewHolder holder, int position) {
        TimelineItem item = mItemsList.get(position);
        holder.mTextViewId.setText(item.getTransaction().getId() + "");
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

        ViewHolder(View view) {
            super(view);
            mTextViewId = (TextView) view.findViewById(R.id.tv_id);
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