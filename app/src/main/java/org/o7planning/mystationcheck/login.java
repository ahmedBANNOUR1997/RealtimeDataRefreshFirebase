package org.o7planning.mystationcheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
        Button login;
        EditText user_login,password;
        TextView newAcc;
        ProgressBar progressBar;
        FirebaseAuth fAuth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            user_login = findViewById(R.id.emailtxt);
            password = findViewById(R.id.mpassword);
            progressBar = findViewById(R.id.progressBar2);
            fAuth = FirebaseAuth.getInstance();
            login = findViewById(R.id.loginBTN);
            newAcc = findViewById(R.id.newacc);


            newAcc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(login.this, createaccount.class));
                }
            });


            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = user_login.getText().toString();
                    String  motdepasse  = password.getText().toString();

                    if(TextUtils.isEmpty(email)){
                        user_login.setError("Email is Required.");
                        return;
                    }

                    if(TextUtils.isEmpty(motdepasse)){
                        password.setError("Password is Required.");
                        return;
                    }

                    if(password.length() < 6){
                        password.setError("Password Must be >= 6 Characters");
                        return;
                    }

                    progressBar.setVisibility(View.VISIBLE);

                    // authenticate the user

                    fAuth.signInWithEmailAndPassword(email,motdepasse).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),Temperature.class));
                            }else {
                                Toast.makeText(login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                    });

                }
            });

        }}