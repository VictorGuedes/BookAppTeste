package com.example.android.bookappteste.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bookappteste.R;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.databinding.BottomSheetFavoriteBinding;
import com.example.android.bookappteste.ui.adapter.RecyclerBookListAdapter;
import com.example.android.bookappteste.ui.adapter.RecyclerFavoriteAdapter;
import com.example.android.bookappteste.viewmodel.BookViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoritesFragment extends BottomSheetDialogFragment {

    private BookViewModel bookViewModel;
    private BottomSheetFavoriteBinding favoriteBinding;

    private RecyclerFavoriteAdapter adapter;

    public FavoritesFragment(){}

    public FavoritesFragment(BookViewModel bookViewModel){
        this.bookViewModel = bookViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observeData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        favoriteBinding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_favorite, container, false);
        initializeRecycler();

        return favoriteBinding.getRoot();
    }

    private void initializeRecycler(){
        favoriteBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteBinding.recyclerView.setHasFixedSize(true);

        List<Item> list = new ArrayList<>();
        adapter = new RecyclerFavoriteAdapter(list);

        favoriteBinding.recyclerView.setAdapter(adapter);

    }

    private void observeData(){
        bookViewModel.getFavoriteBooks().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                if (items.isEmpty()) favoriteBinding.noDataTextView.setVisibility(View.VISIBLE);
                else  populateRecycler(items);

                Log.d("Itens", items.toString());
            }
        });
    }

    private void populateRecycler(List<Item> items){
        favoriteBinding.recyclerView.setVisibility(View.VISIBLE);
        adapter.setItems(items);
    }
}
