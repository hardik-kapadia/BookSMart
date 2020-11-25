package com.example.booksmart.profiles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;

public class BookProfile extends AppCompatActivity {

    Book book;

    public void removeBook(View view) {
        MainActivity.data.removeBook(book);
        MainActivity.data.getCurrentUser().removeBook(book);
        Log.i("Book removed: ", book.getName());
        Log.i("Books after removal", MainActivity.data.getAllBooks().toString());
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_profile);

        int bookId = getIntent().getIntExtra("bookId", -1);

        Button remover = findViewById(R.id.removeButton);
        remover.setEnabled(false);
        remover.setAlpha(0.8f);
        remover.setBackgroundColor(Color.parseColor("#404040"));

        if (bookId != -1) {
            book = MainActivity.data.getBookFromId(bookId);
            if (book != null) {
                TextView bName = findViewById(R.id.name);
                TextView bAuthor = findViewById(R.id.bAuthor);
                TextView bYear = findViewById(R.id.bYear);
                TextView bCat = findViewById(R.id.bCat);

                TextView giverFName = findViewById(R.id.giverFName1);
                TextView giverEmail = findViewById(R.id.giverEmail);
                TextView giverMobile = findViewById(R.id.giverMobile);

                if (MainActivity.data.getCurrentUser().getUniqueId() == book.getGiverId()) {
                    remover.setEnabled(true);
                    remover.setBackgroundColor(Color.parseColor("#2196F3"));
                    remover.setAlpha(1.0f);
                }

                Log.i("Remove Button", Boolean.toString(remover.isEnabled()));

                bName.setText(book.getName());
                bAuthor.setText(book.getAuthor().getFullName());
                bYear.setText(Integer.toString(book.getYear()));
                bCat.setText(book.getCategory());

                giverFName.setText(book.getGiver().getName().getFullName());
                giverEmail.setText(book.getGiver().getEmail());
                giverMobile.setText(book.getGiver().getMobile());

            } else {
                Log.i("Book ID received", "Invalid");
            }
        } else {
            Log.i("Book received", "NULL");
        }

    }
}