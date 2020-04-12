package com.edu.news.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import com.edu.news.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;


import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   FirebaseUser currentUser;
   FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //Views
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();




        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        updateNavHeader();



    }

    private void updateNavHeader() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = headerView.findViewById(R.id.nav_username);
        TextView navUserMail = headerView.findViewById(R.id.nav_user_email);
        ImageView navUserPhoto = headerView.findViewById(R.id.nav_user_photo);

        navUserMail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());

        // now we will use Glide to load user image
        // first we need to import the library

        Glide.with(this).load(currentUser.getPhotoUrl()).into(navUserPhoto);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
//
//            getSupportActionBar().setTitle("Home");
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        } else if (id == R.id.nav_search) {

//            getSupportActionBar().setTitle("Profile");
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,new ProfileFragment()).commit();

        } else if (id == R.id.nav_category) {

//            getSupportActionBar().setTitle("Settings");
//            getSupportFragmentManager().beginTransaction().replace(R.id.container,new SettingsFragment()).commit()
//
        } else if (id == R.id.nav_save) {


        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_login) {

            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(loginActivity);
            finish();

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainActivity);
            finish();

        } else if (id == R.id.nav_list) {

        } else if (id == R.id.nav_send) {
        } else if (id == R.id.nav_about) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
