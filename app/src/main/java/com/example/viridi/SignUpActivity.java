package com.example.viridi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class SignUpActivity extends AppCompatActivity {

    private Button createAccountBtn;
    private EditText inputName,inputPhoneNumber,inputPassword;
    private ProgressDialog loadingBar;

    private CountryCodePicker ccp;
    private String checker = "", phone = "";
    private EditText codeText;
    private LinearLayout linearLayout;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;      //
    private FirebaseAuth mAuth;  //
    private String mVerificationId; //
    private PhoneAuthProvider.ForceResendingToken mResendToken;   //



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sign_up);
        inputName = findViewById (R.id.register_username_input);
        inputPhoneNumber = findViewById (R.id.register_phone_number);
        inputPassword = findViewById (R.id.register_password);
        createAccountBtn = findViewById (R.id.registered_btn);

        ccp = findViewById (R.id.ccp);
        codeText = findViewById (R.id.codeText);


        linearLayout = findViewById (R.id.input_phone_number_layout);

        loadingBar = new ProgressDialog (this);



        mAuth = FirebaseAuth.getInstance ();


        createAccountBtn.setOnClickListener (new View.OnClickListener ()
        {
            @Override
            public void onClick(View v)
            {
                if (createAccountBtn.getText ().equals ("Continue")  || checker.equals ("Code Sent"))
                {
                    String verificationCode = codeText.getText ().toString ();

                    if (verificationCode.equals (""))
                    {

                    }
                    else
                    {
                        loadingBar.setTitle ("Code Verification");
                        loadingBar.setMessage ("Please wait, while we are verifying your otp");
                        loadingBar.setCanceledOnTouchOutside (false);
                        loadingBar.show ();

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential (mVerificationId, verificationCode);
                        signInWithPhoneAuthCredential (credential);

                    }
                }

                else
                {
                    String name = inputName.getText ().toString ();
                    final String phone = ccp.getFullNumberWithPlus ()+inputPhoneNumber.getText ().toString ();

                    String password = inputPassword.getText ().toString ();
                    String phoneWithoutCode = inputPhoneNumber.getText ().toString ();

                    if(TextUtils.isEmpty (name))
                    {
                        Toast.makeText (SignUpActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show ();
                    }
                    else if(TextUtils.isEmpty (phoneWithoutCode))
                    {
                        Toast.makeText (SignUpActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show ();
                    }
                    else if(TextUtils.isEmpty (password))
                    {
                        Toast.makeText (SignUpActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show ();
                    }
                    else
                    {
                        loadingBar.setTitle ("Creating Account");
                        loadingBar.setMessage ("Please wait while we are checking the credentials");
                        loadingBar.setCanceledOnTouchOutside (false);
                        loadingBar.show ();


                        //OTP code
                        if (!phone.equals (""))
                        {
                            final DatabaseReference RootRef;
                            RootRef = FirebaseDatabase.getInstance ().getReference ();


                            RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot)
                                {

                                    if (!(snapshot.child ("Users").child(phone).exists ()))
                                    {
                                        PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,60,TimeUnit.SECONDS,SignUpActivity.this,mCallbacks);
                                    }

                                    else
                                    {   Toast.makeText (SignUpActivity.this, "This"+phone+"Already exists.... \n Please Login or try using different number", Toast.LENGTH_LONG).show ();
                                        loadingBar.dismiss ();
                                        Intent intent = new Intent(SignUpActivity.this,SignUpActivity.class);
                                        startActivity (intent);
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

//                        loadingBar.setTitle ("Phone Number Verification");
//                        loadingBar.setMessage ("Please wait, while we are verifying");
//                        loadingBar.setCanceledOnTouchOutside (false);
//                        loadingBar.show ();
//
//                        PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,60,TimeUnit.SECONDS,SignUpActivity.this,mCallbacks);        // OnVerificationStateChangedCallbacks
                        }
                        else
                        {
                            Toast.makeText (SignUpActivity.this, "Please write valid phone Number", Toast.LENGTH_SHORT).show ();
                        }
                    }


                }
            }
        });

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks ()
        {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential (phoneAuthCredential);

            }

            @Override
            public void onVerificationFailed(FirebaseException e)
            {
                Toast.makeText (SignUpActivity.this, "Invalid Phone Number..."+e, Toast.LENGTH_LONG).show ();
                loadingBar.dismiss ();
                linearLayout.setVisibility (View.VISIBLE);
                inputName.setVisibility(View.VISIBLE);
                inputPassword.setVisibility(View.VISIBLE);

                createAccountBtn.setText ("Verify Mobile number");
                codeText.setVisibility (View.GONE);

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
            {
                super.onCodeSent (s, forceResendingToken);
                mVerificationId = s;
                mResendToken = forceResendingToken;

                linearLayout.setVisibility (View.GONE);
                inputName.setVisibility(View.GONE);
                inputPassword.setVisibility(View.GONE);

                checker = "Code Sent";
                createAccountBtn.setText ("Continue");
                codeText.setVisibility (View.VISIBLE);

                loadingBar.dismiss ();
                Toast.makeText (SignUpActivity.this, "Code has been sent to you, please write here", Toast.LENGTH_SHORT).show ();
            }
        };

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        String name = inputName.getText ().toString ();
        String phone = ccp.getFullNumberWithPlus ()+inputPhoneNumber.getText ().toString ();
        String password = inputPassword.getText ().toString ();
        String phoneWithoutCode = inputPhoneNumber.getText ().toString ();

        HashMap<String, Object> userdataMap = new HashMap<> ();
        userdataMap.put("name",name);
        userdataMap.put("phone",phone);
        userdataMap.put("password",password);

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance ().getReference ();

        RootRef.child ("Users").child (phone).updateChildren (userdataMap)
                .addOnCompleteListener (new OnCompleteListener<Void> () {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful ())
                        {
                            Toast.makeText (SignUpActivity.this, "Congratulation, your account is created successfully", Toast.LENGTH_SHORT).show ();
                            loadingBar.dismiss ();
                            Intent intent = new Intent (SignUpActivity.this,LoginActivity.class);
                            startActivity (intent);
                            finish();
                        }
                        else
                        {
                            loadingBar.dismiss ();
                            Toast.makeText (SignUpActivity.this, "Network Error : Please try again", Toast.LENGTH_LONG).show ();
                        }
                    }
                });
    }




}


