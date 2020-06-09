package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.crookedinn.Admin.AdminCategory;
import com.rey.material.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.crookedinn.Model.Users;
import com.example.crookedinn.Prevalant.Prevalant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Login extends AppCompatActivity {
    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdmin, ForgetPassword;

    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.login_btn);
        InputPhoneNumber = (EditText) findViewById(R.id.login_phone_number_input);
        InputPassword = (EditText) findViewById(R.id.password_input);
        AdminLink = (TextView) findViewById(R.id.admin_panel_link);
        NotAdmin = (TextView) findViewById(R.id.not_admin_panel_link);
        ForgetPassword = (TextView) findViewById(R.id.forgot_password_link);
        loadingBar = new ProgressDialog(this);


        chkBoxRememberMe = (CheckBox) findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });

        ForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ResetPassword.class);
                intent.putExtra("check", "login");
                startActivity(intent);
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdmin.setVisibility(View.VISIBLE);
                chkBoxRememberMe.setVisibility(View.GONE);
                ForgetPassword.setVisibility(View.GONE);
                parentDbName = "Admins";
            }
        });

        NotAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdmin.setVisibility(View.INVISIBLE);
                chkBoxRememberMe.setVisibility(View.VISIBLE);
                ForgetPassword.setVisibility(View.VISIBLE);
                parentDbName = "Users";
            }
        });


    }

    private void LoginUser(){
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please type your Password...", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone, password);
        }
    }


    private void AllowAccessToAccount(final String phone, final String password){

        if(chkBoxRememberMe.isChecked()){
            Paper.book().write(Prevalant.UserPhoneKey, phone);
            Paper.book().write(Prevalant.UserPasswordKey, password);
        }
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(parentDbName).child(phone).exists()) {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone)) {
                        if (usersData.getPassword().equals(password)) {
                            if (parentDbName.equals("Admins")) {
                                Toast.makeText(Login.this, "Welcome Admin, Login Successful!", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(Login.this, AdminCategory.class);
                                startActivity(intent);
                            }
                            else if (parentDbName.equals("Users")) {
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(Login.this, Home.class);
                                intent.putExtra("category", "none");
                                intent.putExtra("Admin", "User");
                                Prevalant.currentOnlineUser = usersData;
                                startActivity(intent);
                            }
                        }
                        else {
                            Toast.makeText(Login.this, "Password is Incorrect...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                }
                else {
                    Toast.makeText(Login.this, "Account with the number: " + phone + " does not exist", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
