package com.example.crookedinn.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crookedinn.Home;
import com.example.crookedinn.Model.Openclosed;
import com.example.crookedinn.Model.Products;
import com.example.crookedinn.Prevalant.Prevalant;
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

public class KitchenSettings extends AppCompatActivity {

    private CheckBox bar, barmenu, lunchmenu, specialmenu;
    private String bar1 = "", barmenu1 = "", lunchmenu1 = "", specialmenu1 = "", catenumber;
    private Button applyChanges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_settings);

        bar = (CheckBox) findViewById(R.id.checkbox4);
        barmenu = (CheckBox) findViewById(R.id.checkbox1);
        lunchmenu = (CheckBox) findViewById(R.id.checkbox2);
        specialmenu = (CheckBox) findViewById(R.id.checkbox3);
        applyChanges = (Button) findViewById(R.id.apply_settings);

        getSettings();

        applyChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bar.isChecked()) {
                    bar1 = "Open";
                } else {
                    bar1 = "Closed";
                }

                if(barmenu.isChecked()) {
                    barmenu1 = "Open";
                } else {
                    barmenu1 = "Closed";
                }

                if(lunchmenu.isChecked()) {
                    lunchmenu1 = "Open";
                    catenumber = "a";
                } else {
                    lunchmenu1 = "Closed";
                    catenumber = "z";
                }

                if(specialmenu.isChecked()) {
                    specialmenu1 = "Open";
                } else {
                    specialmenu1 = "Closed";
                }

                applySettings();
          }


        });
    }


    private void getSettings() {
        DatabaseReference openref = FirebaseDatabase.getInstance().getReference().child("OpenClosed");
        openref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    Openclosed openclosed = dataSnapshot.getValue(Openclosed.class);

                    if(openclosed.getBar().equals("Open")){
                        bar.setChecked(true);
                    } else {
                        bar.setChecked(false);
                    }

                    if(openclosed.getBarmenu().equals("Open")){
                        barmenu.setChecked(true);
                    } else {
                        barmenu.setChecked(false);
                    }

                    if(openclosed.getLunchmenu().equals("Open")){
                        lunchmenu.setChecked(true);
                    } else {
                        lunchmenu.setChecked(false);
                    }

                    if(openclosed.getSpecialsmenu().equals("Open")){
                        specialmenu.setChecked(true);
                    } else {
                        specialmenu.setChecked(false);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void applySettings() {
//        Toast.makeText(this, "Apply Settings Proceeding...", Toast.LENGTH_SHORT).show();


        final DatabaseReference settingsRef = FirebaseDatabase.getInstance().getReference().child("OpenClosed");

        final HashMap<String, Object> settingsMap = new HashMap<>();
        settingsMap.put("barmenu", barmenu1);
        settingsMap.put("bar", bar1);
        settingsMap.put("lunchmenu", lunchmenu1);
        settingsMap.put("specialsmenu", specialmenu1);

        settingsRef.updateChildren(settingsMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(KitchenSettings.this, "You have updated successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });
        if(lunchmenu1.equals("Closed") || lunchmenu1.equals("Open")) {
            DatabaseReference test = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Products")
                    .orderByChild("category").startAt("lunch").endAt("lunch");
                HashMap<String, Object> updateCatenum = new HashMap<>();
                updateCatenum.put("catenumber", catenumber);

                test.updateChildren(updateCatenum).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent (KitchenSettings.this, AdminCategory.class);
                            startActivity(intent);
                            Toast.makeText(KitchenSettings.this, "Updated Categories", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(KitchenSettings.this, "ERROR ADDING", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }


