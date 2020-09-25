package com.example.android.bookappteste.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.bookappteste.data.models.BookResponse;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.repository.BookRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class BookViewModel extends ViewModel {

    private BookRepository repository;
    private MutableLiveData<List<Item>> bookList = new MutableLiveData<>();
    private LiveData<List<Item>> favoriteBooks = null;

    private MutableLiveData<Boolean> dataWasInsert = new MutableLiveData<>();

    private MutableLiveData<Boolean> dataWasDeleted = new MutableLiveData<>();

    @ViewModelInject
    public BookViewModel(BookRepository repository){
        this.repository = repository;
        //favoriteBooks = repository.getFavoriteBooks();
    }

    public MutableLiveData<List<Item>> getBookList() {
        return bookList;
    }

    public LiveData<List<Item>> getFavoriteBooks() {
        return favoriteBooks;
    }

    public MutableLiveData<Boolean> getDataWasInsert() {
        return dataWasInsert;
    }

    public MutableLiveData<Boolean> getDataWasDeleted() {
        return dataWasDeleted;
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
        //favoriteBooks = repository.getFavoriteBooks();
    }

    public void getBookListFromService(){
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
