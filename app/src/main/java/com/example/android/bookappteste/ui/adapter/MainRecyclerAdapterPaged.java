package com.example.android.bookappteste.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bookappteste.R;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.databinding.RecyclerBookItemBinding;

public class MainRecyclerAdapterPaged extends PagedListAdapter<Item, MainRecyclerAdapterPaged.ViewHolder > {

    public MainRecyclerAdapterPaged(){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerBookItemBinding recyclerBookItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_book_item,
                parent,
                false
        );

        return new MainRecyclerAdapterPaged.ViewHolder(recyclerBookItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item bookItem = getItem(position);
        if (bookItem != null) holder.recyclerBookItemBinding.setModel(bookItem);

    }

    private static DiffUtil.ItemCallback<Item> DIFF_CALLBACK = new DiffUtil.ItemCallback<Item>(){
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerBookItemBinding recyclerBookItemBinding;

        public ViewHolder(@NonNull RecyclerBookItemBinding recyclerBookItemBinding){
            super(recyclerBookItemBinding.getRoot());
            this.recyclerBookItemBinding = recyclerBookItemBinding;
        }
    }

}
