package com.example.crookedinn.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.crookedinn.Model.AdminOrders;
import com.example.crookedinn.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_New_Orders extends AppCompatActivity {
    private RecyclerView ordersList;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__new__orders);

        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders");

        ordersList = findViewById(R.id.orders_list);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrders> options = new FirebaseRecyclerOptions.Builder<AdminOrders>().setQuery(ordersRef, AdminOrders.class).build();

        FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder> adapter = new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, final int position, @NonNull final AdminOrders model) {
                holder.TotalPrice.setText("£ " + model.getTotalAmount());
                holder.TableNumber.setText("Table Number: " + model.getTableNumber());
                holder.DateTime.setText("Ordered at: " + model.getTime() + "   " + model.getDate());
                holder.Description.setText("Notes:" + model.getNotes());

                holder.ShowAllProd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String uID = getRef(position).getKey();
                        Intent intent = new Intent(Admin_New_Orders.this, AdminUserProducts.class);
                        intent.putExtra("uid", uID);
                        startActivity(intent);
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Yes",
                                        "No"
                                };

                        AlertDialog.Builder builder = new AlertDialog.Builder(Admin_New_Orders.this);
                        builder.setTitle("Order Complete?");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                if (i == 0) {
                                    String uID = getRef(position).getKey();
                                    RemoveOrder(uID);
                                } else {
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
            public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                return new AdminOrdersViewHolder(view);
            }
        };

        ordersList.setAdapter(adapter);
        adapter.startListening();

    }
    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder {

        public TextView TableNumber, TotalPrice, DateTime, Description;
        public Button ShowAllProd;


        public AdminOrdersViewHolder(@NonNull View itemView) {

            super(itemView);

            TableNumber = itemView.findViewById(R.id.tablenumber_admin);
            TotalPrice = itemView.findViewById(R.id.totalprice_admin);
            DateTime = itemView.findViewById(R.id.datetime_admin);
            Description = itemView.findViewById(R.id.description_admin_order); //description
            ShowAllProd = itemView.findViewById(R.id.show_all_products);

        }
    }
    private void RemoveOrder(String uID) {
        ordersRef.child(uID).removeValue();
    }
}
