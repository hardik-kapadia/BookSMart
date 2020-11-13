package com.example.booksmart.entry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksmart.People.Person;
import com.example.booksmart.People.User;
import com.example.booksmart.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    public static boolean validateEmail(String emailStr) {
        Pattern valid = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = valid.matcher(emailStr);
        return matcher.find();
    }

    public static String validatePhone(String phone) {
        phone = phone.replace(" ", "").replace("+", "").replace("-", "").replace("(", "").replace(")", "");
        for (char c : phone.toCharArray()) {
            if (!Character.isDigit(c)) {
                return null;
            }
        }
        return phone;
    }

    public void enter(View view) {

        TextView firstN = (TextView) findViewById(R.id.fName);
        TextView lastN = (TextView) findViewById(R.id.lName);
        TextView emailS = (TextView) findViewById(R.id.emailID);
        TextView numberS = (TextView) findViewById(R.id.pNumber);
        TextView passwdS = (TextView) findViewById(R.id.passwd);
        TextView confirmPasswdS = (TextView) findViewById(R.id.confirmPasswd);

        String firstName = firstN.getText().toString();
        String lastName = lastN.getText().toString();

        String emailId = emailS.getText().toString();
        String phoneNumber = numberS.getText().toString();

        String pass = passwdS.getText().toString();
        String pass2 = confirmPasswdS.getText().toString();

        boolean alreadyExists = false;

        for (User u : MainActivity.data.getAllUsers()) {
            if (u.getEmail().equals(emailId)) {
                alreadyExists = true;
                break;
            }
        }

        String[] errorToasts = {"User already Exists", "Invalid E-mail address", "Enter Full Name",
                "Invalid Phone number", "Password too small, Should be atleast 8 characters", "Passwords don't match"};
        int error = -1;

        User newUser = null;

        if (!alreadyExists) {
            if (SignUp.validateEmail(emailId)) {
                if ((!firstName.isEmpty()) && (!lastName.isEmpty())) {
                    if (validatePhone(phoneNumber) != null) {
                        if (pass.length() >= 8) {
                            if (pass.equals(pass2)) {
                                int id = MainActivity.data.getRandomUniqueId();
                                Log.i("password", pass);
                                newUser = new User(new Person(firstName, lastName), emailId, Long.parseLong(phoneNumber), pass, id);
                                Log.i("New User creation", "Success");
                            } else error = 5;
                        } else error = 4;
                    } else error = 3;
                } else error = 2;
            } else error = 1;
        } else error = 0;

        if (newUser != null) {
            MainActivity.data.setCurrentUser(newUser);
            // Intent i = new Intent(getApplicationContext(), testActivity.class);
            // startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), errorToasts[error], Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}