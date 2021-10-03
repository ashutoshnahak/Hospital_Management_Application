package com.example.hospitalmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private List<Model> DoctorList;
    public Adapter(List<Model> DoctorList){
        this.DoctorList=DoctorList;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iten_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  Adapter.ViewHolder holder, int position) {

        int resource=DoctorList.get(position).getImageview1();
        String NameOfDoctor=DoctorList.get(position).getTextview1();
        String Designation=DoctorList.get(position).getTextview2();
        String Divide =DoctorList.get(position).getDivider();
        holder.setData(resource,NameOfDoctor,Designation,Divide);


    }

    @Override
    public int getItemCount() {
        return DoctorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView1;
        private TextView Divider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageview1);
            textView=itemView.findViewById(R.id.nametextview);
            textView1=itemView.findViewById(R.id.designtextview);
            Divider=itemView.findViewById(R.id.divider);

        }

        public void setData(int resource, String nameOfDoctor, String designation, String divide) {
            imageView.setImageResource(resource);
            textView.setText(nameOfDoctor);
            textView1.setText(designation);
            Divider.setText(divide);

        }
    }
}
