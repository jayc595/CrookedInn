package com.example.crookedinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
