package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.rey.material.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.crookedinn.Model.AdditionalOptions;
import com.example.crookedinn.Model.Openclosed;
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
    private TextView productPrice, productDescription, productName, productGf, productDf, productPnull, productCnull;
    private String productID = "", state = "Normal", OptionalAdd = "None";
    private ImageView GFimg, DFimg, NotGFimg, NotDFimg;
    private RelativeLayout rl1;
    private String CategoryTitle;
    private RadioButton Op1, Op2, Op3, Op4, Op5, Op6;

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

        productPnull = (TextView) findViewById(R.id.price_display2);
        productCnull = (TextView) findViewById(R.id.categorynull) ;

        DFimg = (ImageView) findViewById(R.id.DF_image);
        GFimg = (ImageView) findViewById(R.id.GF_image);
        NotDFimg = (ImageView) findViewById(R.id.NotDF_image);
        NotGFimg = (ImageView) findViewById(R.id.NotGF_image);

        Op1 = findViewById(R.id.option1chkbox);
        Op2 = findViewById(R.id.option2chkbox);
        Op3 = findViewById(R.id.option3chkbox);
        Op4 = findViewById(R.id.option4chkbox);
        Op5 = findViewById(R.id.option5chkbox);
        Op6 = findViewById(R.id.option6chkbox);

        CategoryTitle = getIntent().getStringExtra("FoodCategory");


        rl1 = (RelativeLayout) findViewById(R.id.relativelayout1);

        //Later add all options


        getProductDetails(productID);

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Op1.isChecked()){
                    OptionalAdd = Op1.getText().toString();
                }
                else if(Op2.isChecked()){
                    OptionalAdd = Op2.getText().toString();
                }
                else if(Op3.isChecked()){
                    OptionalAdd = Op3.getText().toString();
                }
                else if(Op4.isChecked()){
                    OptionalAdd = Op4.getText().toString();
                }
                else if(Op5.isChecked()){
                    OptionalAdd = Op5.getText().toString();
                }
                else if(Op6.isChecked()){
                    OptionalAdd = Op6.getText().toString();
                }

                else {
                    OptionalAdd = "None";

                }

                final String category = productCnull.getText().toString();
                DatabaseReference openref = FirebaseDatabase.getInstance().getReference().child("OpenClosed");
                openref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            Openclosed openclosed = dataSnapshot.getValue(Openclosed.class);
                            if(openclosed.getBar().equals("Closed")){
                                Toast.makeText(ItemDetails.this, "Sorry we are currently Closed!", Toast.LENGTH_LONG).show();
                                finish();
                                }
                            if(openclosed.getLunchmenu().equals("Closed") && category.equals("lunch")){
                                Toast.makeText(ItemDetails.this, "Lunch menu is currently not available", Toast.LENGTH_LONG).show();
                                finish();
                            }
                            if(openclosed.getSpecialsmenu().equals("Closed") && category.equals("specials")){
                                Toast.makeText(ItemDetails.this, "Special menu is currently not available", Toast.LENGTH_LONG).show();
                                finish();
                            }
                            if(openclosed.getBarmenu().equals("Closed") && category.equals("starters") || category.equals("grill") || category.equals("pasta") || category.equals("dessert") || category.equals("sides")){
                                Toast.makeText(ItemDetails.this, "We currently aren't serving food", Toast.LENGTH_SHORT).show();
                                finish();
                            }

//                            else {
//                                if (state.equals("Order Placed")) {
//                                    Toast.makeText(ItemDetails.this, "Please wait till your order has been confirmed", Toast.LENGTH_LONG).show();
//                                }
//                                if (state.equals("Order Confirmed")) {
//                                    Toast.makeText(ItemDetails.this, "Please wait till your order has arrived", Toast.LENGTH_SHORT).show();
//                                }
                                else {
                                    addingToCartList();
                                }

                           // }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
//        CheckOrderState(); //undo this later
        DatabaseReference additionalRef = FirebaseDatabase.getInstance().getReference().child("FoodCategory").child(CategoryTitle);
        additionalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    AdditionalOptions additionaloptions = dataSnapshot.getValue(AdditionalOptions.class);
                        if(additionaloptions.getOption1().equals("None") || additionaloptions.getOption2().equals("None")){
                            rl1.setVisibility(View.GONE);
                        } else if(!additionaloptions.getOption1().equals("None") || !additionaloptions.getOption2().equals("None")){
                            rl1.setVisibility(View.VISIBLE);
                            Op1.setText(additionaloptions.getOption1());
                            Op2.setText(additionaloptions.getOption2());

                            if(additionaloptions.getOption3().equals("None")){
                                Op3.setVisibility(View.GONE);
                            } else {
                                Op3.setText(additionaloptions.getOption3());
                            }
                            if (additionaloptions.getOption4().equals("None")){
                                Op4.setVisibility(View.GONE);
                            } else {
                                Op4.setText(additionaloptions.getOption4());
                            }
                            if(additionaloptions.getOption5().equals("None")){
                                Op5.setVisibility(View.GONE);
                            } else {
                                Op5.setText(additionaloptions.getOption5());
                            }
                            if(additionaloptions.getOption6().equals("None")){
                                Op6.setVisibility(View.GONE);
                            } else {
                                Op6.setText(additionaloptions.getOption6());
                            }
                        }
                     else {
                        Toast.makeText(ItemDetails.this, "Info not found", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        cartMap.put("price", productPnull.getText().toString());
        cartMap.put("category", productCnull.getText().toString());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);
        cartMap.put("quantity", numberButton.getNumber());
        cartMap.put("discount", "");
        cartMap.put("addon", OptionalAdd);

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
                    productPnull.setText(products.getPrice());
                    productPrice.setText("£" + products.getPrice());
                    productDescription.setText(products.getDescription());
                    productCnull.setText(products.getCategory());


                    CategoryTitle = products.getFoodCategory();
                    if(CategoryTitle.equals("none")){
                        rl1.setVisibility(View.GONE);
                    } else {
                        rl1.setVisibility(View.VISIBLE);
                    }
//                    productGf.setText("Gluten Free: " + products.getGf());
//                    productDf.setText("Dairy Free: " + products.getDf());

                    if(products.getGf().equals("Yes")){
                        productGf.setText("This item is Gluten Free!");
                        NotGFimg.setVisibility(View.INVISIBLE);
                    } else if (products.getGf().equals("No")){
                        productGf.setText("This item is NOT Gluten Free!");
                        GFimg.setVisibility(View.INVISIBLE);
                    } else {
                        productGf.setVisibility(View.GONE);
                        GFimg.setVisibility(View.GONE);
                        NotGFimg.setVisibility(View.GONE);
                    }
                    if(products.getDf().equals("Yes")){
                        productDf.setText("This item is Dairy Free!");
                        NotDFimg.setVisibility(View.INVISIBLE);
                    } else if (products.getDf().equals("No")){
                        productDf.setText("This item is NOT Dairy Free!");
                        DFimg.setVisibility(View.INVISIBLE);
                    } else {
                        productDf.setVisibility(View.GONE);
                        DFimg.setVisibility(View.GONE);
                        NotDFimg.setVisibility(View.GONE);
                    }
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
