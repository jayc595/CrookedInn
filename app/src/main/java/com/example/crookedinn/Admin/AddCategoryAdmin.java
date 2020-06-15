package com.example.crookedinn.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crookedinn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;

public class AddCategoryAdmin extends AppCompatActivity {

    private EditText CategoryTitle, Option1, Option2, Option3, Option4, Option5, Option6 ;
    private Button AddCategory;
    private String CategoryT, Opt1, Opt2, Opt3, Opt4, Opt5, Opt6;
    private DatabaseReference categoryRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_admin);

        categoryRef = FirebaseDatabase.getInstance().getReference().child("FoodCategory");

        AddCategory = (Button) findViewById(R.id.add_new_item_category);
        CategoryTitle = (EditText) findViewById(R.id.optionname);
        Option1 = (EditText) findViewById(R.id.option1);
        Option2 = (EditText) findViewById(R.id.option2);
        Option3 = (EditText) findViewById(R.id.option3);
        Option4 = (EditText) findViewById(R.id.option4);
        Option5 = (EditText) findViewById(R.id.option5);
        Option6 = (EditText) findViewById(R.id.option6);

        AddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateInformation();
            }
        });


    }

    private void ValidateInformation() {
        CategoryT = CategoryTitle.getText().toString();
        Opt1 = Option1.getText().toString();
        Opt2 = Option2.getText().toString();
        Opt3 = Option3.getText().toString();
        Opt4 = Option4.getText().toString();
        Opt5 = Option5.getText().toString();
        Opt6 = Option6.getText().toString();

        if (TextUtils.isEmpty(CategoryT)) {
            Toast.makeText(this, "Please include a product category", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Opt1)){
            Toast.makeText(this, "Please add least add 2 options", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Opt2)){
            Toast.makeText(this, "Please add at least one more option", Toast.LENGTH_SHORT).show();
        }

        else {
            AddCategory();

        }
    }

    private void AddCategory() {
        if (Option3.getText().toString().equals("")){
            Opt3 = "None";
        }
        if (Option4.getText().toString().equals("")){
            Opt4 = "None";
        }
        if (Option5.getText().toString().equals("")){
            Opt5 = "None";
        }
        if (Option6.getText().toString().equals("")){
            Opt6 = "None";
        }

        HashMap<String, Object> AddCategory = new HashMap<>();
        AddCategory.put("categoryTitle", CategoryT);
        AddCategory.put("Option1", Opt1);
        AddCategory.put("Option2", Opt2);
        AddCategory.put("Option3", Opt3);
        AddCategory.put("Option4", Opt4);
        AddCategory.put("Option5", Opt5);
        AddCategory.put("Option6", Opt6);

        categoryRef.child(CategoryT).updateChildren(AddCategory).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(AddCategoryAdmin.this, "Task is successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCategoryAdmin.this, AdminCategory.class);
                    startActivity(intent);
                }
            }
        });

    }

}
