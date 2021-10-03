package com.example.hospitalmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent page1 = new Intent(MainActivity.this,Login.class);
        startActivity(page1);
        finish();
    }
    public void patientstatus(View view){
        Intent page2 = new Intent(MainActivity.this,details.class);
        startActivity(page2);
    }

    public void DoctorPage(View view) {
        Intent page3 = new Intent(MainActivity.this,doctordetails.class);
        startActivity(page3);
    }

    public void Covidpage(View view) {
        
    }

    public void bookingact(View view) {
        Intent page5 = new Intent(MainActivity.this,booking.class);
        startActivity(page5);
    }
}