package com.example.booksmart.DataRead;

import android.content.Context;
import android.util.Log;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;
import com.example.booksmart.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Data {

    private static final String[] categories = {"Fiction", "Academics", "Self-Help", "Classics"};
    private final Context context;
    private ArrayList<User> users;
    private ArrayList<Book> books;
    private User currentUser;
    // private CRUDMySQL mysqlReader;

    public Data(Context context) {
        this.users = new ArrayList<>();
        this.books = new ArrayList<>();
        this.currentUser = null;
        this.context = context;
        // this.mysqlReader = new CRUDMySQL();
    }


    public void readUsersFromRes() {

        ArrayList<User> users = new ArrayList<>();

        /*try {
            mysqlReader.readFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

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
                String mobile = parts[2];
                String pw = parts[3];

                String name = parts[4];

                users.add(new User(new Person(name), email, mobile, pw, id));
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

                    String category = Data.categories[Integer.parseInt(parts[5]) -1];

                    Book tempBook = new Book(name, author, year, bookId, userId, category);

                    books.add(tempBook);
                    for (User user : users) {
                        if (user.getUniqueId() == userId) {
                            user.addUserBook(tempBook);
                            break;
                        }
                    }

                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            Log.wtf("Reading Books", "error");
            e.printStackTrace();
        }

        this.books = books;

        this.sortBooks();

        Log.i("Sorted Books", this.books.toString());

    }

    public Book getBookFromId(int id) {

        for (Book book : books) {
            if (book.getUniqueId() == id) {
                return book;
            }
        }
        return null;

    }

    public ArrayList<String> getCategories() {
        return new ArrayList<>(Arrays.asList(Data.categories));
    }

    public int getRandomUniqueUserId() {

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

    public int getRandomUniqueBookId() {
        Random r = new Random();

        outer:
        while (true) {
            int temp = r.nextInt(10000);
            for (Book book : books) {
                if (book.getUniqueId() == temp) {
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

    public User getUserByEmail(String emailId) {

        for (User user : this.users) {
            if (user.getEmail().equals(emailId)) {
                return user;
            }
        }

        return null;
    }

    public void sortBooks() {

        ArrayList<Book> temp = new ArrayList<>();
        ArrayList<Book> current = this.books;

        while (current.size() > 1) {
            Book min = current.get(0);
            int index = 0, i = 0;
            for (Book book : current) {
                if (book.compareTo(min) < 0) {
                    min = book;
                    i = index;
                }
                index++;
            }
            Log.i("Max", min.getName());
            current.remove(i);
            temp.add(min);
            Log.i("Current", current.toString());
            Log.i("temp", temp.toString());
        }

        this.books = temp;

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

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}
