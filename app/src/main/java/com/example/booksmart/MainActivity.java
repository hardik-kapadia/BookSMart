package com.example.booksmart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static String username, password;

    Button login, signUp;
    TextView userNameField, passWordField;

    public void login(View view) {

        userNameField = (TextView) findViewById(R.id.getUserName);
        passWordField = (TextView) findViewById(R.id.getPassword);

        String usernameTemp = (String) userNameField.getText();
        String passwordTemp = (String) passWordField.getText();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}