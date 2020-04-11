package com.edu.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    TextInputLayout regUsername, regPassword, regPhone, regEmail, regName;
    TextView tvDangNhap;
    Button btnDangKy;
    ProgressBar progressBar;
    FirebaseDatabase mDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Views
        regUsername = findViewById(R.id.editTextUsername);
        regName = findViewById(R.id.editTextName);
        regPassword = findViewById(R.id.editTextPassword);
        regPhone = findViewById(R.id.editPhone);
        regEmail = findViewById(R.id.editEmail);
        btnDangKy = findViewById(R.id.buttonDangKy);
        tvDangNhap = findViewById(R.id.textViewDangNhap);
        progressBar = findViewById(R.id.progressBar);


        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

    }


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty()) {
            regName.setError("Trống");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUserName() {
        String val = regUsername.getEditText().getText().toString();

        if (val.isEmpty()) {
            regUsername.setError("Trống");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();

        if (val.isEmpty()) {
            regEmail.setError("Trống");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhone() {
        String val = regPhone.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhone.setError("Trống");
            return false;
        } else {
            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPassword.setError("Trống");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }


    //This function will execute when user click on Register Button
    public void registerUser(View view) {

        if (!validateName() | !validatePassword() | !validatePhone() | !validateEmail() | !validateUserName()) {
            return;
        }

        //Get all values
        String username = regUsername.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();
        String phone = regPhone.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String name = regName.getEditText().getText().toString();

        mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference("users");
        UserHelperClass helperClass = new UserHelperClass(username, password, phone, email, name);
        reference.child(username).setValue(helperClass);

        Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}













