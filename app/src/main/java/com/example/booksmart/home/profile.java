package com.example.booksmart.home;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;

public class profile extends AppCompatActivity {

    Intent i;

    public void goToGive(View view) {
        i = new Intent(getApplicationContext(), give.class);
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ListView userBooksList = findViewById(R.id.userBooks);
        ArrayAdapter<Book> userBooks = new ArrayAdapter<>(this, R.layout.activity_listview, MainActivity.data.getCurrentUser().getUserBooks());
        userBooksList.setAdapter(userBooks);
        userBooksList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Book b = (Book) parent.getItemAtPosition(position);
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
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