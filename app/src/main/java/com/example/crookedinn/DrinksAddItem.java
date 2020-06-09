package com.example.crookedinn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.crookedinn.Admin.AdminAddNewItem;

public class DrinksAddItem extends AppCompatActivity {
    private ImageView hotdrinks, softdrinks, wine, spirits, beer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_add_item);

        hotdrinks = (ImageView) findViewById(R.id.hotdrinks);
        softdrinks = (ImageView) findViewById(R.id.softdrinks);
        wine = (ImageView) findViewById(R.id.wine);
        spirits = (ImageView) findViewById(R.id.spirits);
        beer = (ImageView) findViewById(R.id.beer);


        hotdrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DrinksAddItem.this, AdminAddNewItem.class);
                intent.putExtra("category", "hotdrinks");
                startActivity(intent);
            }
        });

        softdrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DrinksAddItem.this, AdminAddNewItem.class);
                intent.putExtra("category", "softdrinks");
                startActivity(intent);
            }
        });

        wine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DrinksAddItem.this, AdminAddNewItem.class);
                intent.putExtra("category", "wine");
                startActivity(intent);
            }
        });

        spirits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DrinksAddItem.this, AdminAddNewItem.class);
                intent.putExtra("category", "spirits");
                startActivity(intent);
            }
        });

        beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (DrinksAddItem.this, AdminAddNewItem.class);
                intent.putExtra("category", "beer");
                startActivity(intent);
            }
        });
    }
}
