package com.edu.news.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.edu.news.R;
import com.edu.news.Adapter.PagerAdapter;
import com.edu.news.Fragments.firstFragment;
import com.edu.news.Fragments.secondFragment;
import com.edu.news.Fragments.thirdFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    //TabLayout with Fragment
    ViewPager viewPager;
    TabLayout mTabLayout;
    TabItem firstTab, secondTab, thirdTab;
    PagerAdapter adapter;

    //RecyclerView Post
    RecyclerView postList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //Views tabLayout
        firstTab = findViewById(R.id.firstItem);
        secondTab = findViewById(R.id.secondItem);
        thirdTab = findViewById(R.id.thirdItem);
        mTabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        //Views RecyclerView Post
        postList = findViewById(R.id.postList);



        //Tool Bar
        setSupportActionBar(toolbar);

        //Menu Nav
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        adapter = new PagerAdapter(getSupportFragmentManager());
        addTabs(viewPager);
        ((TabLayout)findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);


}

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Toast.makeText(MainActivity.this, "Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_category:
                Intent intent = new Intent(getApplicationContext(), AllCategories.class);
                startActivity(intent);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(logout);
                break;
            case R.id.nav_profile:
                Intent profile = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(profile);
                break;


        }
        return true;
    }

    public void addTabs(ViewPager viewPager){
        adapter.add(new firstFragment(),"Công nghệ");
        adapter.add(new secondFragment(),"Lập trình");
        adapter.add(new thirdFragment(),"Thảo luận");
        viewPager.setAdapter(adapter);
    }


}


//////////////////////////////////////////////////////////////////////////////
//Navigation Drawer Functions
//    private void navigationDrawer() {
//
//        //Navigation Drawer
//        navigationView.bringToFront();
//        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.setCheckedItem(R.id.nav_home);
//
//        menuIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                }else drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });
//    }
//
//    @Override
//    public void onBackPressed() {
//
//        if (drawerLayout.isDrawerVisible(GravityCompat.START)){
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }else
//        super.onBackPressed();
//    }
///////////////////////////////////////////////////////////////////
