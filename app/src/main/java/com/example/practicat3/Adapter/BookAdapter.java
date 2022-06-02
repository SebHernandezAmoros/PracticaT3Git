package com.example.practicat3.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicat3.BooksActivity;
import com.example.practicat3.DetailActivity;
import com.example.practicat3.Entities.Book;
import com.example.practicat3.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.StringViewHolder>{
    List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookAdapter.StringViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent,false);
        StringViewHolder vh = new StringViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.StringViewHolder holder, int position) {
        View view = holder.itemView;

        Book book = books.get(position);
        TextView tvTitulo =view.findViewById(R.id.tvTitulo);
        ImageView imageView = view.findViewById(R.id.ivImg);

        View Ly = view.findViewById(R.id.llEsquema);

        tvTitulo.setText(book.titulo);
        Picasso.get()
                .load(book.imagen)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(imageView);
        Ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                String bookJson = new Gson().toJson(book);
                intent.putExtra("Libro",bookJson);

                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class StringViewHolder extends RecyclerView.ViewHolder{
        public StringViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
