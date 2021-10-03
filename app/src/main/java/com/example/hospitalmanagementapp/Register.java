package com.example.hospitalmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    TextInputLayout name,email,phnumber,password,age,weight;
    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(TextInputLayout)findViewById(R.id.name);
        email=(TextInputLayout)findViewById(R.id.email);
        phnumber=(TextInputLayout)findViewById(R.id.Phnumber);
        age=(TextInputLayout)findViewById(R.id.age);
        weight=(TextInputLayout)findViewById(R.id.weight);
        password=(TextInputLayout)findViewById(R.id.pwdd);
        mAuth =FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        /*if (mAuth.getCurrentUser() != null){
            Intent main =new Intent(Register.this,MainActivity.class);
            startActivity(main);
            finish();
        }*/

    }
    public void signup(View view){


     String Email= email.getEditText().getText().toString();
     String Password= password.getEditText().getText().toString();
        String Fullname= name.getEditText().getText().toString();
        String PhoneNumber= phnumber.getEditText().getText().toString();
        String Age= age.getEditText().getText().toString();
        String Weight= weight.getEditText().getText().toString();

     if (TextUtils.isEmpty(Email)){
         email.setError(" Email required ");
         return;
     }
        if (TextUtils.isEmpty(Password)){
            password.setError(" Set Password ");
            return;
        }
        if (Password.length()< 6){
            password.setError("Password must be more than 6 char");
        }
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Register.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                    userID = mAuth.getCurrentUser().getUid();
                    
                    DocumentReference documentReference = fstore.collection("Users").document(userID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("Fname",Fullname);
                    user.put("Email",Email);
                    user.put("Password",Password);
                    user.put("Age",Age);
                    user.put("Weight",Weight);
                    user.put("PhNumber",PhoneNumber);
                    user.put("Time","N/A");
                    user.put("Doctor","N/A");
                    user.put("Date","N/A");
                   documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                          Log.d(TAG,"DocumentSnapshot added with ID: " +userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull  Exception e) {
                            Log.d(TAG,"Failed : " +e.toString());
                        }
                    });
                    Intent main =new Intent(Register.this,MainActivity.class);
                    startActivity(main);
                }
                else{
                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    public void loginpage(View view){
        Intent intent=new Intent(Register.this,Login.class);
        startActivity(intent);
    }
}