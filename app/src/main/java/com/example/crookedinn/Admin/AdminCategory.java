package com.example.crookedinn.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.crookedinn.DrinksAddItem;
import com.example.crookedinn.Home;
import com.example.crookedinn.MainActivity;
import com.example.crookedinn.R;

public class AdminCategory extends AppCompatActivity {
    private ImageView starters, lunch;
    private ImageView grill, specials, vegetarian, pasta;
    private ImageView sides, dessert, drinks;

    private Button LogoutBtn, CheckOrderBtn, MaintainProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn = (Button) findViewById(R.id.logout_btn);
        CheckOrderBtn = (Button) findViewById(R.id.check_new_orders);
        MaintainProducts = (Button) findViewById(R.id.maintain_products);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Toast.makeText(AdminCategory.this, "Successfully, logged out!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });

        CheckOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, Admin_New_Orders.class);
                startActivity(intent);
            }
        });

        MaintainProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, Home.class);
                intent.putExtra("category", "none");
                intent.putExtra("Admin", "Admin");
                startActivity(intent);
            }
        });

        starters = (ImageView) findViewById(R.id.starters);
        lunch = (ImageView) findViewById(R.id.lunch);

        grill = (ImageView) findViewById(R.id.grill);
        specials = (ImageView) findViewById(R.id.specials);
        vegetarian = (ImageView) findViewById(R.id.vegetarian);
        pasta = (ImageView) findViewById(R.id.pasta);

        sides = (ImageView) findViewById(R.id.sides);
        dessert = (ImageView) findViewById(R.id.dessert);
        drinks = (ImageView) findViewById(R.id.drinks);


        starters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, AdminAddNewItem.class);
                intent.putExtra("category", "starters");
                intent.putExtra("foodcategory", "none");
                startActivity(intent);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, AdminAddNewItem.class);
                intent.putExtra("category", "lunch");
                intent.putExtra("foodcategory", "none");
                startActivity(intent);
            }
        });

        grill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, AdminAddNewItem.class);
                intent.putExtra("category", "grill");
                intent.putExtra("foodcategory", "none");
                startActivity(intent);
            }
        });

        specials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, AdminAddNewItem.class);
                intent.putExtra("category", "specials");
                intent.putExtra("foodcategory", "none");
                startActivity(intent);
            }
        });

        vegetarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, AdminAddNewItem.class);
                intent.putExtra("category", "vegetarian");
                intent.putExtra("foodcategory", "none");
                startActivity(intent);
            }
        });

        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, AdminAddNewItem.class);
                intent.putExtra("category", "pasta");
                intent.putExtra("foodcategory", "none");
                startActivity(intent);
            }
        });

        sides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, AdminAddNewItem.class);
                intent.putExtra("category", "sides");
                intent.putExtra("foodcategory", "none");
                startActivity(intent);
            }
        });

        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, AdminAddNewItem.class);
                intent.putExtra("category", "dessert");
                intent.putExtra("foodcategory", "none");
                startActivity(intent);
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (AdminCategory.this, DrinksAddItem.class);
                startActivity(intent);
            }
        });
    }
}
