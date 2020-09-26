package com.example.android.bookappteste.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.android.bookappteste.data.models.BookResponse;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.repository.BookDataSourceFactory;
import com.example.android.bookappteste.repository.BookRepository;
import com.example.android.bookappteste.repository.DataSourceBook;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class BookViewModel extends ViewModel {

    private BookRepository repository;
    private MutableLiveData<List<Item>> bookList = new MutableLiveData<>();
    private MutableLiveData<List<Item>> favoriteBooks = new MutableLiveData<>();

    private MutableLiveData<Boolean> dataWasInsert = new MutableLiveData<>();

    private MutableLiveData<Boolean> dataWasDeleted = new MutableLiveData<>();

    private BookDataSourceFactory bookDataSourceFactory;
    private LiveData<PagedList<Item>> bookPagedList;


    @ViewModelInject
    public BookViewModel(BookRepository repository){
        this.repository = repository;
        //this.bookDataSourceFactory = new BookDataSourceFactory(repository);
    }

    public MutableLiveData<List<Item>> getBookList() {
        return bookList;
    }

    public MutableLiveData<List<Item>> getFavoriteBooks() {
        return favoriteBooks;
    }

    public MutableLiveData<Boolean> getDataWasInsert() {
        return dataWasInsert;
    }

    public MutableLiveData<Boolean> getDataWasDeleted() {
        return dataWasDeleted;
    }

    public LiveData<PagedList<Item>> getBookPagedList() {
        return bookPagedList;
    }

    public void insertBook(Item book){
        repository.insertBook(book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> dataWasInsert.setValue(true),
                        throwable -> dataWasInsert.setValue(false)
                );
    }

    public void deleteBook(Item book){
        repository.deleteBook(book).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> dataWasDeleted.setValue(true),
                        throwable -> dataWasDeleted.setValue(false)
                );;
    }

    public void getFavoriteBooksDB(){
        repository.getFavoriteBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favorites -> {favoriteBooks.setValue(favorites);},
                        error-> {Log.e("ERRO", error.getMessage());}
                );
    }

    public void getBookListFromService(){
       /* PagedList.Config pagedListConfig = new PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(40).build();
        bookPagedList = (new LivePagedListBuilder(bookDataSourceFactory, pagedListConfig)).build();*/

        repository.getBook()
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
                        result -> bookList.setValue(result),
                        error-> Log.e("ERRO", error.getMessage() )
                );
    }


}
