package com.example.viridi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BookFragment extends Fragment
{

    public ImageView bookShipvehicle,bookStubRemover;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book,container,false);


//        bookShipvehicle = (ImageView) getView().findViewById(R.id.shipping_vehicle);
//        bookStubRemover = (ImageView) getView().findViewById(R.id.stubRemover);
//
//        bookShipvehicle.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(getActivity(),DemoActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        bookStubRemover.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(getActivity(),DemoActivity.class);
//                startActivity(intent);
//
//            }
//        });
    }
}
