package com.example.crookedinn.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crookedinn.Home;
import com.example.crookedinn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import java.util.HashMap;

public class AdminMaintainActivity extends AppCompatActivity {
    private Button applyChangesBtn, deleteBtn;
    private EditText itemName, itemPrice, itemDescription;
    private CheckBox GFchkbx, DFchkbx, outofStockchkbx;
    private TextView categoryName;

    private String productId = "";
    private DatabaseReference productRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_maintain);

        productId = getIntent().getStringExtra("pid");
        productRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productId);

        applyChangesBtn = findViewById(R.id.apply_changes_button);
        itemName = findViewById(R.id.item_name_layout_maintain);
        itemPrice = findViewById(R.id.item_price_layout_maintain);
        itemDescription = findViewById(R.id.product_description_maintain);
        GFchkbx = findViewById(R.id.gluten_free_chkb_maintain);
        DFchkbx = findViewById(R.id.dairyfree_chkb_maintain);
        outofStockchkbx = findViewById(R.id.outofstock_chkbx_maintain);
        categoryName = findViewById(R.id.categorynamelayout_maintain);
        deleteBtn = findViewById(R.id.delete_button);

        displaySpecificInfo();

        applyChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyChanges();
            }
            
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem();
            }
        });

    }

    private void deleteItem() {
        CharSequence delete[] = new CharSequence[] {
                "Yes",
                "No"
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminMaintainActivity.this);
        builder.setTitle("Are you sure you want to delete this item?");

        builder.setItems(delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0) {
                    productRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(AdminMaintainActivity.this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AdminMaintainActivity.this, Home.class);
                            startActivity(intent);
                        }
                    });
                }
                if (i == 1) {

                }
            }
        });
        builder.show();
    }

    private void applyChanges() {
        String iname = itemName.getText().toString();
        String iprice = itemPrice.getText().toString();
        String idescription = itemDescription.getText().toString();
        String igf = "";
        String idf = "";
        String istock = "";

        if(GFchkbx.isChecked()){
            igf = "Yes";
        } else {
            igf = "No";
        }
        if(DFchkbx.isChecked()){
            idf = "Yes";
        } else {
            idf = "No";
        }
        if(outofStockchkbx.isChecked()){
            istock = "No";
        } else {
            istock = "Yes";
        }

        if(iname.equals("")){
            Toast.makeText(this, "Please write down the Item name", Toast.LENGTH_SHORT).show();
        } else if(iprice.equals("")){
            Toast.makeText(this, "Please include a price for the Item", Toast.LENGTH_SHORT).show();
        } else if(idescription.equals("")){
            Toast.makeText(this, "Please include a description of the Item", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productId);
            productMap.put("description", idescription);
            productMap.put("price", iprice);
            productMap.put("iname", iname);
            productMap.put("gf", igf);
            productMap.put("df", idf);
            productMap.put("stock", istock);

            productRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(AdminMaintainActivity.this, "Changes applied successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (AdminMaintainActivity.this, AdminCategory.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }
    }

    private void displaySpecificInfo() {
        productRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String name = dataSnapshot.child("iname").getValue().toString();
                    String price = dataSnapshot.child("price").getValue().toString();
                    String description = dataSnapshot.child("description").getValue().toString();
                    String category = dataSnapshot.child("category").getValue().toString();
                    String GF = dataSnapshot.child("gf").getValue().toString();
                    String DF = dataSnapshot.child("df").getValue().toString();
                    String outofStock = dataSnapshot.child("stock").getValue().toString();

                    itemName.setText(name);
                    itemPrice.setText(price);
                    itemDescription.setText(description);
                    categoryName.setText(category);

                    if(DF.equals("Yes")){
                        DFchkbx.setChecked(true);
                    }
                    if(outofStock.equals("No")){
                        outofStockchkbx.setChecked(true);
                    }
                    if(GF.equals("Yes")){
                        GFchkbx.setChecked(true);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
