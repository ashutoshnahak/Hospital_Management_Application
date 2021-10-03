package com.example.hospitalmanagementapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class details extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tv1=findViewById(R.id.textView21);
        tv2=findViewById(R.id.textView23);
        tv3=findViewById(R.id.textView24);
        tv4=findViewById(R.id.textView22);
        tv5=findViewById(R.id.textView25);
        tv6=findViewById(R.id.textView28);
        tv7=findViewById(R.id.textView26);
        tv8=findViewById(R.id.textView27);
        mAuth =FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fstore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable  DocumentSnapshot value, @Nullable  FirebaseFirestoreException error) {
              tv1.setText(value.getString("Fname"));
                tv2.setText(value.getString("Email"));
                tv3.setText(value.getString("PhNumber"));
                tv4.setText(value.getString("Age"));
                tv5.setText(value.getString("Weight"));
                tv6.setText(value.getString("Date"));
                tv7.setText(value.getString("Doctor"));
                tv8.setText(value.getString("Time"));


            }
        });
    }

    public void DashBoard(View view) {
        Intent main =new Intent(details.this,MainActivity.class);
        startActivity(main);
    }
}