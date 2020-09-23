package com.example.android.bookappteste.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.android.bookappteste.data.models.Item;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    void insertBook(Item itemBook);

    @Delete
    void deletBook(Item itemBook);

    @Query("Select * from Books")
    LiveData<List<Item>> getAllFavoriteBooks();

    @Query("Select * from Books where id = :id")
    LiveData<List<Item>> getBookById(String id);

}
