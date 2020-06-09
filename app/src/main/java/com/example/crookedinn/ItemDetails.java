package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.crookedinn.Model.Products;
import com.example.crookedinn.Prevalant.Prevalant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ItemDetails extends AppCompatActivity {
    private Button addToCartBtn;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName, productGf, productDf;
    private String productID = "", state = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        productID = getIntent().getStringExtra("pid");
        addToCartBtn = (Button) findViewById(R.id.pd_add_to_cart_button);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_button);
        productName = (TextView) findViewById(R.id.product_name_display);
        productDescription = (TextView) findViewById(R.id.product_info_display);
        productPrice = (TextView) findViewById(R.id.price_display);
        productGf = (TextView) findViewById(R.id.glutenfree_display);
        productDf = (TextView) findViewById(R.id.dairyfree_display);

        getProductDetails(productID);

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (state.equals("Order Placed")) {
                    Toast.makeText(ItemDetails.this, "Please wait till your order has been confirmed", Toast.LENGTH_LONG).show();
                }
                    if (state.equals("Order Confirmed")) {
                        Toast.makeText(ItemDetails.this, "Please wait till your order has arrived", Toast.LENGTH_SHORT).show();
                    }
                else {
                    addingToCartList();
                }
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
//        CheckOrderState(); //undo this later
    }

    private void addingToCartList() {
        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");


        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("pid", productID);
        cartMap.put("phone", Prevalant.currentOnlineUser.getPhone());
        cartMap.put("iname", productName.getText().toString());
        cartMap.put("price", productPrice.getText().toString());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);
        cartMap.put("quantity", numberButton.getNumber());
        cartMap.put("discount", "");
        cartMap.put("extra", "");
        cartMap.put("extra2", "");

        cartListRef.child("User View").child(Prevalant.currentOnlineUser.getPhone()).child("Products").child(productID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    cartListRef.child("Admin View").child(Prevalant.currentOnlineUser.getPhone()).child("Products").child(productID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ItemDetails.this, "Added to Cart Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ItemDetails.this, CartActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }

            }
        });
    }

    private void getProductDetails(String productID) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Products products = dataSnapshot.getValue(Products.class);

                    productName.setText(products.getIname());
                    productPrice.setText(products.getPrice());
                    productDescription.setText(products.getDescription());
                    productGf.setText("Gluten Free: " + products.getGf());
                    productDf.setText("Dairy Free: " + products.getDf());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void CheckOrderState() {
        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalant.currentOnlineUser.getPhone());
        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String orderState = dataSnapshot.child("state").getValue().toString();

                    if (orderState.equals("confirmed")) {
                        state = "Order Confirmed";

                    }
                    else if (orderState.equals("confirming")){
                        state = "Order Placed";



                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
