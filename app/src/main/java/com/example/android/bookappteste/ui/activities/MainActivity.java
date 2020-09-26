package com.example.android.bookappteste.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.android.bookappteste.R;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.databinding.ActivityMainBinding;
import com.example.android.bookappteste.ui.adapter.MainRecyclerAdapterPaged;
import com.example.android.bookappteste.ui.adapter.RecyclerBookListAdapter;
import com.example.android.bookappteste.ui.fragments.FavoritesFragment;
import com.example.android.bookappteste.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private BookViewModel bookViewModel;
    private ActivityMainBinding activityMainBinding;
    private RecyclerBookListAdapter adapter;
    private FavoritesFragment bottomSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeRecycler();

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        initView();

        bookViewModel.getBookListFromService();
        bookViewModel.getFavoriteBooksDB();

        observeData();
    }

    private void initView(){
        bottomSheetFragment = new FavoritesFragment(bookViewModel);
    }

    private void initializeRecycler(){
        activityMainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        activityMainBinding.recyclerView.setHasFixedSize(true);

        List<Item> list = new ArrayList<>();
        adapter = new RecyclerBookListAdapter(list);

        activityMainBinding.recyclerView.setAdapter(adapter);

    }

    private void observeData(){
        bookViewModel.getBookList().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                Log.d("Itens", items.toString());
                adapter.setItems(items);
            }
        });

        /*bookViewModel.getBookPagedList().observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(PagedList<Item> items) {
                Log.d("Deu", items.toString());
                adapter.submitList(items);
            }
        });*/

    }

    public void openFavorites(View view) {
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}