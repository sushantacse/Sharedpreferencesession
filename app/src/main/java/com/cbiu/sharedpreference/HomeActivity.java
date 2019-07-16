package com.cbiu.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView username;
    private Button logout;
    private SharedPreferences userPreference;
    private SharedPreferences.Editor editor;
    private static String SHARED_PREF_KEY = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.username);
        logout = findViewById(R.id.logout);

        userPreference = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        editor = userPreference.edit();

        String getEmail = getIntent().getStringExtra("email");
        username.setText("Welcome dear user: "+getEmail);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.clear();
                editor.apply();
                Intent a = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(a);
            }
        });

    }
}
