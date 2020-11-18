package com.example.booksmart.People;

import com.example.booksmart.Elements.Book;

import java.util.ArrayList;

public class User {

    private final int uniqueId;
    private final Person name;
    private final String email;
    private final long mobile;
    private String password;
    private ArrayList<Book> userBooks;

    public User(Person name, String email, long mobile, String password, int id) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.uniqueId = id;
        this.userBooks = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.userBooks.add(book);
    }

    public void removeBook(Book book) {
        this.userBooks.remove(book);
    }

    public ArrayList<Book> getUserBooks() {
        return new ArrayList<>(this.userBooks);
    }

    public void addUserBook(Book book) {
        this.userBooks.add(book);
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getPassword() {
        return password;
    }

    public Person getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getMobile() {
        return mobile;
    }

    public void setNewPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.uniqueId + ": " + name.getFullName() + "\n( " + this.email + ", " + this.mobile + ") \n[" + this.password + "]";
    }

}
