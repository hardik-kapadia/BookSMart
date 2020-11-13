package com.example.booksmart.DataRead;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Data {

    private ArrayList<User> users;
    private ArrayList<Book> books;

    private User currentUser;

    public Data(){
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

    public void readUsers() {
        File uFile = new File("src/Data/Users.txt");

        ArrayList<User> users = new ArrayList<>();

        try (Scanner readUsers = new Scanner(uFile)) {

            while (readUsers.hasNextLine()) {

                String line = readUsers.nextLine();
                String[] parts = line.split(" ");

                int id = Integer.parseInt(parts[0]);
                String email = parts[1];
                long mobile = Long.parseLong(parts[2]);
                String pw = parts[3];

                String name = "";
                String fName, lName;

                if (parts.length == 6) {
                    fName = parts[4];
                    lName = parts[5];
                    users.add(new User(new Person(fName, lName), email, mobile, pw, id));
                } else {
                    for (int i = 4; i < parts.length; i++) {
                        name += parts[i];
                    }
                    users.add(new User(new Person(name), email, mobile, pw, id));
                }

            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Users File not Found");
        }

        this.users = users;
    }

    public void writeUsers() {

        File uFile = new File("src/Data/Users.txt");

        try (FileWriter fw = new FileWriter(uFile)) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getRandomUniqueId() {

        Random r = new Random();

        outer:
        while (true) {
            int temp = r.nextInt(1000);
            for (User user : users) {
                if (user.getUniqueId() == temp) {
                    continue outer;
                }
            }
            return temp;
        }

    }


    public void readBooks() {

        File bFile = new File("src/Data/Books.txt");

        ArrayList<Book> books;

        try (Scanner readBooks = new Scanner(bFile)) {
            while (readBooks.hasNextLine()) {

                String line = readBooks.nextLine();
                String[] parts = line.split(" ");


            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Books File not Found");
        }

    }

    public ArrayList<User> getAllUsers() {
        return this.users;
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
