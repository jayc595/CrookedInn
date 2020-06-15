package com.example.crookedinn;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crookedinn.Admin.AddCategoryAdmin;
import com.example.crookedinn.Admin.KitchenSettings;
import com.example.crookedinn.Admin.SettingsAdmin;
import com.example.crookedinn.Model.Openclosed;
import com.example.crookedinn.Model.Products;
import com.example.crookedinn.Admin.AdminMaintainActivity;
import com.example.crookedinn.Prevalant.Prevalant;
import com.example.crookedinn.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{


    private AppBarConfiguration mAppBarConfiguration;

    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager LayoutManager;
    private String GetName, Order = "";
    private TextView toolbarName;

    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        type = getIntent().getExtras().get("Admin").toString();

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        toolbarName = (TextView) findViewById(R.id.toolbar_name_layout);

        toolbarName.setText("Menu");


        Paper.init(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(Home.this, CartActivity.class);
                    startActivity(intent);

            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        if(type.equals("Admin")){
            toolbarName.setText("Admin");
            userNameTextView.setText("Welcome Admin");
            fab.setVisibility(View.GONE);
        }

        if (!type.equals("Admin")) {
            userNameTextView.setText(Prevalant.currentOnlineUser.getName());
            Picasso.get().load(Prevalant.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);
        }



        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);

    }


    @Override
    protected void onStart() {
        super.onStart();
        Order = getIntent().getStringExtra("category");

        DatabaseReference openref = FirebaseDatabase.getInstance().getReference().child("OpenClosed");
        openref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Openclosed openclosed = dataSnapshot.getValue(Openclosed.class);

                if (openclosed.getBarmenu().equals("Closed")) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                    builder.setTitle("Please note");
                    final TextView note = new TextView(Home.this);
                    note.setPadding(100, 50, 100, 0);
                    note.setText("The Kitchen is currently closed. However, you can still order a drink");

                    builder.setView(note);

                    builder.setNegativeButton("Browse", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                    nbutton.setTextColor(Color.BLACK);


                } else if (openclosed.getBar().equals("Closed")) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                    builder.setTitle("Please note");
                    final TextView note = new TextView(Home.this);
                    note.setPadding(100, 50, 100, 0);
                    note.setText("We are currently closed, but you can still browse our menu");

                    builder.setView(note);

                    builder.setNegativeButton("Browse", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                    nbutton.setTextColor(Color.BLACK);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        if (Order.equals("starters") || Order.equals("grill") || Order.equals("specials") || Order.equals("sides") || Order.equals("lunch") || Order.equals("pasta") || Order.equals("dessert") || Order.equals("vegeterian")) {
            toolbarName.setText("Menu");
        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>().setQuery(ProductsRef.orderByChild("category").startAt(Order).endAt(Order), Products.class).build();
        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Products model) {
                if (model.getStock().equals("Yes") || model.getStock().equals("NA")) {
                    holder.txtItemName.setVisibility(View.VISIBLE);
                    holder.txtItemPrice.setVisibility(View.VISIBLE);
                    holder.txtItemCategory.setVisibility(View.VISIBLE);
                    holder.itemView.setVisibility(View.VISIBLE);

                    holder.txtItemName.setText(model.getIname());
                    holder.txtItemPrice.setText("£" + model.getPrice());
                    holder.txtItemCategory.setText(model.getCategory());


                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(Home.this, ItemDetails.class);
                                intent.putExtra("pid", model.getPid());
                                startActivity(intent);
                        }
                    });
                } else {
                    holder.Layout_view.setVisibility(View.GONE);

                }
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_products_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        }


        else {
            FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>().setQuery(ProductsRef.orderByChild("catenumber"), Products.class).build();
            FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                @Override
                protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Products model) {
                    if (type.equals("Admin")) {
                        holder.txtItemName.setVisibility(View.VISIBLE);
                        holder.txtItemPrice.setVisibility(View.VISIBLE);
                        holder.txtItemCategory.setVisibility(View.VISIBLE);
                        holder.itemView.setVisibility(View.VISIBLE);

                        holder.txtItemName.setText(model.getIname());
                        holder.txtItemPrice.setText("£" + model.getPrice());
                        holder.txtItemCategory.setText(model.getCategory());

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                    Intent intent = new Intent(Home.this, AdminMaintainActivity.class);
                                    intent.putExtra("pid", model.getPid());
                                    startActivity(intent);
                            }
                        });
                    }
                     else {
                        if (model.getStock().equals("Yes") || model.getStock().equals("NA")) {
                            holder.txtItemName.setVisibility(View.VISIBLE);
                            holder.txtItemPrice.setVisibility(View.VISIBLE);
                            holder.txtItemCategory.setVisibility(View.VISIBLE);
                            holder.itemView.setVisibility(View.VISIBLE);

                            holder.txtItemName.setText(model.getIname());
                            holder.txtItemPrice.setText("£" + model.getPrice());
                            holder.txtItemCategory.setText(model.getCategory());

                            holder.itemView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Intent intent = new Intent(Home.this, ItemDetails.class);
                                        intent.putExtra("pid", model.getPid());
                                        startActivity(intent);

                                }
                            });
                        } else {
                            holder.Layout_view.setVisibility(View.GONE);

                        }
                    }
                }

                @NonNull
                @Override
                public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_products_layout, parent, false);
                    ProductViewHolder holder = new ProductViewHolder(view);
                    return holder;
                }
            };
            recyclerView.setAdapter(adapter);
            adapter.startListening();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    //@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {
            if(!type.equals("Admin")){
                Intent intent = new Intent(Home.this, CartActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "You don't have permission", Toast.LENGTH_SHORT).show();

            }

        } else if (id == R.id.nav_search) {
            if(!type.equals("Admin")){
                Intent intent = new Intent(Home.this, Search.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "You don't have permission", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_categories) {
            if(!type.equals("Admin")){
                Intent intent = new Intent(Home.this, AllCategoriesUser.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(Home.this, AddCategoryAdmin.class);
                startActivity(intent);
                Toast.makeText(this, "You don't have permission", Toast.LENGTH_SHORT).show();

            }
        } else if (id == R.id.nav_settings) {
            if(!type.equals("Admin")){
                Intent intent = new Intent(Home.this, Settings.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(Home.this, KitchenSettings.class);
                startActivity(intent);
                Toast.makeText(this, "You don't have permission", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_logout) {
            Paper.book().destroy();

            Intent intent = new Intent(Home.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(this, "You have logged out", Toast.LENGTH_SHORT).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
