package jocelyn_test03.com.myconnectdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtFname, edtLname, edtName, edtPass, edtEmail;
    private Button signup;
    private final String url = "http://192.168.1.5:80/myphp/login/signup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtFname = (EditText)findViewById(R.id.edtFname);
        edtLname = (EditText)findViewById(R.id.edtLname);
        edtName = (EditText)findViewById(R.id.edtUname);
        edtPass = (EditText)findViewById(R.id.edtPwd);
        edtEmail = (EditText)findViewById(R.id.edtEmail);

        signup = (Button)findViewById(R.id.btnSignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sender s = new Sender(MainActivity.this,url,
                        edtFname, edtLname, edtName, edtPass, edtEmail);
                s.execute();
            }
        });

    }


}
