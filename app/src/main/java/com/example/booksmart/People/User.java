package com.example.booksmart.People;

public class User {

    int uniqueId;
    Person name;
    String email;
    long mobile;
    String password;

    public User(Person name, String email, long mobile, String password, int id){
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.uniqueId = id;
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
}
