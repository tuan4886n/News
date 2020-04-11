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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText mEmail, mPassword;
    TextView tvDangNhap;
    Button btnDangKy;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    String userID;
    FirebaseDatabase mDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmail = findViewById(R.id.editTextUsername);
        mPassword = findViewById(R.id.editTextPassword);
        btnDangKy = findViewById(R.id.buttonDangKy);
        tvDangNhap = findViewById(R.id.textViewDangNhap);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        btnDangKy.setOnClickListener(new View.OnClickListener() {
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

                //dang ky user trong firebase
                if (!(TextUtils.isEmpty(email) && TextUtils.isEmpty(password))) {
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                                userID = fAuth.getCurrentUser().getUid();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Lỗi" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            }
        });
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance();
                reference = mDatabase.getReference("users");
                reference.setValue("Hello alo alo");
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}
