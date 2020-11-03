package com.example.viridi;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viridi.Model.AdminOrders;
import com.example.viridi.Model.AdminOrders;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminsNewOrdersActivity extends AppCompatActivity
{
    private RecyclerView ordersList;
    private DatabaseReference ordersRef;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admins_new_orders);

        ordersRef = FirebaseDatabase.getInstance().getReference().child("orders");

        ordersList = findViewById(R.id.orders_list);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrders> options = new FirebaseRecyclerOptions
                .Builder<AdminOrders>()
                .setQuery(ordersRef, AdminOrders.class)
                .build();

        FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolders> adapter =
                new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolders>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolders holder, final int i, @NonNull final AdminOrders adminOrders)
                    {
                        holder.userName.setText("Name: "+ adminOrders.getName());
                        holder.userPhoneNumber.setText("Phone: "+ adminOrders.getPhone());
                        holder.userTotalPrice.setText("Total Amount: "+ adminOrders.getTotalAmount());
                        holder.userDateTime.setText("Date & Time: "+ adminOrders.getDate()+" "+adminOrders.getTime());
                        holder.userShippingAddress.setText("Address: "+ adminOrders.getAddress()+ " "+ adminOrders.getCity());

                        holder.ShowOrdersBtn.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                String UID = getRef(i).getKey();

                                Intent intent = new Intent(AdminsNewOrdersActivity.this,AdminUserProducts.class);
                                intent.putExtra("uid",UID);
                                startActivity(intent);
                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                CharSequence options[] = new CharSequence[]
                                        {
                                                "Yes",
                                                "NO"
                                        };
                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminsNewOrdersActivity.this);
                                builder.setTitle("Have you shipped this order?");

                                builder.setItems(options, new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {

                                        if(which==0)
                                        {
                                            String UID = getRef(i).getKey();
                                            
                                            RemoveOrder(UID);
                                        }
                                        else
                                        {
                                            finish();
                                        }
                                    }
                                });

                                builder.show();


                            }
                        });

                     }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                        return new AdminOrdersViewHolders(view);
                    }
                };



        ordersList.setAdapter(adapter);
        adapter.startListening();

    }



    public static class AdminOrdersViewHolders extends RecyclerView.ViewHolder
    {

        public TextView userName, userPhoneNumber, userTotalPrice, userDateTime, userShippingAddress;
        public Button ShowOrdersBtn;


        public AdminOrdersViewHolders(@NonNull View itemView)
        {
            super(itemView);

            userName = itemView.findViewById(R.id.user_name);
            userPhoneNumber = itemView.findViewById(R.id.order_phone_number);
            userTotalPrice = itemView.findViewById(R.id.order_total_price);
            userDateTime = itemView.findViewById(R.id.order_date_time);
            userShippingAddress = itemView.findViewById(R.id.order_address_city);
            ShowOrdersBtn = itemView.findViewById(R.id.show_all_products_btn);
        }
    }

    private void RemoveOrder(String uid)
    {
        ordersRef.child(uid).removeValue();

    }



}
