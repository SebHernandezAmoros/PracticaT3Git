package com.example.practicat3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practicat3.Entities.Book;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String getlibroJson = getIntent().getStringExtra("Libro");
        Book book = new Gson().fromJson(getlibroJson,Book.class);

        TextView tvTituloD = findViewById(R.id.tvTituloD);
        TextView tvResumenD = findViewById(R.id.tvResumenD);
        TextView tvAutorD = findViewById(R.id.tvAutorD);
        TextView tvFechaD = findViewById(R.id.tvFechaD);
        TextView tvTienda1D = findViewById(R.id.tvTienda1D);
        TextView tvTienda2D = findViewById(R.id.tvTienda2D);
        TextView tvTienda3D = findViewById(R.id.tvTienda3D);
        ImageView imageView = findViewById(R.id.ivImgD);

        Picasso.get()
                .load(book.imagen)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(imageView);

        tvTituloD.setText(book.titulo);
        tvResumenD.setText(book.resumen);
        tvAutorD.setText(book.autor);
        tvFechaD.setText(book.fecha_publicacion);
        tvTienda1D.setText(book.tienda_1);
        tvTienda2D.setText(book.tienda_2);
        tvTienda3D.setText(book.tienda_3);

    }
}