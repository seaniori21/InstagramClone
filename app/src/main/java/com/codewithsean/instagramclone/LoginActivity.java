package com.codewithsean.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnCreateNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(ParseUser.getCurrentUser() != null){
            goMainActivity();
        }

        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        btnLogin = findViewById(R.id.btnLogin);
        btnCreateNewUser = findViewById(R.id.btnCreateNewUser);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Clicked The btnLogin");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username,password);
            }
        });

        btnCreateNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Clicked The button CreateNewUser");
                goCreateNewUserActivity();//sends you to CreateNewUser.xml
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to Log in User " + username);

            ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(e != null){
                            //TODO better error handling to tell user what is happening
                            Log.e(TAG, "Issue with Login", e);
                            Toast.makeText(LoginActivity.this, "Wrong username and password", Toast.LENGTH_SHORT).show();
                            return;

                        }
                        //navigate to Main Activity if user signs in properly
                        goMainActivity();
                        Toast.makeText(LoginActivity.this, "inLoginSuccess", Toast.LENGTH_SHORT).show();
                    }
            });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goCreateNewUserActivity(){
        Intent i = new Intent(this, CreateNewUserActivity.class);
        startActivity(i);
        finish();
    }


}
