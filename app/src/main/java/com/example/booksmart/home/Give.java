package com.example.booksmart.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;
import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;
import com.example.booksmart.profiles.Profile;

public class Give extends AppCompatActivity {

    String category;
    Spinner categorySelect;
    User giver;
    Intent i;

    public void giveBook(View view) {

        TextView bookN = findViewById(R.id.bookName);
        TextView authorN = findViewById(R.id.authorName);
        TextView yearN = findViewById(R.id.bookYear);

        String bookName = bookN.getText().toString();
        String authorName = authorN.getText().toString();
        String year = yearN.getText().toString();


        giver = MainActivity.data.getCurrentUser();

        if (!bookName.isEmpty()) {
            if (!authorName.isEmpty()) {
                if (!year.isEmpty()) {
                    if (this.category != null) {
                        Book book = new Book(bookName, new Person(authorName), giver, Integer.parseInt(year), MainActivity.data.getRandomUniqueBookId(), category);
                        MainActivity.data.addBook(book);
                        MainActivity.data.getCurrentUser().addUserBook(book);
                        bookN.setText("");
                        authorN.setText("");
                        yearN.setText("");
                        categorySelect.setPrompt("Select a Category");

                        Log.i("Book added", book.getName());

                    } else Toast.makeText(this, "Category not selected", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "Year not selected", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(this, "Author not given", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(this, "Book Name not given", Toast.LENGTH_SHORT).show();


    }

    public void goToHome(View view) {
        i = new Intent(this, Home.class);
        startActivity(i);
    }

    public void goToSearch(View view) {
        i = new Intent(this, Search.class);
        startActivity(i);
    }

    public void goToProfile(View view) {
        i = new Intent(this, Profile.class);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give);

        categorySelect = findViewById(R.id.categorySelector);

        ArrayAdapter<String> categoryOptions = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, MainActivity.data.getCategories());
        categorySelect.setAdapter(categoryOptions);

        final String[] tempCat = new String[1];

        categorySelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = parent.getItemAtPosition(position).toString();
                tempCat[0] = selected;
                Log.i("Category Selected", tempCat[0]);
                Give.this.category = tempCat[0];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Selected", "Nothing");
            }
        });


    }
}