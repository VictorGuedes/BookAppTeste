package com.example.android.bookappteste.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bookappteste.R;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.databinding.RecyclerBookItemBinding;

import java.util.List;

public class RecyclerBookListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items;

    public RecyclerBookListAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerBookItemBinding recyclerBookItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_book_item,
                parent,
                false
        );

        return new RecyclerBookListAdapter.ViewHolder(recyclerBookItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);

        ViewHolder itemViewHolder = (ViewHolder) holder;
        itemViewHolder.bindItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerBookItemBinding recyclerBookItemBinding;

        public ViewHolder(@NonNull RecyclerBookItemBinding recyclerBookItemBinding){
            super(recyclerBookItemBinding.getRoot());
            this.recyclerBookItemBinding = recyclerBookItemBinding;
        }

        void bindItem(Item item) {
            recyclerBookItemBinding.setModel(item);
            recyclerBookItemBinding.executePendingBindings();
        }
    }

}

