package com.example.android.bookappteste.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.android.bookappteste.data.models.BookResponse;
import com.example.android.bookappteste.data.models.Item;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DataSourceBook extends PageKeyedDataSource<Integer, Item> {

    private BookRepository repository;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Item> callback) {

        repository.getBookPaginated(0, 1)
                .subscribeOn(Schedulers.io())
                .map(new Function<BookResponse, List<Item>>() {
                    @Override
                    public List<Item> apply(BookResponse bookResponse) throws Throwable {
                        List<Item> listBooks = bookResponse.getItems();

                        return listBooks;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> callback.onResult(result, null, 1),
                        error -> Log.e("ERRO", error.getMessage())
                );
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        repository.getBookPaginated(0, 1)
                .subscribeOn(Schedulers.io())
                .map(new Function<BookResponse, List<Item>>() {
                    @Override
                    public List<Item> apply(BookResponse bookResponse) throws Throwable {
                        List<Item> listBooks = bookResponse.getItems();

                        return listBooks;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> callback.onResult(result, 1),
                        error -> Log.e("ERRO", error.getMessage())
                );
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        repository.getBookPaginated(0, 1)
                .subscribeOn(Schedulers.io())
                .map(new Function<BookResponse, List<Item>>() {
                    @Override
                    public List<Item> apply(BookResponse bookResponse) throws Throwable {
                        List<Item> listBooks = bookResponse.getItems();

                        return listBooks;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> callback.onResult(result, 1),
                        error -> Log.e("ERRO", error.getMessage())
                );
    }
}
