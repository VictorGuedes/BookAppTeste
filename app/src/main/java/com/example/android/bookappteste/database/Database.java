package com.example.android.bookappteste.database;

import androidx.room.RoomDatabase;
import com.example.android.bookappteste.data.models.Item;

@androidx.room.Database(entities = {Item.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract BookDao bookDao();

}
