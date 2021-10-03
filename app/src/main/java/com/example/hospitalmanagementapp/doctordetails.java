package com.example.hospitalmanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class doctordetails extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    List<Model> Doctorlist;
   LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordetails);
        initData();
        initRecylerview();

        
    }
    private void initData() {
        Doctorlist = new ArrayList<>();
        Doctorlist.add(new Model(R.drawable.doc1,"Dr Vivek Thapar","Neurologist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc2,"Dr.S.K. Acharya","Cardiologist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc3,"Dr. Kapil Sikka","Audiologist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc4,"Dr. Atul Kumar","Dentist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc5,"Dr. Sachin A Borkar","ENT specialist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc6,"Dr.V.K. Paul","Gynaecologist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc2,"Dr. Sumit Bansal","Paediatrician","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc3,"Dr.R.K. Yadav"," Psychiatrists","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc4,"Dr. Randeep Guleria","Radiologist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc5,"Dr.V.K. Sharma","Endocrinologist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc6,"Dr.P.P. Kotwal","Oncologist","________________________________________"));
        Doctorlist.add(new Model(R.drawable.doc1,"Dr.D.K. Gupta","Pulmonologist","________________________________________"));
    }

    private void initRecylerview() {
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(Doctorlist);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}