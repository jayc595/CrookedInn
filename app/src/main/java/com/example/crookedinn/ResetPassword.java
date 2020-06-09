package com.example.crookedinn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crookedinn.Prevalant.Prevalant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    }

    @Override
    protected void onStart() {
        super.onStart();

        numberVerify.setVisibility(View.GONE);

        if(check.equals("settings")){
            title.setText("Set Questions");
            questionTitle.setText("Please set answers for the following Security Questions");
            verifyBtn.setText("Apply Answers");

            verifyBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String answer1 = securityQuestion1.getText().toString().toLowerCase();
                    String answer2 = securityQuestion2.getText().toString().toLowerCase();

                    if (securityQuestion1.equals("")){
                        Toast.makeText(ResetPassword.this, "Question 1 is missing an answer", Toast.LENGTH_SHORT).show();
                    }
                    else if(securityQuestion2.equals("")){
                        Toast.makeText(ResetPassword.this, "Question 2 is missing an answer", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalant.currentOnlineUser.getPhone());

                        HashMap<String, Object> userdataMap = new HashMap<>();
                        userdataMap.put("answer1", answer1);
                        userdataMap.put("answer2", answer2);

                        ref.child("Security Questions").updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
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
            });
        }
        else if(check.equals("login")) {
            numberVerify.setVisibility(View.VISIBLE);

        }
    }
}
