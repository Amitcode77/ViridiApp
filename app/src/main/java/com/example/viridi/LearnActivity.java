package com.example.viridi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);


        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view1);
        getLifecycle().addObserver(youTubePlayerView);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.learn);
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
                        startActivity(new Intent(getApplicationContext(), LearnActivity.class));
                        finish();
                        return true;
                    case R.id.learn:
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
