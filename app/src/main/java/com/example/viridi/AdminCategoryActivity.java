package com.example.viridi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategoryActivity extends AppCompatActivity {



    private Button LogoutBtn, CheckOrderBtn;

    private ImageView addproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_admin_category);

        addproduct = (ImageView) findViewById(R.id.addproduct);

        addproduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,AdminAddProduct.class);
                startActivity(intent);

            }
        });




        CheckOrderBtn = (Button) findViewById(R.id.check_orders_btn);
        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);




        CheckOrderBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminsNewOrdersActivity.class);
                startActivity(intent);



            }
        });


        LogoutBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this,WelcomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

}

}
