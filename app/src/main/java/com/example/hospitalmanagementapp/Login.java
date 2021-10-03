package com.example.hospitalmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextInputLayout t1,t2;
    ProgressBar bar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1=(TextInputLayout)findViewById(R.id.email);
        t2=(TextInputLayout)findViewById(R.id.pwd);
        bar=(ProgressBar)findViewById(R.id.progressBar);


    }
    public void loginbutton(View view){
     String email=t1.getEditText().getText().toString();
        String password =t2.getEditText().getText().toString();
        mAuth =FirebaseAuth.getInstance();
        if (TextUtils.isEmpty(email)){
            t1.setError(" Email required ");
            return;
        }
        if (TextUtils.isEmpty(password)){
            t2.setError(" Set Password ");
            return;
        }
        if (password.length()< 6){
            t2.setError("Password must be more than 6 char");
        }
        bar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent main1 =new Intent(Login.this,MainActivity.class);
                    startActivity(main1);

                }
                else{
                    Toast.makeText(Login.this, "Login Failed : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    bar.setVisibility(view.GONE);
                }
            }
        });
    }
    public void Rpage(View view){
        Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
        Intent  rgpage = new Intent(Login.this,Register.class);
        startActivity(rgpage);

    }
}