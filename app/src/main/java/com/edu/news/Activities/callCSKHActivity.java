package com.edu.news.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.edu.news.R;

public class callCSKHActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_cskh);
        Intent intent=getIntent();
    }

    public void clickBack(View view) {
        finish();
    }
}
