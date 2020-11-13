package com.example.booksmart.Elements;

import com.example.booksmart.People.Person;

public class Book {

    private final String name;
    private final Person author;
    private final Person giver;
    private final int year;
    private final int uniqueId;
    private final int giverId;

    Categories category;

    public Book(String name, Person author, Person giver, int year, int uniqueId, int giverId, Categories category) {
        this.name = name;
        this.author = author;
        this.giver = giver;
        this.year = year;
        this.uniqueId = uniqueId;
        this.giverId = giverId;
        this.category = category;
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

    public Person getGiver() {
        return giver;
    }

    public int getYear() {
        return year;
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

}
