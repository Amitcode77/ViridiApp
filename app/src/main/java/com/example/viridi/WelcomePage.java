package com.example.viridi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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


//        Paper.init (this);
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

//        String UserPhoneKey = Paper.book ().read (Prevalent.UserPhoneKey);
//        String UserPasswordKey = Paper.book ().read (Prevalent.UserPasswordKey);
//
//        if(UserPhoneKey != "" && UserPasswordKey != "")                             // "" means null
//        {
//            if(!TextUtils.isEmpty (UserPhoneKey) && !TextUtils.isEmpty (UserPasswordKey))
//            {
//                AllowAccess(UserPhoneKey,UserPasswordKey);
//                loadingBar.setTitle ("Already Logged in");
//                loadingBar.setMessage ("Please wait for a second......");
//                loadingBar.setCanceledOnTouchOutside (false);
//                loadingBar.show ();
//            }
//        }


    }
}
