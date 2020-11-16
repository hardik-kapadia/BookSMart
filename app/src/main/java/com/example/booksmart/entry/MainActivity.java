
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
import com.example.booksmart.home.Home;

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

        String usernameTemp = userNameField.getText().toString();
        String passwordTemp = passWordField.getText().toString();

        User loggedIn = data.getUserByEmail(usernameTemp);

        if (loggedIn != null) {
            if (data.checkPassword(loggedIn, passwordTemp)) {
                data.setCurrentUser(loggedIn);
                Log.i("Current User", data.getCurrentUser().toString());
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
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
            data = new Data(getApplicationContext());
            data.readUsersFromRes();
            data.readBooksFromRes();
            Log.i("data", "Initializing");
            Log.i("Size of Users", Integer.toString(data.getAllUsers().size()));
            Log.i("Size of Books", Integer.toString(data.getAllBooks().size()));
        }

    }

}