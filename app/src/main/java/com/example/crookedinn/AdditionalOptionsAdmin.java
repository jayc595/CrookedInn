package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crookedinn.Admin.AdminAddNewItem;
import com.example.crookedinn.Admin.Admin_New_Orders;
import com.example.crookedinn.Interface.ItemClickListener;
import com.example.crookedinn.Model.AdditionalOptions;
import com.example.crookedinn.ViewHolder.AdditionalOptionsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdditionalOptionsAdmin extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layout;
    private TextView CategoryTitle;
    private String CategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_options_admin);

        CategoryName = getIntent().getExtras().get("category").toString();

        recycler = findViewById(R.id.recycler_options);
        recycler.setHasFixedSize(true);
        layout = new LinearLayoutManager(this);
        recycler.setLayoutManager(layout);

//        CategoryTitle = findViewById(R.id.CategoryOptionsTitle);

        DatabaseReference optionsAdditional = FirebaseDatabase.getInstance().getReference().child("FoodCategory");

        FirebaseRecyclerOptions<AdditionalOptions> options = new FirebaseRecyclerOptions.Builder<AdditionalOptions>().setQuery(optionsAdditional, AdditionalOptions.class).build();

        FirebaseRecyclerAdapter<AdditionalOptions, AdditionalOptionsViewHolder> adapter = new FirebaseRecyclerAdapter<AdditionalOptions, AdditionalOptionsViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull AdditionalOptionsViewHolder holder, int position, @NonNull final AdditionalOptions model) {
                holder.Categorytitle.setText(model.getCategoryTitle());
                holder.Categorytitle.setTextColor(Color.BLACK);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AdditionalOptionsAdmin.this, AdminAddNewItem.class);
                        intent.putExtra("category", CategoryName);
                        intent.putExtra("foodcategory", model.getCategoryTitle());
                        startActivity(intent);
                        Toast.makeText(AdditionalOptionsAdmin.this, "" + model.getCategoryTitle(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @NonNull
            @Override
            public AdditionalOptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.additionaloptionslayout, parent, false);
                AdditionalOptionsViewHolder holder = new AdditionalOptionsViewHolder(view);
                return holder;
            }
        };
        recycler.setAdapter(adapter);
        adapter.startListening();
    }

}
