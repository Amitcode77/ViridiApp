package com.example.viridi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.viridi.Model.Users;
import com.example.viridi.Prevalent.Prevalent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    Toolbar toolbar;

    private DatabaseReference ProductsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_activity);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        ProductsRef = FirebaseDatabase.getInstance ().getReference ().child ("Products");
        Paper.init(this);

        View headerView = navigationView.getHeaderView(0);
        final TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);

        final CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);



        DatabaseReference UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUsers.getPhone());

        UsersRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    if (dataSnapshot.child("image").exists())
                    {
                        String image = dataSnapshot.child("image").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();

                        userNameTextView.setText(name);
                        Picasso.get().load(image).placeholder(R.drawable.profile).into(profileImageView);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

//               userNameTextView.setText(Prevalent.currentOnlineUsers.getName());
//        Picasso.get().load(Prevalent.currentOnlineUsers.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.book);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.book:
                        return true;
                    case R.id.buy:
                        startActivity(new Intent(getApplicationContext(),BuyActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.sell:
                        startActivity(new Intent(getApplicationContext(),SellActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.learn:
                        startActivity(new Intent(getApplicationContext(),LearnActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.craft:
                        startActivity(new Intent(getApplicationContext(),CraftMarketActivity.class));
                        finish();
                        overridePendingTransition(0,0);
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
                Intent intBook = new Intent(HomeActivity.this, BookActivity.class);
                startActivity(intBook);
                break;
            case R.id.buy:
                Intent intBuy = new Intent(HomeActivity.this, BuyActivity.class);
                startActivity(intBuy);
                break;
            case R.id.sell:
                Intent intSell = new Intent(HomeActivity.this, SellActivity.class);
                startActivity(intSell);
                break;
            case R.id.learn:
                Intent intLearn = new Intent(HomeActivity.this, LearnActivity.class);
                startActivity(intLearn);
                break;
            case R.id.craftWork:
                Intent intent1 = new Intent(HomeActivity.this, CraftMarketActivity.class);
                startActivity(intent1);
                break;

            case R.id.settings:
                Intent intentSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intentSettings);
                break;

            case R.id.logout:
                Paper.book().destroy();
                Intent intentLogout = new Intent(HomeActivity.this, WelcomePage.class);
                intentLogout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentLogout);
                finish();
                break;
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
