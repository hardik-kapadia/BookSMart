package com.example.booksmart.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    ListView resultView;

    ArrayAdapter<Book> resultVals;

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

        String searched = ((TextView) findViewById(R.id.searching)).toString();

        if (!searched.isEmpty()) {
            for (Book book : MainActivity.data.getAllBooks()) {
                if (book.matches(searched)) {
                    matching.add(book);
                }
            }
        } else {
            matching = new ArrayList<>(MainActivity.data.getAllBooks());
        }

        resultVals = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matching);
        resultView.setAdapter(resultVals);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }
}