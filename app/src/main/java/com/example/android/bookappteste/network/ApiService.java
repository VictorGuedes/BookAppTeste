package com.example.android.bookappteste.network;

import com.example.android.bookappteste.data.models.BookResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("volumes?q=Android")
    Observable<BookResponse> getBook(
            @Query("startIndex") int startIndex,
            @Query("maxResults") int endPaginate
    );


}
