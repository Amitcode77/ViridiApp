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

public class SellActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.sell);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.book:
                        startActivity(new Intent(getApplicationContext(), BookActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.buy:
                        startActivity(new Intent(getApplicationContext(), BuyActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.sell:
                        return true;
                    case R.id.learn:
                        startActivity(new Intent(getApplicationContext(), LearnActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.craft:
                        startActivity(new Intent(getApplicationContext(), CraftMarketActivity.class));
                        finish();
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }


}