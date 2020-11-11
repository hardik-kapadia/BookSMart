package com.example.booksmart.DataRead;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GetData {

    ArrayList<User> users;
    ArrayList<Book> books;

    //TODO Randomly generate and assign a UNIQUE Id.
    public int getUniqueId() {
        return -1;
    }

    //TODO Get Data from MySQL and write it into the file or store directly into Array.
    public void readFile() {

        File uFile = new File("src/Data/Users.txt");
        File bFile = new File("src/Data/Books.txt");

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

        try(Scanner readBooks = new Scanner(bFile)){
            while(readBooks.hasNextLine()){

                String line = readBooks.nextLine();
                String[] parts = line.split(" ");



            }
        } catch (FileNotFoundException fnfe){
            System.out.println("Books File not Found");
        }

    }

    public static void main(String[] args) {
        GetData gd = new GetData();

        gd.readFile();
    }

}
