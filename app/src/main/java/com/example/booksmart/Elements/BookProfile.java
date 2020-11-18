package com.example.booksmart.Elements;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;

public class BookProfile extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_profile);

        int bookId = getIntent().getIntExtra("bookId", -1);
        Book book;

        if (bookId != -1) {
            book = MainActivity.data.getBookFromId(bookId);
            if (book != null) {
                TextView bName = findViewById(R.id.name);
                TextView bAuthor = findViewById(R.id.bAuthor);
                TextView bYear = findViewById(R.id.bYear);

                TextView giverFName = findViewById(R.id.giverFName1);
                TextView giverLName = findViewById(R.id.giverLName);
                TextView giverEmail = findViewById(R.id.giverEmail);
                TextView giverMobile = findViewById(R.id.giverMobile);

                bName.setText(book.getName());
                bAuthor.setText(book.getAuthor().getFullName());
                bYear.setText(Integer.toString(book.getYear()));

                giverFName.setText(book.getGiver().getName().getFirstName());
                giverLName.setText(book.getGiver().getName().getLastName());
                giverEmail.setText(book.getGiver().getEmail());
                giverMobile.setText(Long.toString(book.getGiver().getMobile()));

            } else {
                Log.i("Book ID received", "Invalid");
            }
        } else {
            Log.i("Book received", "NULL");
        }

    }
}