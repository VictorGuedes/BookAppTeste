package com.example.android.bookappteste.repository;

import androidx.paging.DataSource;

import com.example.android.bookappteste.data.models.Item;

public class BookDataSourceFactory extends DataSource.Factory<Integer, Item> {

    private BookRepository repository;

    public BookDataSourceFactory(BookRepository repository){
       this.repository = repository;
    }

    @Override
    public DataSource<Integer, Item> create() {
        return new DataSourceBook();
    }
}
