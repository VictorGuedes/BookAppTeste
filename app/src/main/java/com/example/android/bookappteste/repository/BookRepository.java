package com.example.android.bookappteste.repository;

import androidx.lifecycle.LiveData;

import com.example.android.bookappteste.data.models.BookResponse;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.database.BookDao;
import com.example.android.bookappteste.network.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class BookRepository {

    private ApiService apiService;
    private BookDao bookDao;

    @Inject
    public BookRepository(ApiService apiService, BookDao bookDao){
        this.apiService = apiService;
        this.bookDao = bookDao;
    }

    public Observable<BookResponse> getBook(){
        return apiService.getBook(0, 20);
    }


    public void deleteBook(Item item){
        bookDao.deletBook(item);
    }

    public void getBookById(String id){
        bookDao.getBookById(id);
    }

    public void insertBook(Item item){
        bookDao.insertBook(item);
    }

    public LiveData<List<Item>> getFavoriteBooks(){
        return bookDao.getAllFavoriteBooks();
    }


}
