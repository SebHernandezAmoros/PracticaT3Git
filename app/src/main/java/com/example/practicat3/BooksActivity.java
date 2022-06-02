package com.example.practicat3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.practicat3.Adapter.BookAdapter;
import com.example.practicat3.Entities.Book;
import com.example.practicat3.Factories.RetrofitFactory;
import com.example.practicat3.Service.BookService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BooksActivity extends AppCompatActivity {
    List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        Retrofit retrofit = RetrofitFactory.build();
        BookService service =retrofit.create(BookService.class);

        Call<List<Book>> call =service.GetBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(!response.isSuccessful()){
                    Log.i("PRUEBA","Error de aplicacion");
                }else{
                    Log.i("PRUEBA","Respuesta correcta");
                    Log.i("PRUEBA",new Gson().toJson(response.body()));

                    //Obtencion de lista y envio al adapter
                    books = response.body();

                    BookAdapter adapter = new BookAdapter(books);
                    //Obtencion del recyclerview y envio del adapter
                    RecyclerView rv = findViewById(R.id.rvLista);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.i("PRUEBA","No pudo conectar con el servicio");
            }
        });

    }
}