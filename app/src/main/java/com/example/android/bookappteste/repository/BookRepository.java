package com.example.android.bookappteste.repository;

import com.example.android.bookappteste.data.models.BookResponse;
import com.example.android.bookappteste.data.models.Item;
import com.example.android.bookappteste.database.BookDao;
import com.example.android.bookappteste.network.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
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
        return apiService.getBook(0, 40);
    }

    public Observable<BookResponse> getBookPaginated(int start, int end){
        return apiService.getBook(start, end);
    }

    public Completable deleteBook(Item item){
        return bookDao.deletBook(item);
    }

    public Flowable<List<Item>> getBookById(String id){
        return  bookDao.getBookById(id);
    }

    public Completable insertBook(Item item){
        return bookDao.insertBook(item);
    }

    public Flowable<List<Item>> getFavoriteBooks(){
        return bookDao.getAllFavoriteBooks();
    }


}
