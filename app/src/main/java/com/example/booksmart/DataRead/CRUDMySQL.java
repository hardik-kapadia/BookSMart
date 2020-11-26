
package com.example.booksmart.DataRead;

import com.example.booksmart.Elements.Book;
import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

class CRUDMySQL {

    public void addDatabaseUser(User a) {
        try {
            String url = "jdbc:mysql://localhost:3306//javaproject";
            String uname = "root";
            String pass = "Mvvsashank1!";
            String query = "insert into users (?,?,?,?,?,?)";
            int uniqueId = a.getUniqueId();
            Person name = a.getName();
            String email = a.getEmail();
            String mobile = a.getMobile();
            String password = a.getPassword();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, uniqueId);
            st.setString(2, name.getFirstName());
            st.setString(3, name.getLastName());
            st.setString(4, email);
            st.setLong(5, Long.parseLong(mobile));
            st.setString(6, password);
            int count = st.executeUpdate();
            System.out.println(count + "row affected");
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Vector<User> readFromDatabaseUser() {
        try {
            Vector<User> vc = new Vector<User>();
            String url = "jdbc:mysql://localhost:3306//javaproject";
            String uname = "root";
            String pass = "Mvvsashank1!";
            String query = "select * from users";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement st = (PreparedStatement) con.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int uniqueId = rs.getInt(1);
                String fname = rs.getString(2);
                String Lastname = rs.getString(3);

                String email = rs.getString(4);
                long mobile = rs.getLong(5);//not sure if this is Big Int
                String password = rs.getString(6);

                User ob = new User(new Person(fname, Lastname), email, Long.toString(mobile), password, uniqueId);
                vc.addElement(ob);
            }
            st.close();
            con.close();
            return vc;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    public void addDatabaseBook(Book a) {
        try {
            String url = "jdbc:mysql://localhost:3306//javaproject";
            String uname = "root";
            String pass = "Mvvsashank1!";
            String query = "insert into books(?,?,?,?,?,?,?)";


            String name = a.getName();
            String authorfname = a.getAuthor().getFirstName();
            String authorLname = a.getAuthor().getLastName();
            int year = a.getYear();
            int uniqueId = a.getUniqueId();
            int giverId = a.getGiverId();
            String category = a.getCategory();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, authorfname);
            st.setString(3, authorLname);
            st.setInt(4, year);
            st.setInt(5, uniqueId);
            st.setInt(6, giverId);
            st.setString(7, category);
            int count = st.executeUpdate();
            System.out.println(count + "row affected");
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Vector<Book> readFromDatabaseBook() {
        try {
            Vector<Book> readbook = new Vector<>();
            String url = "jdbc:mysql://localhost:3306//javaproject";
            String uname = "root";
            String pass = "Mvvsashank1!";
            String query = "select * from books";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement st = (PreparedStatement) con.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {


                String name1 = rs.getString(1);
                String authorfname1 = rs.getString(2);
                String authorLname1 = rs.getString(3);
                int year1 = rs.getInt(4);
                int uniqueId1 = rs.getInt(4);
                int giverId1 = rs.getInt(6);
                String Category1 = rs.getString(7);
                Book ob = new Book(name1, new Person(authorfname1, authorLname1), year1, uniqueId1, giverId1, Category1);
                readbook.addElement(ob);
            }
            st.close();
            con.close();
            return readbook;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    public void deleteDatabaseBook(Book deletebook) {
        try {
            String url = "jdbc:mysql://localhost:3306//javaproject";
            String uname = "root";
            String pass = "Mvvsashank1!";
            String query = "DELETE FROM books WHERE uniqueid=?;";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, pass);
            PreparedStatement st = con.prepareStatement(query);

            st.setInt(1, deletebook.getUniqueId());

            int count = st.executeUpdate();
            System.out.println(count + "row affected");
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}




/*

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
*/
