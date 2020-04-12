package com.edu.news.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.news.R;
import com.edu.news.Helper.UserHelperClass;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class RegisterActivity extends AppCompatActivity {

    private EditText regName, regUsername, regEmail, regPassword;
    private Button btnDangKy;
    private ProgressBar loadingProgressBar;
    private FirebaseAuth mAuth;
    TextView tvDangNhap;

    ImageView ImgUserPhoto;

    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Views
        ImgUserPhoto = findViewById(R.id.regUserPhoto);
        regUsername = findViewById(R.id.regUsername);
        regName = findViewById(R.id.regName);
        regPassword = findViewById(R.id.regPassword);
        regEmail = findViewById(R.id.regEmail);
        btnDangKy = findViewById(R.id.regBtn);
        tvDangNhap = findViewById(R.id.tvDangnhap);
        loadingProgressBar = findViewById(R.id.regProgressBar);
        loadingProgressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        //Bat su kien Onlclick btnDangKy
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDangKy.setVisibility(View.INVISIBLE);
                loadingProgressBar.setVisibility(View.VISIBLE);
                final String name = regName.getText().toString();
                final String username = regUsername.getText().toString();
                final String email = regEmail.getText().toString();
                final String password = regPassword.getText().toString();


                // bat dieu kien
                if (name.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    //Khong nhap du lieu vao cac o se bat loi va thong bao len man hinh
                    showMessage("Trống");
                    btnDangKy.setVisibility(View.VISIBLE);
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    // nguoc lai khong trong se tao tai khoan
                    //phuong thuc Create se tao tai khoan neu email valid
                    CreateUserAccount(username, name, email, password);
                }
            }
        });


        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 22) {
                    checkAndRequestForPermission();

                } else {
                    openGallery();
                }
            }
        });

    }

    private void CreateUserAccount(String username, final String name, String email, String password) {
        // tao tai khoan rieng voi email va password

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // tao tai khoan thanh oong
                    showMessage("Tạo tài khoản thành công");
                    //tao thanh cong xong update avatar va name
                    updateUserInfo(name, pickedImgUri, mAuth.getCurrentUser());


                } else {
                    // tao tai khoan that bai
                    showMessage("Tạo thất bại" + task.getException().getMessage().toString());
                    btnDangKy.setVisibility(View.VISIBLE);
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    // update avatar va name
    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        // first: up len firebase storage va get url
        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //up anh thanh cong
                //co the get our image url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        // contain user image url

                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();


                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            // info user update thanh cong
                                            showMessage("Đăng ký thành công");
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            updateUI();
                                        }

                                    }
                                });
                    }
                });
            }
        });
    }


    private void updateUI() {

    }


    // Phuong thuc de show Toast len activity
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    //phuong thuc lay anh tu thu vien va set avatar
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            pickedImgUri = data.getData();
            ImgUserPhoto.setImageURI(pickedImgUri);
        }
    }

    private void openGallery() {
        //mo Gallery
        Intent GalleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        GalleryIntent.setType("image/*");
        startActivityForResult(GalleryIntent, REQUESCODE);
    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(RegisterActivity.this, "Cho phép quyền truy cập", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
            }
        } else
            openGallery();
    }




}













