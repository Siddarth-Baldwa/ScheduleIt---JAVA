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
import com.example.register.ImpDocuments;
import com.example.register.R;
import com.example.register.model.WorkModel;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.DiscountedProductViewHolder> {

    Context context;
    List<WorkModel> workModelList;

    public WorkAdapter(Context context, List<WorkModel> workModelList) {
        this.context = context;
        this.workModelList = workModelList;
    }

    @NonNull
    @Override
    public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.work_items, parent, false);
        return new DiscountedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountedProductViewHolder holder, int position) {

        holder.discountImageView.setImageResource(workModelList.get(position).getImageurl());
        holder.discountImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position){
                    case 0:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","CatchUpCalls");
                        context.startActivity(intent);
                        break;
                    case 1:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","Queries");
                        context.startActivity(intent);
                        break;
                    case 2:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","Important Mails");
                        context.startActivity(intent);
                        break;

                    case 3:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","Meetings");
                        context.startActivity(intent);
                        break;
                    case 4:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","Points to Remember");
                        context.startActivity(intent);
                        break;
                    case 5:
                        intent=new Intent(context, ImpDocuments.class);
                        context.startActivity(intent);
                        break;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return workModelList.size();
    }

    public static class DiscountedProductViewHolder extends  RecyclerView.ViewHolder{

        ImageView discountImageView;

        public DiscountedProductViewHolder(@NonNull View itemView) {
            super(itemView);

            discountImageView = itemView.findViewById(R.id.discountImage);

        }
    }
}
