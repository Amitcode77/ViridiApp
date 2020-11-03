package com.example.viridi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class SellActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_activity);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.sell);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.book:
                        startActivity(new Intent(getApplicationContext(), BookActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.buy:
                        startActivity(new Intent(getApplicationContext(), BuyActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.sell:
                        return true;
                    case R.id.learn:
                        startActivity(new Intent(getApplicationContext(), LearnActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.craft:
                        startActivity(new Intent(getApplicationContext(), CraftMarketActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.book:
                return true;
            case R.id.buy:
                startActivity(new Intent(getApplicationContext(), BuyActivity.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.sell:
                startActivity(new Intent(getApplicationContext(), SellActivity.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.learn:
                startActivity(new Intent(getApplicationContext(), LearnActivity.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.craft:
                startActivity(new Intent(getApplicationContext(), CraftMarketActivity.class));
                overridePendingTransition(0, 0);
                return true;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}