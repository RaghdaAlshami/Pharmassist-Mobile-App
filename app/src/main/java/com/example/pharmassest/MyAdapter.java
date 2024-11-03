package com.example.pharmassest;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<Model> dataholder;


    public MyAdapter(ArrayList<Model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_medName.setText(dataholder.get(position).getMed());
        holder.tv_desc.setText(dataholder.get(position).getDesc());
        holder.tv_com.setText(dataholder.get(position).getCom());
        holder.tv_closet.setText(dataholder.get(position).getCloset());
        holder.tv_shelf.setText(dataholder.get(position).getShelf());
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_medName,tv_com,tv_desc,tv_closet,tv_shelf;
        public ViewHolder(@NonNull View view) {
            super(view);
            tv_medName = (TextView)itemView.findViewById(R.id.tv_medicine_name);
            tv_desc = (TextView)itemView.findViewById(R.id.tv_desc);
            tv_com = (TextView)itemView.findViewById(R.id.tv_company);
            tv_closet = (TextView)itemView.findViewById(R.id.tv_closet);
            tv_shelf = (TextView)itemView.findViewById(R.id.tv_shelf_no);
        }
    }
}

