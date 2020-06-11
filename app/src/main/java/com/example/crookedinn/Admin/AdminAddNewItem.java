package com.example.crookedinn.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.crookedinn.R;
import com.rey.material.widget.CheckBox;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminAddNewItem extends AppCompatActivity {
    private String CategoryName, Description, Iname, Price, saveCurrentDate, saveCurrentTime;
    private Button AddNewItemButton;
    private EditText InputItemName, InputItemDescription, InputItemPrice, Title;
    private String productRandomKey;
    private DatabaseReference ProductRef;
    private ProgressDialog loadingBar;
    private CheckBox chkboxGlutenFree, chkboxDairyFree, chkboxOutOfStock;
    private String GF = "no", DF = "no", Stock = "yes", CategoryNumber = "Z";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_item);

        CategoryName = getIntent().getExtras().get("category").toString();
        ProductRef = FirebaseDatabase.getInstance().getReference().child("Products");

        AddNewItemButton = (Button) findViewById(R.id.add_new_item);
        InputItemName = (EditText) findViewById(R.id.item_name);
        InputItemDescription = (EditText) findViewById(R.id.item_description);
        InputItemPrice = (EditText) findViewById(R.id.item_price);
        Title = (EditText) findViewById(R.id.categorytitle);
        loadingBar = new ProgressDialog(this);

        chkboxDairyFree = (CheckBox) findViewById(R.id.dairyfree_chkb);
        chkboxGlutenFree = (CheckBox) findViewById(R.id.glutenfree_chkb);
        chkboxOutOfStock = (CheckBox) findViewById(R.id.outofstock_chkbx);

        InputItemDescription.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        Title.setText("Adding to '" + CategoryName + "' Category");

        AddNewItemButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view) {
                ValidateItemData();
            }
        });




//        if ( CategoryName.equals("softdrinks") || CategoryName.equals("hotdrinks")|| CategoryName.equals("beer") || CategoryName.equals("wine")|| CategoryName.equals("spirits")) {
//            chkboxGlutenFree.setVisibility(View.INVISIBLE);
//            chkboxDairyFree.setVisibility(View.INVISIBLE);
//            chkboxOutOfStock.setVisibility(View.INVISIBLE);
//            Stock = "NA";
//            GF = "NA";
//            DF = "NA";
            if (CategoryName.equals("Starters")){
            CategoryNumber = "a";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No";
        } else  if (CategoryName.equals("starters")){
            CategoryNumber = "a";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No";
        } else  if (CategoryName.equals("lunch")){
            CategoryNumber = "b";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No";
        } else  if (CategoryName.equals("grill")){
            CategoryNumber = "c";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No";
        } else  if (CategoryName.equals("pasta")){
            CategoryNumber = "d";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No";
        } else  if (CategoryName.equals("specials")){
            CategoryNumber = "e";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No";
        } else  if (CategoryName.equals("vegetarian")){
            CategoryNumber = "f";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No"; }
//         else  if (CategoryName.equals("children")){
//            CategoryNumber = "g";
//            chkboxDairyFree.setVisibility(View.VISIBLE);
//            chkboxGlutenFree.setVisibility(View.VISIBLE);
//            chkboxOutOfStock.setVisibility(View.VISIBLE);
//            Stock = "Yes";
//            GF = "No";
//            DF = "No";
//        }
        else  if (CategoryName.equals("sides")){
            CategoryNumber = "h";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No";
        } else  if (CategoryName.equals("dessert")){
            CategoryNumber = "i";
            chkboxDairyFree.setVisibility(View.VISIBLE);
            chkboxGlutenFree.setVisibility(View.VISIBLE);
            chkboxOutOfStock.setVisibility(View.VISIBLE);
            Stock = "Yes";
            GF = "No";
            DF = "No";
        } else  if (CategoryName.equals("softdrinks")){
            CategoryNumber = "j";
            chkboxGlutenFree.setVisibility(View.INVISIBLE);
            chkboxDairyFree.setVisibility(View.INVISIBLE);
            chkboxOutOfStock.setVisibility(View.INVISIBLE);
            Stock = "NA";
            GF = "NA";
            DF = "NA";
            } else  if (CategoryName.equals("hotdrinks")){
                CategoryNumber = "k";
                chkboxGlutenFree.setVisibility(View.INVISIBLE);
                chkboxDairyFree.setVisibility(View.INVISIBLE);
                chkboxOutOfStock.setVisibility(View.INVISIBLE);
                Stock = "NA";
                GF = "NA";
                DF = "NA";
            } else  if (CategoryName.equals("beer")){
                CategoryNumber = "l";
                chkboxGlutenFree.setVisibility(View.INVISIBLE);
                chkboxDairyFree.setVisibility(View.INVISIBLE);
                chkboxOutOfStock.setVisibility(View.INVISIBLE);
                Stock = "NA";
                GF = "NA";
                DF = "NA";
            } else  if (CategoryName.equals("wine")){
                CategoryNumber = "m";
                chkboxGlutenFree.setVisibility(View.INVISIBLE);
                chkboxDairyFree.setVisibility(View.INVISIBLE);
                chkboxOutOfStock.setVisibility(View.INVISIBLE);
                Stock = "NA";
                GF = "NA";
                DF = "NA";
            } else  if (CategoryName.equals("spirits")){
                CategoryNumber = "n";
                chkboxGlutenFree.setVisibility(View.INVISIBLE);
                chkboxDairyFree.setVisibility(View.INVISIBLE);
                chkboxOutOfStock.setVisibility(View.INVISIBLE);
                Stock = "NA";
                GF = "NA";
                DF = "NA";
            }

    }

        private void ValidateItemData(){
        Description = InputItemDescription.getText().toString();
        Price = InputItemPrice.getText().toString();
        Iname = InputItemName.getText().toString();


            if (TextUtils.isEmpty(Description)) {
            Toast.makeText(this, "Please include a product description", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Price)) {
                Toast.makeText(this, "Please include a product price", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(Iname)) {
                Toast.makeText(this, "Please include a product name", Toast.LENGTH_SHORT).show();
            }
            else if(!Price.contains(".")) {
                Toast.makeText(this, "Price must be entered in decimal format", Toast.LENGTH_SHORT).show();
            }else {
                StoreItemInformation();

            }
        }
        private void StoreItemInformation(){

            if(chkboxGlutenFree.isChecked()) {
                GF = "yes";
            }
//            else {
//                GF = "no";
//            }

            if(chkboxDairyFree.isChecked()) {
                DF = "yes";
            }

            if(chkboxOutOfStock.isChecked()) {
                Stock = "no";
            }
//            else {
//                DF = "no";
//            }

            loadingBar.setTitle("Adding New Item");
            loadingBar.setMessage("Please wait, we are adding the Item");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
            saveCurrentDate = currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime = currentTime.format(calendar.getTime());

            productRandomKey = saveCurrentDate + saveCurrentTime;
            SaveItemInformation ();
        }

    private void SaveItemInformation() {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", Description);
        productMap.put("category", CategoryName);
        productMap.put("catenumber", CategoryNumber);
        productMap.put("price", Price);
        productMap.put("iname", Iname);
        productMap.put("gf", GF);
        productMap.put("df", DF);
        productMap.put("stock", Stock);




        ProductRef.child(productRandomKey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(AdminAddNewItem.this, AdminCategory.class);
                    startActivity(intent);

                    loadingBar.dismiss();
                    Toast.makeText(AdminAddNewItem.this, "Item has been added successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    loadingBar.dismiss();
                    String message = task.getException().toString();
                    Toast.makeText(AdminAddNewItem.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
