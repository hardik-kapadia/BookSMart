package com.example.booksmart.Elements;

import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;
import com.example.booksmart.entry.MainActivity;

public class Book {

    private final String name;
    private final Person author;
    private final User giver;
    private final int year;
    private final int uniqueId;
    private final int giverId;

    Categories category;

    public Book(String name, Person author, User giver, int year, int uniqueId, Categories category) {
        this.name = name;
        this.author = author;
        this.giver = giver;
        this.year = year;
        this.uniqueId = uniqueId;
        this.giverId = this.giver.getUniqueId();
        this.category = category;
    }


    public String getCategory() {
        return category.toString();
    }

    public Book(String name, Person author, int year, int uniqueId, int giverId, Categories category) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.uniqueId = uniqueId;
        this.giverId = giverId;
        this.category = category;

        User giving = null;

        if (MainActivity.data.getAllUsers() != null) {
            for (User user : MainActivity.data.getAllUsers()) {
                System.out.println("IN BOOK, this user's id: " + giverId);
                if (user.getUniqueId() == giverId) {
                    giving = user;
                    break;
                }
            }
        } else {
            System.out.println("All users have not been read yet");
        }

        if (giving == null) {
            System.out.println("No giver found for id" + giverId);
        }

        this.giver = giving;

    }

    @Override
    public int hashCode() {
        return this.name.hashCode() * 12 + this.author.hashCode() * 7 + this.giver.hashCode() * 18 + this.category.hashCode() * 3 + this.year;
    }

    public int getUniqueId() {
        return this.uniqueId;
    }

    public String getName() {
        return name;
    }

    public Person getAuthor() {
        return author;
    }

    public int getGiverId() {
        return giverId;
    }

    public User getGiver() {
        return giver;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return this.name + " ";
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        if (this.hashCode() == obj.hashCode()) {
            return true;
        }

        Book compareTo = (Book) obj;

        return (this.name.contains(compareTo.getName()) || this.author.equals(compareTo.getAuthor()));
    }

    public Boolean matches(String searched) {
        return (this.name.toLowerCase().contains(searched.toLowerCase()) || this.author.equals(new Person(searched)));
    }

}
