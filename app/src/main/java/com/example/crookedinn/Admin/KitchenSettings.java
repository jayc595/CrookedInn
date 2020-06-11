package com.example.crookedinn.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.crookedinn.R;
import com.rey.material.widget.CheckBox;

public class KitchenSettings extends AppCompatActivity {

    private CheckBox bar, barmenu, lunchmenu, specialmenu;
    private String bar1 = "", barmenu1 = "", lunchmenu1 = "", specialmenu1 = "";
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
                } else {
                    lunchmenu1 = "Closed";
                }

                if(specialmenu.isChecked()) {
                    specialmenu1 = "Open";
                } else {
                    specialmenu1 = "Closed";
                }

                if(barmenu1.equals("Open")) {
                    Toast.makeText(KitchenSettings.this, "Bar menu open?", Toast.LENGTH_SHORT).show();
                    //add Open to the database
                } else {
                    Toast.makeText(KitchenSettings.this, "Bar menu closed?", Toast.LENGTH_SHORT).show();
                    // update to closed in the database 
                }
            }
        });
    }

}
