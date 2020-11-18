package com.example.booksmart.Elements;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.booksmart.R;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    private Context context;
    private ArrayList<Book> booksToDisplay;

    public BookAdapter(Context context, int resource, ArrayList<Book> booksToDisplay) {
        super(context, resource, booksToDisplay);

        this.context = context;
        this.booksToDisplay = booksToDisplay;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        Book book = booksToDisplay.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.book_layout, null);

        TextView bName = (TextView) view.findViewById(R.id.nameOfB);
        TextView bYear = (TextView) view.findViewById(R.id.year);
        TextView bAuthor = (TextView) view.findViewById(R.id.author);

        bName.setText(book.getName());
        bYear.setText(Integer.toString(book.getYear()));
        bAuthor.setText(book.getAuthor().getFullName());

        return view;
    }
}