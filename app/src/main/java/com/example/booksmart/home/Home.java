package com.example.booksmart.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.booksmart.R;

public class Home extends AppCompatActivity {

    Intent intent;

    public void goToSearch(View view) {
        intent = new Intent(getApplicationContext(), Search.class);
        startActivity(intent);
    }

    public void goToGive(View view) {
        intent = new Intent(getApplicationContext(), give.class);
        startActivity(intent);
    }

    public void goToProfile(View view) {
        intent = new Intent(getApplicationContext(), profile.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}