package com.example.android.bookappteste.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bookappteste.R;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.databinding.RecyclerFavoritesItemBinding;

import java.util.List;

public class RecyclerFavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> items;

    public RecyclerFavoriteAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerFavoritesItemBinding favoritesItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycler_favorites_item,
                parent,
                false
        );

        return new RecyclerFavoriteAdapter.ViewHolder(favoritesItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = items.get(position);

        RecyclerFavoriteAdapter.ViewHolder itemViewHolder = (RecyclerFavoriteAdapter.ViewHolder) holder;
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

        private RecyclerFavoritesItemBinding favoritesItemBinding;

        public ViewHolder(@NonNull RecyclerFavoritesItemBinding favoritesItemBinding){
            super(favoritesItemBinding.getRoot());
            this.favoritesItemBinding = favoritesItemBinding;
        }

        void bindItem(Item item) {
            favoritesItemBinding.setModel(item);
            favoritesItemBinding.executePendingBindings();
        }
    }

}
