package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.crookedinn.Model.Openclosed;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AllCategoriesUser extends AppCompatActivity {
    private ImageView startersuser, lunchuser;
    private ImageView grilluser, specialsuser, vegetarianuser, pastauser;
    private ImageView sidesuser, dessertuser, drinksuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories_user);

        startersuser = (ImageView) findViewById(R.id.startersuser);
        lunchuser = (ImageView) findViewById(R.id.lunchuser);
        grilluser = (ImageView) findViewById(R.id.grilluser);
        specialsuser = (ImageView) findViewById(R.id.specialsuser);
        vegetarianuser = (ImageView) findViewById(R.id.vegetarianuser);
        pastauser = (ImageView) findViewById(R.id.pastauser);
        sidesuser = (ImageView) findViewById(R.id.sidesuser);
        dessertuser = (ImageView) findViewById(R.id.dessertuser);
        drinksuser = (ImageView) findViewById(R.id.drinks);

        DatabaseReference openref = FirebaseDatabase.getInstance().getReference().child("OpenClosed");
        openref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Openclosed openclosed = dataSnapshot.getValue(Openclosed.class);
                if (openclosed.getLunchmenu().equals("Closed")){
                    lunchuser.setVisibility(View.GONE);
                } else if (openclosed.getLunchmenu().equals("Open")){
                    lunchuser.setVisibility(View.VISIBLE);
                } if (openclosed.getSpecialsmenu().equals("Closed")){
                    specialsuser.setVisibility(View.GONE);
                } else if (openclosed.getSpecialsmenu().equals("Open")){
                    specialsuser.setVisibility(View.VISIBLE);
                } if (openclosed.getBarmenu().equals("Closed")){
                    startersuser.setVisibility(View.GONE);
                    grilluser.setVisibility(View.GONE);
                    sidesuser.setVisibility(View.GONE);
                    pastauser.setVisibility(View.GONE);
                    dessertuser.setVisibility(View.GONE);
                    vegetarianuser.setVisibility(View.GONE);
                } else if (openclosed.getBarmenu().equals("Open")){
                    startersuser.setVisibility(View.VISIBLE);
                    grilluser.setVisibility(View.VISIBLE);
                    sidesuser.setVisibility(View.VISIBLE);
                    pastauser.setVisibility(View.VISIBLE);
                    dessertuser.setVisibility(View.VISIBLE);
                    vegetarianuser.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        startersuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AllCategoriesUser.this, Home.class);
                intent.putExtra("Admin", "User");
                intent.putExtra("category", "starters");
                startActivity(intent);
            }
        });
        lunchuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AllCategoriesUser.this, Home.class);
                intent.putExtra("Admin", "User");
                intent.putExtra("category", "lunch");
                startActivity(intent);
            }
        });
        grilluser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AllCategoriesUser.this, Home.class);
                intent.putExtra("Admin", "User");
                intent.putExtra("category", "grill");
                startActivity(intent);
            }
        });
        specialsuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AllCategoriesUser.this, Home.class);
                intent.putExtra("Admin", "User");
                intent.putExtra("category", "specials");
                startActivity(intent);
            }
        });
        vegetarianuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AllCategoriesUser.this, Home.class);
                intent.putExtra("Admin", "User");
                intent.putExtra("category", "vegeterian");
                startActivity(intent);
            }
        });
        pastauser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AllCategoriesUser.this, Home.class);
                intent.putExtra("Admin", "User");
                intent.putExtra("category", "pasta");
                startActivity(intent);
            }
        });
        dessertuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AllCategoriesUser.this, Home.class);
                intent.putExtra("Admin", "User");
                intent.putExtra("category", "dessert");
                startActivity(intent);
            }
        });
        sidesuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AllCategoriesUser.this, Home.class);
                intent.putExtra("Admin", "User");
                intent.putExtra("category", "sides");
                startActivity(intent);
            }
        });
    }
}
