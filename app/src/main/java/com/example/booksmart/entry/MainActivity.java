
package com.example.booksmart.entry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksmart.DataRead.Data;
import com.example.booksmart.Elements.Book;
import com.example.booksmart.Elements.Categories;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;
import com.example.booksmart.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button login, signUp;
    TextView userNameField, passWordField;

    public static Data data = null;

    public void login(View view) {

        userNameField = (TextView) findViewById(R.id.getUserName);
        passWordField = (TextView) findViewById(R.id.getPassword);

        String usernameTemp = (String) userNameField.getText();
        String passwordTemp = (String) passWordField.getText();

        User loggedIn = data.getUser(usernameTemp);

        if (loggedIn != null) {
            if (data.checkPassword(loggedIn, passwordTemp)) {
                data.setCurrentUser(loggedIn);
                Log.i("Logged in user", loggedIn.toString());
                Log.i("Current User", data.getCurrentUser().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                clearFields();
            }
        } else {
            Toast.makeText(getApplicationContext(), "User Does not exist", Toast.LENGTH_SHORT).show();
            clearFields();
        }

    }

    public void goToSignUp(View view) {

        Intent goingToSignUp = new Intent(this, SignUp.class);
        startActivity(goingToSignUp);

    }

    public void clearFields() {
        this.userNameField.setText("");
        this.passWordField.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (data == null) {
            data = new Data();
            this.readUsersFromRes();
            this.readBooksFromRes();
            Log.i("data", "Initializing");
            Log.i("Size of Users", Integer.toString(data.getAllUsers().size()));
            Log.i("Size of Books", Integer.toString(data.getAllBooks().size()));
        }
    }

    public void readBooksFromRes() {

        ArrayList<Book> books = new ArrayList<>();

        InputStream isr = getResources().openRawResource(R.raw.books);
        BufferedReader reader = new BufferedReader(new InputStreamReader(isr));

        String line;

        Categories[] allCategories = Categories.values();

        try {
            while (true) {
                line = reader.readLine();
                if (line == null) break;

                String[] parts = line.split(",");

                if (parts.length == 6) {

                    int bookId = Integer.parseInt(parts[0].trim());
                    int userId = Integer.parseInt(parts[1].trim());

                    int year = Integer.parseInt(parts[2].trim());
                    String name = parts[3].replace("$", ",").trim();
                    Person author = new Person(parts[4].trim());

                    Categories category = allCategories[Integer.parseInt(parts[5])];

                    books.add(new Book(name, author, year, bookId, userId, category));

                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            Log.wtf("Reading Books", "error");
            e.printStackTrace();
        }

        data.setBooks(books);

    }

    public void readUsersFromRes() {

        ArrayList<User> users = new ArrayList<>();

        InputStream isr = getResources().openRawResource(R.raw.users);
        BufferedReader reader = new BufferedReader(new InputStreamReader(isr));

        String line;

        try {
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String email = parts[1];
                long mobile = Long.parseLong(parts[2]);
                String pw = parts[3];

                String name = parts[4];

                users.add(new User(new Person(name), email, mobile, pw, id));
            }
        } catch (IOException e) {
            Log.wtf("Reading Users", "Error");
            e.printStackTrace();
        }

        data.setUsers(users);
    }
}