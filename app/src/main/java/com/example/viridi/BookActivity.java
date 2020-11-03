package com.example.viridi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BookActivity extends AppCompatActivity
{
    ImageView bookShipvehicle,bookStubRemover;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookShipvehicle = (ImageView) findViewById(R.id.shipping_vehicle);
        bookStubRemover = (ImageView) findViewById(R.id.stubRemover);

        bookShipvehicle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(BookActivity.this,DemoActivity.class);
                startActivity(intent);
            }
        });

        bookStubRemover.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(BookActivity.this,DemoActivity.class);
                startActivity(intent);

            }
        });

    }
}