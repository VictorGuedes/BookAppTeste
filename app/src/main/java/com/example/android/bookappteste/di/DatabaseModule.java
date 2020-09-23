package com.example.android.bookappteste.di;

import android.app.Application;

import androidx.room.Room;

import com.example.android.bookappteste.database.BookDao;
import com.example.android.bookappteste.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static Database provideBookDB(Application application){

        return Room.databaseBuilder(application, Database.class, "My Favorites Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static BookDao provideBookDao(Database database){
        return database.bookDao();
    }

}
