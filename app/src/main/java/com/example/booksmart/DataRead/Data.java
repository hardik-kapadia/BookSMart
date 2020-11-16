package com.example.booksmart.DataRead;

import android.content.Context;
import android.util.Log;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.Elements.Categories;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;
import com.example.booksmart.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Data {

    private ArrayList<User> users;
    private ArrayList<Book> books;
    private final Context context;

    private User currentUser;

    private final Categories[] allCategories = Categories.values();

    public Data(Context context) {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.currentUser = null;
        this.context = context;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void readUsersFromRes() {

        ArrayList<User> users = new ArrayList<>();

        InputStream isr = this.context.getResources().openRawResource(R.raw.users);
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

                users.add(
                        new User(
                                new Person(
                                        name
                                ),
                                email, mobile, pw, id
                        )
                );
            }
        } catch (IOException e) {
            Log.wtf("Reading Users", "Error");
            e.printStackTrace();
        }

        this.users = users;
    }

    public void readBooksFromRes() {

        ArrayList<Book> books = new ArrayList<>();

        InputStream isr = context.getResources().openRawResource(R.raw.books);
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

        this.books = books;

    }

    public void writeUsers() {

        File uFile = new File("com/example/booksmart/Data/Users.csv");

        try (FileWriter fw = new FileWriter(uFile)) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRandomUniqueId() {

        Random r = new Random();

        outer:
        while (true) {
            int temp = r.nextInt(10000);
            for (User user : users) {
                if (user.getUniqueId() == temp) {
                    continue outer;
                }
            }
            return temp;
        }

    }


    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(this.users);
    }

    public ArrayList<Book> getAllBooks() {
        return new ArrayList<>(this.books);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public User getUserById(int id) {
        for (User user : this.users) {
            if (user.getUniqueId() == id) {
                return user;
            }
        }
        return null;
    }

    public User getUserByEmail(String emailId) {

        for (User user : this.users) {
            if (user.getEmail().equals(emailId)) {
                return user;
            }
        }

        return null;
    }

    public boolean checkPassword(String emailId, String password) {

        User user = this.getUserByEmail(emailId);
        return checkPassword(user, password);

    }

    public boolean checkPassword(User user, String password) {

        if (user != null) {
            return user.getPassword().equals(password);
        }

        return false;

    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

}
