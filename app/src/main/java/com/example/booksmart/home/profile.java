package com.example.booksmart.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.booksmart.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
}