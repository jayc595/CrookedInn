package com.example.crookedinn.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crookedinn.R;

public class SettingsAdmin extends AppCompatActivity {

    private Button closeBar, openBar, kitchen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admin);

        closeBar = (Button) findViewById(R.id.close_bar);
        openBar = (Button) findViewById(R.id.open_bar);
        kitchen = (Button) findViewById(R.id.kitchen_btn);

        closeBar.setVisibility(View.GONE);

        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingsAdmin.this, "Kitchen Button pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (SettingsAdmin.this, KitchenSettings.class);
                startActivity(intent);
            }
        });

        openBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBar.setVisibility(View.GONE);
                closeBar.setVisibility(View.VISIBLE);
            }
        });
        closeBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeBar.setVisibility(View.GONE);
                openBar.setVisibility(View.VISIBLE);
            }
        });
    }
}
