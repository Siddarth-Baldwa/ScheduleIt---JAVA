package com.example.register.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.register.CalenderActivity;
import com.example.register.HomeActivity;
import com.example.register.ImpDocuments;
import com.example.register.R;
import com.example.register.model.RecentlyViewed;

import java.util.List;

public class RecentlyViewedAdapter extends RecyclerView.Adapter<RecentlyViewedAdapter.RecentlyViewedViewHolder> {

    Context context;
    List<RecentlyViewed> recentlyViewedList;

    public RecentlyViewedAdapter(Context context, List<RecentlyViewed> recentlyViewedList) {
        this.context = context;
        this.recentlyViewedList = recentlyViewedList;
    }

    @NonNull
    @Override
    public RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recently_viewed_items, parent, false);

        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentlyViewedViewHolder holder, final int position) {

        holder.name.setText(recentlyViewedList.get(position).getName());
        /*holder.description.setText(recentlyViewedList.get(position).getDescription());
        holder.price.setText(recentlyViewedList.get(position).getPrice());
        holder.qty.setText(recentlyViewedList.get(position).getQuantity());
        holder.unit.setText(recentlyViewedList.get(position).getUnit());*/
        holder.categoryImage1.setImageResource(recentlyViewedList.get(position).getImageUrl());
        /*holder.bg.setBackgroundResource(recentlyViewedList.get(position).getImageUrl());*/

        holder.categoryImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                switch(position){
                    case 0:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","Other_Task");
                        context.startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(context, ImpDocuments.class);
                        intent.putExtra("photo","Other_ImpDocuments");
                        context.startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(context, CalenderActivity.class);
                        intent.putExtra("meetings","Other_Calender");
                        context.startActivity(intent);
                        break;
                    /*case 3:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("other","Other_Task");
                        context.startActivity(intent);
                        break;*/
                }

                /*Intent i=new Intent(context, ProductDetails.class);
                *//*i.putExtra("name", recentlyViewedList.get(position).getName());
                i.putExtra("image", recentlyViewedList.get(position).getId());
                i.putExtra("price",recentlyViewedList.get(position).getPrice());
                i.putExtra("desc",recentlyViewedList.get(position).getDescription());
                i.putExtra("qty",recentlyViewedList.get(position).getQuantity());
                i.putExtra("unit",recentlyViewedList.get(position).getUnit());*//*
                context.startActivity(i);*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return recentlyViewedList.size();
    }

    public  static class RecentlyViewedViewHolder extends RecyclerView.ViewHolder{

       /* TextView name, description, price, qty, unit;
        ConstraintLayout bg;*/
       ImageView categoryImage1;
        TextView name;


        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.othertext);
            categoryImage1 = itemView.findViewById(R.id.otherImage);

            /*description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty);
            unit = itemView.findViewById(R.id.unit);
            bg = itemView.findViewById(R.id.recently_layout);*/

        }
    }

}

