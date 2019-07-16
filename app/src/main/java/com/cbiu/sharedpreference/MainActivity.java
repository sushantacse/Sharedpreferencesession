package com.cbiu.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private CheckBox Remember;
    private Button Login;

    // need to create object for sharedpreference
    private SharedPreferences userPreference;
    private SharedPreferences.Editor editor;
    private static String SHARED_PREF_KEY = "user";
    private static String SHARED_EMAIL_KEY = "email";
    private static String SHARED_PASSWORD_KEY = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPreference = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        editor = userPreference.edit();
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        Remember = findViewById(R.id.checkbox);
        Login = findViewById(R.id.Login);
        Email.setText(userPreference.getString(SHARED_EMAIL_KEY,""));
        Password.setText(userPreference.getString(SHARED_PASSWORD_KEY,""));

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sessionManagementforLoginUser();
            }
        });
    }

    private void sessionManagementforLoginUser() {

        String email =  Email.getText().toString();
        String password =  Password.getText().toString();

        if (email.matches("") && password.matches(""))
        {
            Toast.makeText(MainActivity.this, "Please Enter Email & Password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (Remember.isChecked())
            {
                editor.putString(SHARED_EMAIL_KEY,email);
                editor.putString(SHARED_PASSWORD_KEY,password);
                editor.apply();
                Toast.makeText(getApplicationContext(),"Email & Password Saved",Toast.LENGTH_SHORT).show();
                Intent a = new Intent(MainActivity.this, HomeActivity.class);
                a.putExtra("email",email);
                startActivity(a);
            }
            else
            {
                Intent a = new Intent(MainActivity.this, HomeActivity.class);
                a.putExtra("email",email);
                startActivity(a);
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (userPreference.contains(SHARED_EMAIL_KEY) && userPreference.contains(SHARED_PASSWORD_KEY))
        {
            Intent a = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(a);
        }
    }
}
