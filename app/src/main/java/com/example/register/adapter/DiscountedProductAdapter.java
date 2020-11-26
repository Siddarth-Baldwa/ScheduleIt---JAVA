package com.example.register.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.register.HomeActivity;
import com.example.register.Notif_Main;
import com.example.register.R;
import com.example.register.model.DiscountedProducts;

import java.util.List;

public class DiscountedProductAdapter extends RecyclerView.Adapter<DiscountedProductAdapter.DiscountedProductViewHolder> {

    Context context;
    List<DiscountedProducts> discountedProductsList;

    public DiscountedProductAdapter(Context context, List<DiscountedProducts> discountedProductsList) {
        this.context = context;
        this.discountedProductsList = discountedProductsList;
    }

    @NonNull
    @Override
    public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.discounted_row_items, parent, false);
        return new DiscountedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountedProductViewHolder holder, int position) {

        holder.discountImageView.setImageResource(discountedProductsList.get(position).getImageurl());
        holder.discountImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position){
                    case 0:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","abc");
                        context.startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","xyz");
                        context.startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(context, Notif_Main.class);
                        context.startActivity(intent);
                        break;

                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return discountedProductsList.size();
    }

    public static class DiscountedProductViewHolder extends  RecyclerView.ViewHolder{

        ImageView discountImageView;

        public DiscountedProductViewHolder(@NonNull View itemView) {
            super(itemView);

            discountImageView = itemView.findViewById(R.id.discountImage);

        }
    }
}
