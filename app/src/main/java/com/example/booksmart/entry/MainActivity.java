package com.example.booksmart.entry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmart.DataRead.Data;
import com.example.booksmart.People.User;
import com.example.booksmart.R;
import com.example.booksmart.home.Home;

public class MainActivity extends AppCompatActivity {

    public static Data data = null;
    TextView userNameField, passWordField;

    public void login(View view) {

        userNameField = findViewById(R.id.getUserName);
        passWordField = findViewById(R.id.getPassword);

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
        }

    }

}