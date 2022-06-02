package com.example.practicat3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.practicat3.Entities.Book;
import com.example.practicat3.Factories.RetrofitFactory;
import com.example.practicat3.Service.BookService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText etTitulo = findViewById(R.id.etTitulo);
        EditText etResumen = findViewById(R.id.etResumen);
        EditText etAutor = findViewById(R.id.etAutor);
        EditText etFecha = findViewById(R.id.etFecha);
        EditText etTienda1 = findViewById(R.id.etTienda1);
        EditText etTienda2 = findViewById(R.id.etTienda2);
        EditText etTienda3 = findViewById(R.id.etTienda3);
        EditText etImagen = findViewById(R.id.etImagen);
        Button btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetrofitFactory.build();
                BookService service = retrofit.create(BookService.class);

                Book book = new Book();
                book.titulo =String.valueOf(etTitulo.getText());
                book.resumen =String.valueOf(etResumen.getText());
                book.autor =String.valueOf(etAutor.getText());
                book.fecha_publicacion =String.valueOf(etFecha.getText());
                book.tienda_1 =String.valueOf(etTienda1.getText());
                book.tienda_2 =String.valueOf(etTienda2.getText());
                book.tienda_3 =String.valueOf(etTienda3.getText());
                book.imagen =String.valueOf(etImagen.getText());

                Call<Book> call = service.createBook(book);
                call.enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.isSuccessful()){
                            Log.i("PRUEBA",new Gson().toJson(response.body()));

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable t) {
                        Log.i("PRUEBA","No se pudo conectar");
                    }
                });
            }
        });




    }
}