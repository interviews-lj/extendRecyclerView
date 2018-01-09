package com.example.lj.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Generic view holder
 */
public class GenericViewHolder<T> extends RecyclerView.ViewHolder {

    OnItemClick<T> mOnItemClick;
    OnItemLongClick<T> mOnItemLongClick;
    T item;
    int position;

    public GenericViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(mOnClickListener);
        itemView.setOnLongClickListener(mOnLongClickListener);
    }

    final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onItemClick(v);
            if (mOnItemClick != null) {
                mOnItemClick.onClick(position, item);
            }
        }
    };

    final View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            onItemLongClick(v);
            if (mOnItemLongClick != null) {
                mOnItemLongClick.onLongClick(position, item);
            }
            return true;
        }
    };

    void onItemLongClick(View view) {
    }

    void onItemClick(View view) {
    }

    public interface OnItemLongClick<T> {
        void onLongClick(int position, T item);
    }

    public interface OnItemClick<T> {
        void onClick(int position, T item);
    }

    void setItem(T item) {
        this.item = item;
    }

    void setPosition(int position) {
        this.position = position;
    }

    public void setOnItemClickListener(OnItemClick<T> onItemClickListener) {
        mOnItemClick = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClick<T> onItemLongClickListener) {
        mOnItemLongClick = onItemLongClickListener;
    }

}
