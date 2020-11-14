package com.example.booksmart.DataRead;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.Elements.Categories;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;
import com.example.booksmart.R;
import com.example.booksmart.entry.MainActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Data extends AppCompatActivity {

    private ArrayList<User> users;
    private ArrayList<Book> books;

    private User currentUser;

    private final Categories[] allCategories = Categories.values();

    public Data() {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.currentUser = null;
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

    /*public void readUsers() {

        File uFile = new File("src/main/java/com/example/booksmart/Data/users.csv");

        Log.i("User file", Boolean.toString(uFile.isFile()));

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

                String name = parts[5];

                users.add(new User(new Person(name), email, mobile, pw, id));
            }
        } catch (IOException e) {
            Log.wtf("Reading Users", "Error");
            e.printStackTrace();
        }

        *//*try (Scanner readUsers = new Scanner(uFile)) {

            while (readUsers.hasNextLine()) {

                String line = readUsers.nextLine();
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String email = parts[1];
                long mobile = Long.parseLong(parts[2]);
                String pw = parts[3];

                String name = parts[5];

                users.add(new User(new Person(name), email, mobile, pw, id));

                *//**//*if (parts.length == 6) {
                    fName = parts[4];
                    lName = parts[5];
                    users.add(new User(new Person(fName, lName), email, mobile, pw, id));
                } else {
                    for (int i = 4; i < parts.length; i++) {
                        name += parts[i];
                    }
                    users.add(new User(new Person(name), email, mobile, pw, id));
                }*//**//*

            }
        } catch (FileNotFoundException fnfe) {
            Log.i("User's File", "Not found");
        }*//*

        Log.i("Users in users", Integer.toString(users.size()));

        this.users = users;

    }*/

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


    public void readBooks() {

        File bFile = new File("com/example/booksmart/Data/Books.csv");

        ArrayList<Book> books = new ArrayList<>();

        try (Scanner readBooks = new Scanner(bFile)) {
            while (readBooks.hasNextLine()) {

                String line = readBooks.nextLine();
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
        } catch (FileNotFoundException fnfe) {
            System.out.println("Books File not Found");
        }

        MainActivity.data.setBooks(books);

    }

    public ArrayList<User> getAllUsers() {
        return new ArrayList<>(this.users);
    }

    public ArrayList<Book> getAllBooks() {
        return new ArrayList<>(this.books);
    }


    public User getUserById(int id) {
        for (User user : this.users) {
            if (user.getUniqueId() == id) {
                return user;
            }
        }
        return null;
    }

    public User getUser(String emailId) {

        for (User user : this.users) {
            if (user.getEmail().equals(emailId)) {
                return user;
            }
        }

        return null;
    }

    public boolean checkPassword(String emailId, String password) {

        User user = this.getUser(emailId);
        return checkPassword(user, password);

    }

    public boolean checkPassword(User user, String password) {

        if (user != null) {
            return user.getPassword().equals(password);
        }

        return false;

    }

    public User getCurrentUser() {
        return this.currentUser;
    }

}
