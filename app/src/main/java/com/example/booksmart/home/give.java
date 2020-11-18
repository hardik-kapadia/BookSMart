package com.example.booksmart.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.Elements.Categories;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;
import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;

import java.util.ArrayList;

public class give extends AppCompatActivity {

    public void give(View view) {

        TextView bookN = (TextView) findViewById(R.id.bookName);
        TextView authorN = (TextView) findViewById(R.id.authorName);
        TextView yearN = (TextView) findViewById(R.id.bookYear);
        Spinner categorySelect = (Spinner) findViewById(R.id.categorySelector);

        String bookName = bookN.getText().toString();
        String authorName = authorN.getText().toString();
        String year = yearN.getText().toString();
        final Categories[] category = new Categories[1];

        ArrayAdapter<Categories> categoryOptions = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, MainActivity.data.getCategories());
        categorySelect.setAdapter(categoryOptions);


        categorySelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Categories selected = (Categories) parent.getItemAtPosition(position);
                category[0] = selected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                category[0] = null;
            }
        });

        User giver = MainActivity.data.getCurrentUser();

        if (!bookName.isEmpty()) {
            if (!authorName.isEmpty()) {
                if (!year.isEmpty()) {
                    if (category[0] != null) {
                        Book book = new Book(bookName, new Person(authorName), giver, Integer.parseInt(year), -1, category[0]);
                    }
                }
            }
        }


    }

    Intent i;

    public void goToHome(View view) {
        i = new Intent(this, Home.class);
        startActivity(i);
    }

    public void goToSearch(View view) {
        i = new Intent(this, Search.class);
        startActivity(i);
    }

    public void goToProfile(View view) {
        i = new Intent(this, profile.class);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give);
    }
}