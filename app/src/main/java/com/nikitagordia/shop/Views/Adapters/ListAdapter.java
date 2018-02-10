package com.nikitagordia.shop.Views.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikitagordia.shop.Presenters.BrandEditPresenter;
import com.nikitagordia.shop.R;
import com.nikitagordia.shop.Data.Models.ListableItem;
import com.nikitagordia.shop.Views.Activities.BrandEditActivity;
import com.nikitagordia.shop.Views.Activities.PCEditActivity;

import java.util.List;

/**
 * Created by nikitagordia on 2/7/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {

    public static final String EXTRA_ID = "com.nikitagordia.shop.View.Activities.extraId";

    private List<ListableItem> mList;
    private Context mContext;
    private ClickItemListener mListener;

    public ListAdapter(Context context, List<ListableItem> list) {
        mContext = context;
        mList = list;
    }

    public void addNewItem(ListableItem item) {
        mList.add(0, item);
        notifyItemInserted(0);
    }

    public ListableItem deleteFirstItem() {
        if (mList.isEmpty()) return null;
        ListableItem item = mList.get(0);
        mList.remove(0);
        notifyItemRemoved(0);
        return item;
    }

    public void onUpdateItem(ListableItem item) {
        int pos = 0;
        for (ListableItem i : mList) {
            if (i.getId() == item.getId()) {
                mList.set(pos, item);
                notifyItemChanged(pos);
                break;
            }
            pos++;
        }
    }

    public void registerClickItemListener(ClickItemListener listener) {
        mListener = listener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(mContext), parent);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private int mId;

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item, parent, false));

            mTextView = (TextView) itemView.findViewById(R.id.text);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onClick(mId);
                }
            });
        }

        public void bind(ListableItem item) {
            mTextView.setText(item.getTitle());
            mId = item.getId();
        }
    }
}
