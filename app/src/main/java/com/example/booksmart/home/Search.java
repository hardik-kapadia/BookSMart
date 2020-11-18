package com.example.booksmart.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.Elements.BookAdapter;
import com.example.booksmart.Elements.BookProfile;
import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    ListView resultView;
    public ArrayList<Book> matching;

    Intent i;

    public void goToGive(View view) {
        i = new Intent(getApplicationContext(), give.class);
        startActivity(i);
    }

    public void goToHome(View view) {
        i = new Intent(this, Home.class);
        startActivity(i);
    }

    public void goToProfile(View view) {
        i = new Intent(this, profile.class);
        startActivity(i);
    }

    public void getResults(View view) {

        resultView = findViewById(R.id.results);

        ArrayList<Book> matching = new ArrayList<>();
        String searched = ((TextView) findViewById(R.id.searching)).getText().toString();

        if (!searched.isEmpty()) {
            for (Book book : MainActivity.data.getAllBooks()) {
                if (book.matches(searched)) {
                    matching.add(book);
                }
            }
        } else {
            matching = new ArrayList<>(MainActivity.data.getAllBooks());
        }

        ArrayAdapter<Book> resultVals = new BookAdapter(this, 0, matching);

        resultView.setAdapter(resultVals);

        AdapterView.OnItemClickListener bookListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Book book = (Book) parent.getItemAtPosition(position);
                Log.i("Item Clicked", book.getName());
                Intent i = new Intent(Search.this, BookProfile.class);
                i.putExtra("bookId", book.getUniqueId());
                startActivity(i);
            }
        };

        resultView.setOnItemClickListener(bookListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}