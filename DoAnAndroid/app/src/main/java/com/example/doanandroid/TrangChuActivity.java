package com.example.doanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class TrangChuActivity extends AppCompatActivity {

    BottomNavigationView navView;
    FrameLayout frameLayout;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        AnhXa();
        loadFragment(new TrangChuFragment());
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);


    }

    @Override
    public void onBackPressed() {
        finish();
        return;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction =
        getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.viewPagerTrangChu, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void AnhXa() {
        navView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.viewPagerTrangChu);
        viewPager.setAdapter(new MyAdapterTablayout(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.tabLayoutTrangChu);
        tabLayout.setupWithViewPager(viewPager);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch(item.getItemId()){
                case R.id.navigation_home:
     //               getSupportActionBar().setTitle("Home");
                    fragment = new TrangChuFragment();
                    loadFragment(fragment);
                    overridePendingTransition(R.anim.anim_enter,R.anim.anim_enter);
                    //Toast.makeText(MainActivity.this, "Đây là home", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:

                    //Toast.makeText(MainActivity.this, "Đây là dashbroad", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:

                    //Toast.makeText(MainActivity.this, "Đây là Thông báo", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_account:

                    return true;

            }
            return false;
        }
    };


}
