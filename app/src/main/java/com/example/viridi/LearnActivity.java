package com.example.viridi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);


        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view1);
        getLifecycle().addObserver(youTubePlayerView);

    }
}
