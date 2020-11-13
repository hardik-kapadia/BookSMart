package entry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.booksmart.People.User;
import com.example.booksmart.R;

public class testActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        TextView tv = (TextView) findViewById(R.id.userInfo);

        tv.setText(MainActivity.data.getCurrentUser().toString());

    }
}