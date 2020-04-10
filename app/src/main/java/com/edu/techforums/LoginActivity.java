package com.edu.techforums;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    TextView tvDangKy;
    Button btnDangNhap;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.editTextUsername);
        mPassword = findViewById(R.id.editTextPassword);
        btnDangNhap = findViewById(R.id.buttonDangNhap);
        tvDangKy = findViewById(R.id.textViewDangKy);
        progressBar = findViewById(R.id.progressBar);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = fAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginActivity.this,"Ban da dang nhap",Toast.LENGTH_SHORT ).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(LoginActivity.this,"Chua dang nhap",Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Chưa nhập Email");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Chưa nhập Password");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Phải nhập hơn 6 kí tự");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //auther user

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this, "Lỗi" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(mAuthStateListener);
    }
}