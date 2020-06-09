package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.GetChars;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chivorn.smartmaterialspinner.SmartMaterialSpinner;
import com.example.crookedinn.Model.Cart;
import com.example.crookedinn.Prevalant.Prevalant;
import com.example.crookedinn.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button NextProcessBtn;
    private TextView TxtTotalAmount, txtMSG1;
    private EditText AdditionalNotes;
    private RelativeLayout rl1;

    private int overTotalPrice = 0;
    private String TableNum = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Spinner spinner = (Spinner) findViewById(R.id.table_number);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tables, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);

        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NextProcessBtn = (Button) findViewById(R.id.cart_next);
        TxtTotalAmount = (TextView) findViewById(R.id.total_price);
        txtMSG1 = (TextView) findViewById(R.id.msg1);
        AdditionalNotes = (EditText) findViewById(R.id.additional_notes);
        rl1 = (RelativeLayout) findViewById(R.id.rl1);

//       TableNumber = (TextView) findViewById(R.id.table_number);

        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence options[] = new CharSequence[] {
                        "Upstairs",
                        "Outside",
                        "Table 1",
                        "Table 2",
                        "Table 3",
                        "Table 4",
                        "Table 5",
                        "Table 6",
                        "Table 7",
                        "Table 8",
                        "Table 9",
                        "Table 10",
                        "Table 11",
                        "Table 12",
                        "Table 13",
                        "Table 14"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                builder.setTitle("Table Number:");

                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (i == 0) {
                            TableNum = "Upstairs";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 1) {
                            TableNum = "Outside";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 2) {
                            TableNum = "1";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 3) {
                            TableNum = "2";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 4) {
                            TableNum = "3";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 5) {
                            TableNum = "4";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 6) {
                            TableNum = "5";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 7) {
                            TableNum = "6";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 8) {
                            TableNum = "7";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 9) {
                            TableNum = "8";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 10) {
                            TableNum = "9";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 11) {
                            TableNum = "10";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 12) {
                            TableNum = "11";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 13) {
                            TableNum = "12";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                        if (i == 14) {
                            TableNum = "13";
                            Intent intent = new Intent(CartActivity.this, ConfirmFinalOrder.class);
                            intent.putExtra("Total Price", String.valueOf(overTotalPrice));
                            intent.putExtra("Table Number", String.valueOf(TableNum));
                            startActivity(intent);
                            finish();
                        }
                    }
                });
                builder.show();


            }
        });
    }


    @Override
    protected void onStart() {
        CheckOrderState();
        super.onStart();

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>().setQuery(cartListRef.child("User View").child(Prevalant.currentOnlineUser.getPhone()).child("Products"), Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart model) {
            holder.txtItemQuantity.setText("Quantity: " + model.getQuantity());
            holder.txtItemName.setText(model.getIname());
            holder.txtItemPrice.setText("£" + model.getPrice());

            int oneTyprProductTPrice = ((Integer.valueOf(model.getPrice()))) * Integer.valueOf(model.getQuantity());
            overTotalPrice = overTotalPrice + oneTyprProductTPrice;
            TxtTotalAmount.setText("Total Price: £" + String.valueOf(overTotalPrice));


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CharSequence options[] = new CharSequence[] {
                            "Edit",
                            "Remove"
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                    builder.setTitle("Order Options:");

                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            if (i == 0) {
                                Intent intent = new Intent(CartActivity.this, ItemDetails.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);
                            }
                            if (i == 1) {
                                cartListRef.child("User View").child(Prevalant.currentOnlineUser.getPhone()).child("Products").child(model.getPid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(CartActivity.this, "Item has been removed", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(CartActivity.this, Home.class);
                                            intent.putExtra("category", "none");
                                            intent.putExtra("Admin", "User");
                                            startActivity(intent);
                                        }
                                    }
                                });
                            }
                        }
                    });
                    builder.show();
                }
            });
            }
            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();


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
                        TxtTotalAmount.setText("Order Confirmed");
                        recyclerView.setVisibility(View.VISIBLE);

                        txtMSG1.setText("Your order has been confirmed, and your food will be with you soon");
                        txtMSG1.setVisibility(View.VISIBLE);
//                        AdditionalNotes.setVisibility(View.GONE);
                        NextProcessBtn.setVisibility(View.GONE);
                        rl1.setVisibility(View.GONE);

                    }
                    else if (orderState.equals("confirming")){
                        TxtTotalAmount.setText("Order Confirming");

                        txtMSG1.setText("Please wait, we are confirming your order");
//                        AdditionalNotes.setVisibility(View.GONE);
                        txtMSG1.setVisibility(View.VISIBLE);
                        NextProcessBtn.setVisibility(View.GONE);
                        rl1.setVisibility(View.GONE);


                    } else {
                        TxtTotalAmount.setText("Total Amount: ");
//                        AdditionalNotes.setVisibility(View.VISIBLE);
                        txtMSG1.setVisibility(View.INVISIBLE);
                        NextProcessBtn.setVisibility(View.VISIBLE);
                        rl1.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
