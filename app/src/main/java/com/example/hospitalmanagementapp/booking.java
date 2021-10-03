package com.example.hospitalmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.hospitalmanagementapp.Register.TAG;

public class booking extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView,autoCompleteTextView1;
    String doctorname,time,date;
    TextView tv,tv1;
    ImageView iv;
    ProgressBar bar;
    int m,d,y;
    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        mAuth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        autoCompleteTextView=findViewById(R.id.doctorbook);
        autoCompleteTextView1=findViewById(R.id.doctortime);
        String[] options1 ={"5 PM - 6 PM","6 PM - 7 PM","7 PM - 8 PM"};
        String[] options ={"Dr. Atul Kumar","Dr. S.K. Acharya","Dr. Kapil Sikka","Dr. Sachin A Borkar","Dr. Vivek Thapar","Dr. V.K. Paul","Dr. Sumit Bansal","Dr. R.K. Yadav","Dr. Randeep Guleria","Dr. V.K. Sharma","Dr. P.P. Kotwal","Dr. D.K. Gupta"};
        ArrayAdapter arrayAdapter= new ArrayAdapter(this,R.layout.doctoradapter,options);
        ArrayAdapter arrayAdapter1= new ArrayAdapter(this,R.layout.doctoradapter,options1);
        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(),false);
        autoCompleteTextView1.setText(arrayAdapter1.getItem(0).toString(),false);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView1.setAdapter(arrayAdapter1);
        tv=(TextView)findViewById(R.id.dateofapp);
        iv=(ImageView)findViewById(R.id.imageButton);
        Calendar c= Calendar.getInstance();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y= c.get(Calendar.YEAR);
                d= c.get(Calendar.DAY_OF_MONTH);
                m= c.get(Calendar.MONTH);
                DatePickerDialog g=new DatePickerDialog(booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                     tv.setText(dayOfMonth+" / "+(1+month)+" / "+year);

                    }
                },y,m,d);
               g.show();
            }
        });
    }

    public void Book(View view) {
        doctorname=autoCompleteTextView.getText().toString();
        time=autoCompleteTextView1.getText().toString();
        date=tv.getText().toString();
        bar=findViewById(R.id.progressBar2);
        bar.setVisibility(View.VISIBLE);
        tv1=findViewById(R.id.textView9);

        DocumentReference documentReference = fstore.collection("Users").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("Doctor",doctorname);
        user.put("Date",date);
        user.put("Time",time);

        documentReference.update(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //tv1.setText("Appointment Fixed");
                        Toast.makeText(booking.this, "Appointment Successfull", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"updated");
                        bar.setVisibility(View.GONE);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Toast.makeText(booking.this, "Appointment Failed", Toast.LENGTH_SHORT).show();
                     Log.d(TAG,"failed ");
                    }
                });
    }

    public void Cancel(View view) {

        bar=findViewById(R.id.progressBar2);
        bar.setVisibility(View.VISIBLE);
        tv1=findViewById(R.id.textView9);

        DocumentReference documentReference = fstore.collection("Users").document(userID);
        Map<String, Object> user = new HashMap<>();
        user.put("Doctor","N/A");
        user.put("Date","N/A");
        user.put("Time","N/A");

        documentReference.update(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //tv1.setText("Appointment Fixed");
                        Toast.makeText(booking.this, "Appointment Cancelled Successfully", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"updated");
                        bar.setVisibility(View.GONE);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Toast.makeText(booking.this, "Cancellation Failed", Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"failed ");
                    }
                });
    }
}