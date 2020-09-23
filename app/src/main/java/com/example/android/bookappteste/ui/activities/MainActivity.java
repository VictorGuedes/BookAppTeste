package com.example.android.bookappteste.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bookappteste.R;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.databinding.ActivityMainBinding;
import com.example.android.bookappteste.ui.adapter.RecyclerBookListAdapter;
import com.example.android.bookappteste.viewmodel.BookViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private BookViewModel bookViewModel;
    private RecyclerView recyclerView;
    private ActivityMainBinding activityMainBinding;
    private RecyclerBookListAdapter adapter;

    private boolean requestFavorite = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeRecycler();

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);

        observeData();
        bookViewModel.getBookListFromService();

    }

    private void initializeRecycler(){
        recyclerView = activityMainBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        List<Item> list = new ArrayList<>();
        adapter = new RecyclerBookListAdapter(list);

        recyclerView.setAdapter(adapter);

    }

    private void observeData(){
        bookViewModel.getBookList().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                Log.d("Itens", items.toString());
                adapter.setItems(items);
            }
        });
    }
}