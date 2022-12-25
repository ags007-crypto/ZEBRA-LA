package com.firstapp.zebra;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recycler_adapter extends RecyclerView.Adapter<recycler_adapter.ViewHolder> {
    Context context;
    String data[]; String details[]; float rate[];
    public recycler_adapter(Context context, String[] data,String[] details,float[] rate) {
        this.details=details;
        this.rate=rate;
        this.data=data;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.tut_design,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String img="tut2";
        holder.txtview.setText(data[position]);
        holder.details.setText(details[position]);
        holder.ratingBar.setRating(rate[position]);

        switch (position){
            case 0:         holder.tutimg.setImageResource(R.drawable.tutor1);break;
            case 1:         holder.tutimg.setImageResource(R.drawable.tut2);break;
            case 2:         holder.tutimg.setImageResource(R.drawable.tut3);break;
            case 3:         holder.tutimg.setImageResource(R.drawable.tut4);break;
            case 4:         holder.tutimg.setImageResource(R.drawable.tut5);break;
            case 5:         holder.tutimg.setImageResource(R.drawable.tut6);break;


        }


    }

    @Override
    public int getItemCount() {
        return data.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
TextView txtview,details; String imgsrc;



        RatingBar ratingBar;
ImageView tutimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtview=itemView.findViewById(R.id.tutor1txt);
            details=itemView.findViewById(R.id.tutor1details);
            tutimg=itemView.findViewById(R.id.tutor1img);
            ratingBar=itemView.findViewById(R.id.ratingBar);


        }

    }
}
