package com.majestyinc.translate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<kaya> userlist;
    public MyAdapter(Context context, ArrayList<kaya> userlist) {
        this.context = context;
        this.userlist = userlist;
    }



    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        kaya k = userlist.get(position);
        holder.item.setText(k.item);
        holder.itemSize.setText(k.itemsize);
        String n = k.itemSize1;
        holder.itemSize1.setText(n);

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        EditText name,size,item,itemSize,itemSize1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cnkayaName);
            size = itemView.findViewById(R.id.cngirmaEt1);
            item = itemView.findViewById(R.id.itemname);
            itemSize = itemView.findViewById(R.id.itemsize);
            itemSize1  = itemView.findViewById(R.id.itemsize1);


        }



    }
}
