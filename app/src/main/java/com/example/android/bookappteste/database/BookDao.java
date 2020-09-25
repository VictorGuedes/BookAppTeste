package com.example.android.bookappteste.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.bookappteste.data.models.Item;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;


@Dao
public interface BookDao {

    @Insert
    Completable insertBook(Item itemBook);

    @Delete
    Completable deletBook(Item itemBook);

    @Query("Select * from Books")
    Flowable<List<Item>> getAllFavoriteBooks();

    @Query("Select * from Books where id = :id")
    Flowable<List<Item>> getBookById(String id);

}
