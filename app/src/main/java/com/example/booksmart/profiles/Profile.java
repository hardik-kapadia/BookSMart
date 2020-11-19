package com.example.booksmart.profiles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.Elements.BookAdapter;
import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;
import com.example.booksmart.home.Give;
import com.example.booksmart.home.Home;
import com.example.booksmart.home.Search;

public class Profile extends AppCompatActivity {

    Intent i;

    public void goToGive(View view) {
        i = new Intent(getApplicationContext(), Give.class);
        startActivity(i);
    }

    public void goToHome(View view) {
        i = new Intent(this, Home.class);
        startActivity(i);
    }

    public void goToSearch(View view) {
        i = new Intent(this, Search.class);
        startActivity(i);
    }

    public void signOut(View view) {
        i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ListView userBooksList = findViewById(R.id.userBooks);
        ArrayAdapter<Book> userBooks = new BookAdapter(this, 0, MainActivity.data.getCurrentUser().getUserBooks());
        userBooksList.setAdapter(userBooks);

        userBooksList.setOnItemClickListener((parent, view, position, id) -> {
            Book b = (Book) parent.getItemAtPosition(position);
            Intent i = new Intent(Profile.this, BookProfile.class);
            i.putExtra("bookId", b.getUniqueId());
            Log.i("Going to book", b.getName());
            startActivity(i);
        });

        TextView fn = findViewById(R.id.firstName);
        TextView ln = findViewById(R.id.lastN);
        TextView email = findViewById(R.id.email);
        TextView phone = findViewById(R.id.phone);

        fn.setText(MainActivity.data.getCurrentUser().getName().getFirstName());
        ln.setText(MainActivity.data.getCurrentUser().getName().getLastName());
        email.setText(MainActivity.data.getCurrentUser().getEmail());
        phone.setText(Long.toString(MainActivity.data.getCurrentUser().getMobile()));

    }
}