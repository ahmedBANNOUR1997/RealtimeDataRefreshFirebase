package org.o7planning.mystationcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Temperature extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference p1,p2;
    private TextView temperature, humidité;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        temperature = findViewById(R.id.temp);
        humidité= findViewById(R.id.hum);

        firebaseDatabase = FirebaseDatabase.getInstance();
        p1= firebaseDatabase.getReference("/station/Temperature");
        p1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String  value = datasnapshot.getValue(String.class);
                temperature.setText(value);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        p2= firebaseDatabase.getReference("station/Humidite");
        p2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                String  value = datasnapshot.getValue(String.class);
                humidité.setText(value);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }}