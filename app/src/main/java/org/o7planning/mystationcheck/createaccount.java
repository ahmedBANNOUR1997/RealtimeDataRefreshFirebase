package org.o7planning.mystationcheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class createaccount extends AppCompatActivity {
    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword, etRegPhone, etRegfullname;
    ProgressBar progressbar ;
    FirebaseAuth mAuth;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPass);
        etRegPhone = findViewById(R.id.etRegphone);
        etRegfullname = findViewById(R.id.etRegfullname);
        progressbar = findViewById(R.id.pg2);
        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.btnRegister);

       // if(mAuth.getCurrentUser() != null){
      //      startActivity(new Intent(getApplicationContext(),MainActivity.class));
        //    finish();
      //  }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email =etRegEmail.getText().toString();
                String password = etRegPassword.getText().toString();
                final String fullName = etRegfullname.getText().toString();
                final String phone    = etRegPhone .getText().toString();

                if(TextUtils.isEmpty(email)){
                    etRegEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    etRegPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    etRegPassword.setError("Password Must be >= 6 Characters");
                    return;
                }

                progressbar.setVisibility(View.VISIBLE);

                // register the user in firebase


                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAuth.createUserWithEmailAndPassword(email,password);
                    }
                });

                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);

                            // if the user created intent to login activity
                            Intent intent
                                    = new Intent(createaccount.this,
                                    login.class);
                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // hide the progress bar
                            progressbar.setVisibility(View.GONE);
                        }

                    }
                });
            }
        });

    }
}