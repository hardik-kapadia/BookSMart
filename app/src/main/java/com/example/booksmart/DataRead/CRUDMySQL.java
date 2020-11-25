package com.example.booksmart.DataRead;


import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CRUDMySQL {

    private Connection connect;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    public void readFromDatabase() throws Exception {
        ArrayList<User> usersR = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://192.168.1.106:3306/example",
                    "tempUser", "password123");
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM example.users");
            usersR = getUsersFromResult(resultSet);
            System.out.println(usersR.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        if (usersR != null) {
            for (User u : usersR) {
                System.out.println(u);
            }

        }
    }

    private ArrayList<User> getUsersFromResult(ResultSet resultSet) throws SQLException {

        ArrayList<User> temp = new ArrayList<>();
        while (resultSet.next()) {

            int id = resultSet.getInt(1);
            String email = resultSet.getString(2);
            String firstName = resultSet.getString(3);
            String lastName = resultSet.getString(4);
            String mobile = resultSet.getString(5);
            String pass = resultSet.getString(6);

            User user = new User(new Person(firstName, lastName), email, mobile, pass, id);
            temp.add(user);
        }

        return temp;
    }

    private void close() throws SQLException {
        if (connect != null) {
            connect.close();
        }
        if (statement != null) {
            statement.close();
        }

        if (resultSet != null) {
            resultSet.close();
        }
    }

}
