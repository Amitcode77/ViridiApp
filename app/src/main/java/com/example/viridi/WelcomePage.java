package com.example.viridi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.viridi.Model.Users;
import com.example.viridi.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class WelcomePage extends AppCompatActivity {

    private Button joinNowButton, loginButton;

    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        joinNowButton = findViewById (R.id.main_join_now_btn);
        loginButton = findViewById (R.id.main_login_btn);
        loadingBar = new ProgressDialog(this);

        Paper.init (this);



//
        joinNowButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePage.this,SignUpActivity.class);
                startActivity (intent);
            }
        });

        loginButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePage.this,LoginActivity.class);
                startActivity (intent);
            }
        });

//To remember me function : Gaining phone number and password from local saved storage

        String UserPhoneKey = Paper.book ().read (Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book ().read (Prevalent.UserPasswordKey);

        if(UserPhoneKey != "" && UserPasswordKey != "")                             // "" means null
        {
            if(!TextUtils.isEmpty (UserPhoneKey) && !TextUtils.isEmpty (UserPasswordKey))
            {
                AllowAccess(UserPhoneKey,UserPasswordKey);
                loadingBar.setTitle ("Already Logged in");
                loadingBar.setMessage ("Please wait for a second......");
                loadingBar.setCanceledOnTouchOutside (false);
                loadingBar.show ();
            }
        }


    }

    private void AllowAccess(final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance ().getReference ();

        RootRef.addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child ("Users").child (phone).exists()){
                    Users userData = dataSnapshot.child ("Users").child (phone).getValue (Users.class);
                    //  Toast.makeText (MainActivity.this, "Checked", Toast.LENGTH_SHORT).show ();

                    if(userData.getPhone ().equals (phone)) {
                        if (userData.getPassword ().equals (password))
                        {
                            Toast.makeText (WelcomePage.this, "Logged in Successfully", Toast.LENGTH_SHORT).show ();
                            loadingBar.dismiss ();
                            Intent intent = new Intent(WelcomePage.this,HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Prevalent.currentOnlineUsers = userData;
                            startActivity (intent);
                            finish();
                        }
                        else
                        {
                            loadingBar.dismiss ();
                            Toast.makeText (WelcomePage.this, "Password is incorrect!", Toast.LENGTH_SHORT).show ();
                        }
                    }
                }
                else {
                    //   Toast.makeText (loginActivity.this,"Account with "+phone+" doesn't exists",Toast.LENGTH_SHORT).show ();
                    loadingBar.dismiss ();
                    Toast.makeText (WelcomePage.this, "You need to create your account", Toast.LENGTH_SHORT).show ();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
