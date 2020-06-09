package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crookedinn.Model.Users;
import com.example.crookedinn.Prevalant.Prevalant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

        private Button signupButton, loginButton;
        private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    signupButton = (Button) findViewById(R.id.main_signup_btn);
    loginButton = (Button) findViewById(R.id.main_login_btn);
    loadingBar = new ProgressDialog(this);

        Paper.init(this);

    loginButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View view)
        {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    } );

    signupButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View view)
        {
            Intent intent = new Intent(MainActivity.this, Register.class);
            startActivity(intent);
        }

    } );

    String UserPhoneKey = Paper.book().read(Prevalant.UserPhoneKey);
    String UserPasswordKey = Paper.book().read(Prevalant.UserPasswordKey);
    if (UserPhoneKey != "" && UserPasswordKey != ""){
        if (!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)) {
            AllowAccess(UserPhoneKey, UserPasswordKey);

            loadingBar.setTitle("Already Logged In");
            loadingBar.setMessage("Please wait");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
        }
    }

    }

    private void AllowAccess(final String phone, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Users").child(phone).exists()) {
                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone)) {
                        if (usersData.getPassword().equals(password)) {
                            Toast.makeText(MainActivity.this, "Please wait, you're already logged in..", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                            Intent intent = new Intent(MainActivity.this, Home.class);
                            intent.putExtra("category", "none");
                            intent.putExtra("Admin", "User");
                            Prevalant.currentOnlineUser = usersData;
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Password is Incorrect...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Account with the number:" + phone + "does not exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
