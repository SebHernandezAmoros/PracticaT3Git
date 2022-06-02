package com.example.practicat3.Service;

import com.example.practicat3.Entities.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BookService {
    @GET("books")
    Call<List<Book>> GetBooks();
    @POST("books")
    Call<Book> createBook(@Body Book book);

}
