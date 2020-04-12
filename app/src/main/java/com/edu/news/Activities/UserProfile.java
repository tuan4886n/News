    package com.edu.news.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.edu.news.R;
import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {
    TextView fullname_field, username_field;
    TextInputLayout fullname, password, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        //Hooks

        fullname_field = findViewById(R.id.fullname_field);
        username_field = findViewById(R.id.username_field);
        fullname = findViewById(R.id.full_name_profile);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phone = intent.getStringExtra("phone");
        String user_password = intent.getStringExtra("password");

        fullname_field.setText(user_name);
        username_field.setText(user_username);
        fullname.getEditText().setText(user_name);
        password.getEditText().setText(user_password);
        email.getEditText().setText(user_email);
        phone.getEditText().setText(user_phone);
    }
}
