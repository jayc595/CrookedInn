package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crookedinn.Prevalant.Prevalant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ResetPassword extends AppCompatActivity {
    private String check = "";
    private TextView title, questionTitle;
    private EditText securityQuestion1, securityQuestion2, numberVerify;
    private Button verifyBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        title = findViewById(R.id.securityquestiontitle);
        questionTitle = findViewById(R.id.AnswerFollowingQuestions);
        securityQuestion1 = findViewById(R.id.securityquestion1);
        securityQuestion2 = findViewById(R.id.securityquestion2);
        numberVerify = findViewById(R.id.typephonenumber);
        verifyBtn = findViewById(R.id.verifyBtn);

        check = getIntent().getStringExtra("check");

       // displayPreviousAnswers();
    }

    @Override
    protected void onStart() {
        super.onStart();

        numberVerify.setVisibility(View.GONE);

        if (check.equals("settings")) {
            title.setText("Set Questions");
            questionTitle.setText("Please set answers for the following Security Questions");
            verifyBtn.setText("Apply Answers");
            displayPreviousAnswers();

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setAnswers();

                }
            });
        } else if (check.equals("login")) {
            numberVerify.setVisibility(View.VISIBLE);

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    verifyUser();
                }
            });


        }
    }


    private void setAnswers() {
        String answer1 = securityQuestion1.getText().toString().toLowerCase();
        String answer2 = securityQuestion2.getText().toString().toLowerCase();

        if (securityQuestion1.equals("")) {
            Toast.makeText(ResetPassword.this, "Question 1 is missing an answer", Toast.LENGTH_SHORT).show();
        } else if (securityQuestion2.equals("")) {
            Toast.makeText(ResetPassword.this, "Question 2 is missing an answer", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalant.currentOnlineUser.getPhone());

            HashMap<String, Object> userdataMap = new HashMap<>();
            userdataMap.put("answer1", answer1);
            userdataMap.put("answer2", answer2);

            ref.child("Security Questions").updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ResetPassword.this, "Security Questions have been updated Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ResetPassword.this, Home.class);
                        intent.putExtra("category", "none");
                        intent.putExtra("Admin", "User");
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void displayPreviousAnswers(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalant.currentOnlineUser.getPhone());

        ref.child("Security Questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String ans1 = dataSnapshot.child("answer1").getValue().toString();
                    String ans2 = dataSnapshot.child("answer2").getValue().toString();

                    securityQuestion1.setText(ans1);
                    securityQuestion2.setText(ans2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void verifyUser(){
        final String phone = numberVerify.getText().toString();
        final String answer1 = securityQuestion1.getText().toString().toLowerCase();
        final String answer2 = securityQuestion2.getText().toString().toLowerCase();

        if(!phone.equals("") && !answer1.equals("") && !answer2.equals("")){
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(phone);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){

                        String uPhone = dataSnapshot.child("phone").getValue().toString();
                        if (dataSnapshot.hasChild("Security Questions")) {
                            String ans1 = dataSnapshot.child("Security Questions").child("answer1").getValue().toString();
                            String ans2 = dataSnapshot.child("Security Questions").child("answer2").getValue().toString();

                            if(!ans1.equals(answer1)) {
                                Toast.makeText(ResetPassword.this, "One of your Security Questions are Incorrect", Toast.LENGTH_SHORT).show();
                            }
                            if(!ans2.equals(answer2)) {
                                Toast.makeText(ResetPassword.this, "One of your Security Questions are Incorrect", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(ResetPassword.this);
                                builder.setTitle("New Password");
                                final EditText newPassword = new EditText(ResetPassword.this);
                                newPassword.setHint("Write new password");
                                builder.setView(newPassword);

                                builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(!newPassword.getText().toString().equals("")){
                                            ref.child("password").setValue(newPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        Toast.makeText(ResetPassword.this, "Password changed Successfully", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        }
                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int which) {
                                        dialogInterface.cancel();
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                                pbutton.setTextColor(Color.BLACK);
                                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                                nbutton.setTextColor(Color.BLACK);
                            }
                        }

                        else {
                            Toast.makeText(ResetPassword.this, "Security Questions haven't been set, contact Admin", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(ResetPassword.this, "Phone number doesn't exist", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else {
            Toast.makeText(this, "Please complete the Password Reset form", Toast.LENGTH_SHORT).show();
        }


    }

}
